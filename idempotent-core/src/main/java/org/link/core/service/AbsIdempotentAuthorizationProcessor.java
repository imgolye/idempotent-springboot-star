package org.link.core.service;

import org.link.core.constant.IdempotentConstant;
import org.link.core.exception.IdempotentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 幂等处理抽象类
 * @author gol
 */
public abstract class AbsIdempotentAuthorizationProcessor implements IdempotentAuthorizationProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbsIdempotentAuthorizationProcessor.class);

    /**
     * 创建幂等计数器
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @param timeUnit       时间策略
     * @return boolean
     */
    protected abstract boolean createIdempotentTally(String idempotentKey, Long idempotentTime, TimeUnit timeUnit);

    /**
     * 幂等执行的入口
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间,默认毫秒级别
     * @return boolean
     */
    @Override
    public boolean execute(String idempotentKey, Long idempotentTime) throws IdempotentException {
        LOGGER.info("execute idempotent check, idempotentKey:{},idempotentTime:{}", idempotentKey, idempotentTime);
        return this.execute(idempotentKey, idempotentTime, false);
    }

    /**
     * 幂等执行的入口
     *
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间,默认毫秒级别
     * @param throwsException 是否抛出异常
     * @return boolean
     */
    @Override
    public boolean execute(String idempotentKey, Long idempotentTime, boolean throwsException) throws IdempotentException {
        boolean idempotentTally = createIdempotentTally(idempotentKey, idempotentTime, TimeUnit.MILLISECONDS);
        if (!idempotentTally && throwsException) {
            throw new IdempotentException(IdempotentConstant.IDEMPOTENT_FAIL_MSG);
        }
        return idempotentTally;
    }

    /**
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @param timeUnit       时间策略,默认毫秒级别
     * @return boolean
     * @throws IdempotentException ex
     */
    @Override
    public boolean execute(String idempotentKey, Long idempotentTime, TimeUnit timeUnit) throws IdempotentException {
        return this.execute(idempotentKey, idempotentTime, timeUnit, false);
    }

    /**
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间
     * @param timeUnit        timeUnit
     * @param throwsException 是否抛出异常
     * @return boolean
     * @throws IdempotentException IdempotentException
     */
    @Override
    public boolean execute(String idempotentKey, Long idempotentTime, TimeUnit timeUnit, boolean throwsException) throws IdempotentException {
        boolean idempotentTally = createIdempotentTally(idempotentKey, idempotentTime, null == timeUnit ? TimeUnit.MILLISECONDS : timeUnit);
        if (!idempotentTally && throwsException) {
            throw new IdempotentException(IdempotentConstant.IDEMPOTENT_FAIL_MSG);
        }
        return idempotentTally;
    }
}
