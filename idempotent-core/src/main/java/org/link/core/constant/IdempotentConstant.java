package org.link.core.constant;

/**
 * 幂等常量
 * @author gol
 */
public interface IdempotentConstant {

    String IDEMPOTENT_KEY = "idempotent_key";

    String IDEMPOTENT_SPLIT = ":";

    String DEFAULT_REDIS_LINK = "redis://";

    String IDEMPOTENT_FAIL_MSG = "Repeat Request !";
}
