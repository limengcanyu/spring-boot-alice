package com.spring.boot.caffeine;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

//    @Bean
//    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
//        return new CacheManagerCustomizer<ConcurrentMapCacheManager>() {
//
//            @Override
//            public void customize(ConcurrentMapCacheManager cacheManager) {
//                cacheManager.setAllowNullValues(false);
//            }
//
//        };
//    }

}
