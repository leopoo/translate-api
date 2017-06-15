package com.leopoo.translate.util;

import java.io.Serializable;

/**
 * @author zbb
 * @create 2017-06-15 14:44
 */
public class ResponseTranslation implements Serializable {

    private static final long serialVersionUID = -2041212371635278542L;

    /**
     * 查询状态
     */
    private int status;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误码
     */
    private String errorMessage;

    /**
     * 源语言
     */
    private String origin;

    /**
     * 源语言
     */
    private String target;

    /**
     * 原文
     */
    private String src;

    /**
     * 译文
     */
    private String dst;

    /**
     * 原文发音 URL
     */
    private String srcUrl;

    /**
     * 译文发音 URL
     */
    private String dstUrl;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getDstUrl() {
        return dstUrl;
    }

    public void setDstUrl(String dstUrl) {
        this.dstUrl = dstUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
