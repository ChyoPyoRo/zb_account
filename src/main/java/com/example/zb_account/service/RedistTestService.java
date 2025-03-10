package com.example.zb_account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedistTestService {
    private final RedissonClient redissonClient;

    public String getData(String id){
        RLock lock = redissonClient.getLock("lock" + id);
        try{
            log.debug("try lock");
            boolean isLock = lock.tryLock(1,15, TimeUnit.SECONDS);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        finally {
            return lock.isLocked() ? "true" : "false";
        }
    };
}
