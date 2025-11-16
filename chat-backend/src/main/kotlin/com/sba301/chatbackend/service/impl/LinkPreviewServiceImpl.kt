package com.sba301.chatbackend.service.impl

import com.sba301.chatbackend.entity.EmbedCard
import com.sba301.chatbackend.entity.LinkPreview
import com.sba301.chatbackend.entity.UrlPreviewCache
import com.sba301.chatbackend.repository.UrlPreviewCacheRepository
import com.sba301.chatbackend.service.LinkPreviewService
import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@Service
class LinkPreviewServiceImpl(
    private val webClient: WebClient,
    private val urlPreviewCacheRepository: UrlPreviewCacheRepository
) : LinkPreviewService {


    private val URL_CANDIDATE = Regex("""(?i)\b(?:https?://|www\.)[^\s<>()\[\]{}"']+""")
    private val TRAILING_JUNK = Regex("""[)\].,;:!?'”…]+$""")
    private val youtubeRegex = Regex("""(?:https?://)?(?:www\.)?(?:youtube\.com/watch\?v=|youtu\.be/)([a-zA-Z0-9_-]+)""")
    private val spotifyRegex = Regex("""(?:https?://)?open\.spotify\.com/(track|album|playlist)/([a-zA-Z0-9]+)""")


    fun fetchPreview(url: String): Mono<LinkPreview> {
        val uri = URI.create(url)
        if (!isSafePublicHost(uri)) return Mono.empty()


        return urlPreviewCacheRepository.findById(url)
            .map { it.preview }
            .switchIfEmpty(
                webClient.get().uri(uri).retrieve().toEntity(String::class.java)
                    .flatMap { resp ->
                        val body = resp.body ?: return@flatMap Mono.empty<LinkPreview>()
                        val ct = resp.headers.contentType?.toString() ?: "text/plain"
                        val finalUrl = resp.headers.location?.toString() ?: url
                        val doc = Jsoup.parse(body, finalUrl)
                        fun meta(name: String) = doc.selectFirst("""meta[property="$name"], meta[name="$name"]""")?.attr("content")
                        val title = doc.title().takeIf { it.isNotBlank() } ?: meta("og:title") ?: meta("twitter:title") ?: ""
                        val description = meta("og:description") ?: meta("twitter:description") ?: doc.selectFirst("meta[name=description]")?.attr("content")
                        val image = meta("og:image") ?: meta("twitter:image") ?: doc.selectFirst("meta[name=image]")?.attr("content") ?: doc.selectFirst("link[rel=image_src]")?.attr("href")
                        val siteName = meta("og:site_name") ?: doc.selectFirst("meta[name=application-name]")?.attr("content")
                        val icon = doc.selectFirst("link[rel~=(?i)^(shortcut icon|icon)$]")?.attr("href")
                        val preview = LinkPreview(url = url, finalUrl = finalUrl, siteName = siteName, title = title, description = description, image = image, icon = icon, contentType = ct)
                        urlPreviewCacheRepository.save(UrlPreviewCache(url, preview)).thenReturn(preview)
                    }
                    .onErrorResume { Mono.empty() }
            )
    }


    override fun fetchPreviews(urls: List<String>): Mono<List<LinkPreview>> =
        Flux.fromIterable(urls.take(10)).flatMapSequential({ fetchPreview(it) }, 10).collectList()


    data class LinkProcessResult(
        val linkPreviews: List<LinkPreview> = emptyList(),
        val embedCards: List<EmbedCard> = emptyList()
    )


    override fun processLinks(urls: List<String>): Mono<LinkProcessResult> {
        if (urls.isEmpty()) return Mono.empty()
        return Flux.fromIterable(urls.take(10))
            .flatMapSequential({ url ->
                when {
                    youtubeRegex.containsMatchIn(url) -> fetchYouTubeEmbed(url).map { LinkProcessResult(embedCards = listOf(it)) }
                    spotifyRegex.containsMatchIn(url) -> fetchSpotifyEmbed(url).map { LinkProcessResult(embedCards = listOf(it)) }
                    else -> fetchPreview(url).map { LinkProcessResult(linkPreviews = listOf(it)) }
                }
            }, 10)
            .reduce(LinkProcessResult()) { acc, r -> acc.copy(linkPreviews = acc.linkPreviews + r.linkPreviews, embedCards = acc.embedCards + r.embedCards) }
    }


    private fun fetchYouTubeEmbed(url: String): Mono<EmbedCard> {
        val videoId = youtubeRegex.find(url)?.groupValues?.get(1) ?: return Mono.empty()
        val embedUrl = "https://www.youtube.com/embed/$videoId"
        return Mono.just(EmbedCard(provider = "youtube", type = "video", originalUrl = url, embedHtml = """<iframe width=\"560\" height=\"315\" src=\"$embedUrl\" frameborder=\"0\" allowfullscreen></iframe>""", embedUrl = embedUrl, thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"))
    }


    private fun fetchSpotifyEmbed(url: String): Mono<EmbedCard> {
        val m = spotifyRegex.find(url) ?: return Mono.empty()
        val type = m.groupValues[1]; val id = m.groupValues[2]
        val embedUrl = "https://open.spotify.com/embed/$type/$id"
        return Mono.just(EmbedCard(provider = "spotify", type = "audio", originalUrl = url, embedHtml = """<iframe src=\"$embedUrl\" width=\"300\" height=\"380\" frameborder=\"0\" allow=\"encrypted-media\"></iframe>""", embedUrl = embedUrl))
    }

    override fun detectUrls(text: String?, max: Int): List<String> {
        if (text.isNullOrBlank()) return emptyList()
        return URL_CANDIDATE.findAll(text)
            .map { it.value }.map { tidyToken(it) }.mapNotNull { normalizeUrl(it) }
            .distinct().take(max).toList()
    }

    private fun tidyToken(token: String): String {
        var s = token.trim()
        s = s.replace(TRAILING_JUNK, "")
        val open = s.count { it == '(' }; val close = s.count { it == ')' }
        if (close > open) { var toTrim = close - open; while (toTrim > 0 && s.endsWith(")")) { s = s.dropLast(1); toTrim-- } }
        return s
    }

    private fun normalizeUrl(raw: String): String? {
        return try {
            val withScheme = if (raw.startsWith("http://", true) || raw.startsWith("https://", true)) raw else "http://$raw"
            val uri = java.net.URI.create(withScheme); val host = uri.host ?: return null
            if (host.contains(' ')) return null
            if (!isSafePublicHost(uri)) return null
            withScheme
        } catch (_: Throwable) { null }
    }

    private fun isSafePublicHost(uri: java.net.URI): Boolean {
        val host = uri.host ?: return false
        val ips = try { java.net.InetAddress.getAllByName(host) } catch (_: Throwable) { return false }
        return ips.all { ip ->
            val a = ip.address
            !(ip.isAnyLocalAddress || ip.isLoopbackAddress || ip.isLinkLocalAddress ||
                    (a.size == 4 && ((a[0].toInt() and 0xFF) == 10 || ((a[0].toInt() and 0xFF) == 172 && ((a[1].toInt() and 0xF0) == 16)) || ((a[0].toInt() and 0xFF) == 192 && (a[1].toInt() and 0xFF) == 168))))
        }
    }
}