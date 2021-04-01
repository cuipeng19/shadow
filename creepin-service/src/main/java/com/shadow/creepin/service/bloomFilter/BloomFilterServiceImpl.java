package com.shadow.creepin.service.bloomFilter;

import com.github.pagehelper.PageHelper;
import com.google.common.hash.Funnels;
import com.shadow.creepin.dao.BMerchAudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author cuipeng 2021/3/29 10:47
 */
@Service
public class BloomFilterServiceImpl implements BloomFilterService {

    private BloomFilterHelper bloomFilterHelper;

    @Autowired
    private BMerchAudMapper merchAudMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;




    @Override
    public void run(ApplicationArguments args) throws Exception {
        int count = merchAudMapper.getMerchCount();

        this.bloomFilterHelper = new BloomFilterHelper<>(Funnels.stringFunnel(Charset.defaultCharset()), count+10000, 0.01);

        for(int i=1; i<=(count/50000)+1; i++) {
            PageHelper.startPage(i,50000);
            List<String> list = merchAudMapper.getMerchId();
            list.parallelStream().forEach(e -> {
                put("bloom:merchId", e);
            });
            System.out.println("put完成" + i);
        }
        System.out.println("bloom完成");
    }



    private <T> void put(String key, T value) {
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for(int i : offset) {
            stringRedisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    @Override
    public <T> boolean mightContain(String key, T value) {
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for(int i : offset) {
            if(!stringRedisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }
}
