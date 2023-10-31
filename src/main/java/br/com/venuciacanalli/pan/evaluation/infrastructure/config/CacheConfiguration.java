package br.com.venuciacanalli.pan.evaluation.infrastructure.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @CacheEvict(allEntries = true, cacheNames = { "clients"})
    @Scheduled(fixedDelay = 60000)
    public void defaultCacheEvict() {}

}
