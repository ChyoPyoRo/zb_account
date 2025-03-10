package com.example.zb_account.service;

import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class LockService {
    private final RedissonClient redisson;

    public void lock(String accountNumber){
        RLock lock = redisson.getLock(getLockKey(accountNumber));
        log.debug("Trying to lock account {}", accountNumber);
        try{
            boolean isLock = lock.tryLock(1,100, TimeUnit.SECONDS);
            if(!isLock){
                log.error("!!!!! LOCK ACQUISITION FAILED!!!!!");
                throw new CustomException(CustomError.AOP_LOCK);
            }
        }
        catch(CustomException e){
            log.error(e.getMessage());
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage());
            log.error("!!!!! REDIS LOCK FAILED !!!!!");
        }
    }

    public void unlock(String accountNumber){
        log.debug("Trying to unlock account {}", accountNumber);
        redisson.getLock(getLockKey(accountNumber)).unlock();
    }

    private String getLockKey(String accountNumber){
        return "ACLK:"+accountNumber;
    }
}
