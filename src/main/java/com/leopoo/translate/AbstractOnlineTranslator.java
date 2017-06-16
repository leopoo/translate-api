package com.leopoo.translate;

import com.leopoo.language.Detector;
import com.leopoo.language.DetectorFactory;
import com.leopoo.language.util.LangDetectException;
import com.leopoo.translate.enums.LANG;
import com.leopoo.translate.util.TranslationResult;
import com.leopoo.translate.enums.ResultState;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOnlineTranslator implements Translator {

    @Override
    final public TranslationResult translate(String query, LANG origin, LANG target) throws Exception {
        TranslationResult result = null;
        try {
            String response = getResponse(query, origin, target);
            result = parse(response);
            result.setOrigin(origin.getLanguage());
            result.setTarget(target.getLanguage());
            result.setSrc(query);
        } catch (Exception e) {
            result = new TranslationResult();
            result.setStatus(ResultState.FAILD.getStatus());
        }
        return result;
    }

    @Override
    final public TranslationResult translate(String query, LANG target) throws Exception {
        return translate(query, detect(query), target);
    }

    @Override
    final public TranslationResult translate(String query) throws Exception {
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

    abstract protected TranslationResult parse(String jsonString);
}
