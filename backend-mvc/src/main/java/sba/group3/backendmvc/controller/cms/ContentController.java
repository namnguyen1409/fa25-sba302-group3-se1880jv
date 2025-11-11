package sba.group3.backendmvc.controller.cms;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.cms.ContentRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.cms.ContentResponse;
import sba.group3.backendmvc.service.cms.ContentService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/cms/contents")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "CMS", description = "Content Management APIs")
public class ContentController {
    ContentService contentService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ContentResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering contents with criteria: {}", filter);
        return ResponseEntity.ok(CustomApiResponse.<Page<ContentResponse>>builder()
                .data(contentService.filter(filter)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ContentResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.getById(id)).build());
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<CustomApiResponse<ContentResponse>> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.getBySlug(slug)).build());
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','MANAGER')")
    @PostMapping
    public ResponseEntity<CustomApiResponse<ContentResponse>> create(
            @RequestBody ContentRequest req,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var authorUserId = UUID.fromString(jwt.getClaimAsString("staffId"));
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.create(req, authorUserId)).build());
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ContentResponse>> update(@PathVariable UUID id, @RequestBody ContentRequest req) {
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.update(id, req)).build());
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable UUID id) {
        contentService.delete(id);
        return ResponseEntity.ok(CustomApiResponse.<Void>builder().message("Deleted").build());
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','MANAGER')")
    @PostMapping("/{id}/publish")
    public ResponseEntity<CustomApiResponse<ContentResponse>> publish(@PathVariable UUID id) {
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.publish(id)).build());
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','MANAGER')")
    @PostMapping("/{id}/archive")
    public ResponseEntity<CustomApiResponse<ContentResponse>> archive(@PathVariable UUID id) {
        return ResponseEntity.ok(CustomApiResponse.<ContentResponse>builder()
                .data(contentService.archive(id)).build());
    }

}
