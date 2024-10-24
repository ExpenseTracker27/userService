package github.tanishqtrivedi27.userService.services;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DistributedLockService {
    private final RedissonClient redissonClient;
    private static final long LOCK_TIMEOUT = 10000L; // 10 seconds

    @Autowired
    public DistributedLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean acquireLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void releaseLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}
