package com.shadow.creepin.service.bloomFilter;

import org.springframework.boot.ApplicationRunner;

/**
 * @author cuipeng 2021/3/29 10:46
 */
public interface BloomFilterService extends ApplicationRunner {

    <T> boolean mightContain(String key, T value);

}
