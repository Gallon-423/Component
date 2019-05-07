package com.xiaojinzi.component.error.ignore;

/**
 * 表示 Activity result 异常
 *
 * time   : 2018/11/03
 *
 * @author : xiaojinzi 30212
 */
public class ActivityResultException extends RuntimeException {

    public ActivityResultException() {
    }

    public ActivityResultException(String message) {
        super(message);
    }

    public ActivityResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityResultException(Throwable cause) {
        super(cause);
    }

}
