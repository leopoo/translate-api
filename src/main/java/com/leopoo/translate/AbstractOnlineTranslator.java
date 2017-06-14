package com.leopoo.translate;

import com.leopoo.language.Detector;
import com.leopoo.language.DetectorFactory;
import com.leopoo.language.util.LangDetectException;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOnlineTranslator implements Translator {
    protected Map<LANG, String> langMap = new HashMap<>(); // 语言映射，由子类完成

    @Override
    final public String translate(String query, LANG origin, LANG target) throws Exception {
        String response = "";
        try {

            response = getResponse(query, origin, target);
            String result = parseString(response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }

    @Override
    final public String translate(String query, LANG target) throws Exception {
        return translate(query, detect(query), target);
    }

    @Override
    final public String translate(String query) throws Exception {
        LANG origin = detect(query);
        LANG target = LANG.ZH;
        if (origin.equals(LANG.ZH)) { // 如果是中文 翻译成英文
            target = LANG.EN;
        }
        return translate(query, origin, target);
    }

    private LANG detect(String query) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(query);
        String language = detector.detect();
        return LANG.getLang(language);
    }

    abstract protected String getResponse(String query, LANG origin, LANG target) throws Exception;

    abstract protected String parseString(String jsonString);
}
