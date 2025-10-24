package sba.group3.backendmvc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomLuceneAnalysisConfigurer implements LuceneAnalysisConfigurer {
    @Override
    public void configure(LuceneAnalysisConfigurationContext luceneAnalysisConfigurationContext) {
        luceneAnalysisConfigurationContext.analyzer("english").custom()
                .tokenizer("standard")
                .tokenFilter("lowercase")
                .tokenFilter("asciifolding")
                .tokenFilter("stop")
                .tokenFilter("porterStem")
                .tokenFilter("ngram")
                .param("minGramSize", "5")
                .param("maxGramSize", "10")
        ;
        luceneAnalysisConfigurationContext.normalizer("lowercase").custom()
                .tokenFilter("lowercase");

    }
}
