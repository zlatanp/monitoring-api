package com.monitoring.monitorapi.service.command;

import com.monitoring.monitorapi.service.command.context.Context;
import lombok.Builder;
import lombok.Getter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

public abstract class CacheCommand<RESULT> implements Command<RESULT> {

    protected <CACHE_KEY, CACHE_RESULT> Cache<CACHE_KEY, CACHE_RESULT> getOrCreateCache(
        final CacheConfig<CACHE_KEY, CACHE_RESULT> cacheConfig,
        final Context ctx) {
        var cacheManager = ctx.getCacheManager();
        var cache = cacheManager.getCache(cacheConfig.cacheName, cacheConfig.cacheKeyClass, cacheConfig.cacheResultClass);

        return cache == null ? createCache(cacheManager, cacheConfig) : cache;
    }

    private <CACHE_KEY, CACHE_RESULT> Cache<CACHE_KEY, CACHE_RESULT> createCache(
        final CacheManager cacheManager,
        final CacheConfig<CACHE_KEY, CACHE_RESULT> cacheConfig) {

        // TODO allow custom configuration using CacheConfig
        var cacheConfiguration = CacheConfigurationBuilder
            .newCacheConfigurationBuilder(cacheConfig.cacheKeyClass, cacheConfig.cacheResultClass, ResourcePoolsBuilder.heap(2000))
            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(60))).build();

        return cacheManager.createCache(cacheConfig.cacheName, cacheConfiguration);
    }

    @Builder
    @Getter
    protected static class CacheConfig<CACHE_KEY, RESULT> {
        private final String cacheName;
        private final Class<CACHE_KEY> cacheKeyClass;
        private final Class<RESULT> cacheResultClass;
    }
}
