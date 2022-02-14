package org.link.core.exception;

/**
 * 幂等异常类
 *
 * @author gol
 */
public class IdempotentException extends RuntimeException {

    private int code;

    /**
     * @param msg 异常信息
     */
    public IdempotentException(String msg) {
        super(msg);
    }

    /**
     * @param msg  异常信息
     * @param code 异常码
     */
    public IdempotentException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
