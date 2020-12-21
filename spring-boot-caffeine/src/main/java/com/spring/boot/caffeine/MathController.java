package com.spring.boot.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MathController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private MathService mathService;

    /**
     * localhost:8080/math/computePiDecimal
     *
     * output:
     *
     * cache2
     * piDecimals
     * cache1
     * cache: org.springframework.cache.concurrent.ConcurrentMapCache@59fd5c10
     * result: 0 第一次调用，还没缓存，因此返回备选值0
     *
     * cache2
     * piDecimals
     * cache1
     * cache: org.springframework.cache.concurrent.ConcurrentMapCache@59fd5c10
     * result: 2 第二次调用，已缓存，返回缓存值2
     *
     * @return
     */
    @RequestMapping("/math/computePiDecimal")
    public int computePiDecimal() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
//        cacheNames.forEach(System.out::println);

        Cache cache = cacheManager.getCache("piDecimals");
//        System.out.println("cache: " + cache);

        if (!ObjectUtils.isEmpty(cache)) {
            Integer result = cache.get(1, Integer.class);
            System.out.println("result: " + Optional.ofNullable(result).orElse(0));
        }

        return mathService.computePiDecimal(1);
    }
}
