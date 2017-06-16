package com.leopoo.translate;

import com.leopoo.translate.enums.LANG;
import com.leopoo.translate.util.TranslationResult;

public interface Translator {

    /**
     * 
     * @param query 待翻译的字符串
     * @param origin 源语言
     * @param target 目标语言
     * @return 翻译结果
     * @throws Exception
     */
    public TranslationResult translate(String query, LANG origin, LANG target) throws Exception;

    /**
     * 将翻译字符串翻译成目标语言 ，会自动识别翻译字符串，如果识别不了默认为英文
     *
     * @param query 翻译字符串
     * @param target 目标语言
     * @return 翻译结果
     * @throws Exception
     */
    public TranslationResult translate(String query, LANG target) throws Exception;

    /**
     * 将翻译字符串翻译成中文 ，会自动识别翻译字符串，如果识别不了默认为英文
     * 
     * @param query 翻译字符串
     * @return 翻译结果
     * @throws Exception
     */
    public TranslationResult translate(String query) throws Exception;

}
