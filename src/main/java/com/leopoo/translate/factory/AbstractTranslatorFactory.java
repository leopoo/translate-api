package com.leopoo.translate.factory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leopoo.language.Detector;
import com.leopoo.language.DetectorFactory;
import com.leopoo.language.util.LangDetectException;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.Trans;
import com.leopoo.translate.Translator;
import com.leopoo.translate.annotation.TranslatorComponent;
import com.leopoo.translate.exception.DupIdException;
import com.leopoo.translate.impl.BaiduTranslator;
import com.leopoo.translate.impl.GoogleTranslator;
import com.leopoo.translate.impl.YoudaoTranslator;

public abstract class AbstractTranslatorFactory implements TFactory {

    protected Map<Trans, Translator> translatorMap = new HashMap<>();

    private List<String> workPackages = new ArrayList<>();

    private final String profilePath = "lang/data";

    private List<Class<? extends AbstractOnlineTranslator>> translatorClasses = Arrays.asList(BaiduTranslator.class,
            GoogleTranslator.class, YoudaoTranslator.class);

    public AbstractTranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            DupIdException, URISyntaxException, LangDetectException {
        File f = new File(this.getClass().getClassLoader().getResource(profilePath).getFile());

        DetectorFactory.loadProfile(f); //加载语料

        // Detector detector = DetectorFactory.create();

        initSystemTranslator();
        initUserTranslator();
    }

    public AbstractTranslatorFactory(String[] workPackages) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, DupIdException, URISyntaxException, LangDetectException {
        this();
    }

    public AbstractTranslatorFactory(String workPackage) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, DupIdException, URISyntaxException, LangDetectException {
        this(new String[] { workPackage });
    }

    private void initSystemTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            DupIdException, URISyntaxException {
        for (Class<?> translatorClass : translatorClasses) {
            TranslatorComponent component = translatorClass.getAnnotation(TranslatorComponent.class);
            if (component != null) {
                Trans id = component.id();
                if (translatorMap.containsKey(id)) {
                    throw new DupIdException("Id duplication exception");
                } else {
                    translatorMap.put(id, (Translator) translatorClass.newInstance());
                }
            }
        }
    }

    private void initUserTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            DupIdException, URISyntaxException {
        for (String workPackage : workPackages) {
            List<String> workClassesName = getClassNameByPackage(workPackage);
            for (String workClassName : workClassesName) {
                Class<?> workClass = Class.forName(workClassName);
                TranslatorComponent component = workClass.getAnnotation(TranslatorComponent.class);
                if (component != null) {
                    Trans id = component.id();
                    if (translatorMap.containsKey(id)) {
                        throw new DupIdException("Id duplication exception");
                    } else {
                        translatorMap.put(id, (Translator) workClass.newInstance());
                    }
                }
            }
        }
    }

    private List<String> getClassNameByPackage(String packageName) throws URISyntaxException {
        List<String> classesName = new ArrayList<>();
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource(packageName.replace(".", "/"));

        File packageDir = new File(new URI(url.getPath()).getPath());
        for (File classFile : packageDir.listFiles()) {
            String classNickName = classFile.getName();
            classNickName = classNickName.substring(0, classNickName.indexOf('.'));
            classesName.add(packageName + "." + classNickName);
        }
        return classesName;
    }
}
