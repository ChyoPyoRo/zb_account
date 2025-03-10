package com.example.zb_account.service;

import com.example.zb_account.aop.AccountLockIdInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LockAopAspect {
    private final LockService lockService;
    private final ThreadLocal<Boolean> isLocked = ThreadLocal.withInitial(() -> false);
    @Around("@annotation(com.example.zb_account.aop.AccountLock) && args(request)")
    public Object aroundMethod(
            ProceedingJoinPoint pjp,
            AccountLockIdInterface request
    )throws Throwable {
        String accountNumber = request.getAccountNumber();
        //lock 취득 시도
        log.debug("Lock account number: {}", accountNumber);

        try{
            lockService.lock(accountNumber);
            isLocked.set(true);
            return pjp.proceed();

        }
        catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        finally {
            //lock 해제
            if(isLocked.get()){
                lockService.unlock(accountNumber);
                isLocked.remove();
            }
        }
    }
}
