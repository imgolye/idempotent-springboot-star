package org.link.redis.repository;

import org.link.core.constant.IdempotentConstant;
import org.link.core.properties.IdempotentProperties;
import org.link.core.service.AbsIdempotentAuthorizationProcessor;
import org.link.core.utils.StringUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的幂等处理器
 * @Author: Gol
 */
@Component
public class RedisIdempotentAuthorizationProcessor  extends AbsIdempotentAuthorizationProcessor {

    private final RedissonClient redissonClient;

    private final IdempotentProperties idempotentProperties;

    public RedisIdempotentAuthorizationProcessor(RedissonClient redissonClient, IdempotentProperties idempotentProperties) {
        this.redissonClient = redissonClient;
        this.idempotentProperties = idempotentProperties;
    }

    /**
     * 创建幂等计数
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @param timeUnit       时间策略
     * @return boolean
     */
    @Override
    protected boolean createIdempotentTally(String idempotentKey, Long idempotentTime, TimeUnit timeUnit) {
        idempotentKey = StringUtils.join(idempotentProperties.getApplicationName(), IdempotentConstant.IDEMPOTENT_KEY, IdempotentConstant.IDEMPOTENT_SPLIT, idempotentKey);
        RAtomicLong atomicLong = redissonClient.getAtomicLong(idempotentKey);
        if (atomicLong.isExists()) {
            return false;
        }
        long idempotentCount = atomicLong.incrementAndGet();
        if (idempotentCount == 1) {
            // 保证过期时间只设置一次,不进行多次续期
            atomicLong.expire(idempotentTime, timeUnit);
            return true;
        }
        return false;
    }
}
