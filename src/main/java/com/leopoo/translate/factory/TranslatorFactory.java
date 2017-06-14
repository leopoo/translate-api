package com.leopoo.translate.factory;

import java.net.URISyntaxException;

import com.leopoo.language.util.LangDetectException;
import com.leopoo.translate.Trans;
import com.leopoo.translate.Translator;
import com.leopoo.translate.exception.DupIdException;

final public class TranslatorFactory extends AbstractTranslatorFactory {

    public TranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            DupIdException, URISyntaxException, LangDetectException {
        super();
    }

    @Override
    public Translator get(Trans id) {
        return translatorMap.get(id);
    }

}
