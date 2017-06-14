package com.leopoo.translate.factory;

import com.leopoo.translate.Trans;
import com.leopoo.translate.Translator;

public interface TFactory {
    Translator get(Trans id);
}
