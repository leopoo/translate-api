package com.leopoo.language.util;

/**
 * @author Nakatani Shuyo
 *
 */
public class LangDetectException extends Exception {
    private static final long serialVersionUID = 1L;
    private ErrorCode code;
    

    /**
     * @param code
     * @param message
     */
    public LangDetectException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @return the error code
     */
    public ErrorCode getCode() {
        return code;
    }
}
