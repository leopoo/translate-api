import com.leopoo.translate.Translator;
import com.leopoo.translate.enums.Trans;
import com.leopoo.translate.util.Proxy;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.junit.Before;
import org.junit.Test;

import com.leopoo.translate.factory.TFactory;
import com.leopoo.translate.factory.TranslatorFactory;

import java.util.Arrays;
import java.util.List;

public class TranslatorTest {
    TFactory factory = null;

    @Before
    public void initTFactory() throws Exception {
        // 初始化翻译工厂
        factory = new TranslatorFactory();
    }


    @Test
    public void testGoogle() throws Exception {
        Translator translator = factory.get(Trans.Google);
        ((Proxy) translator).setProxy(new HttpHost("127.0.0.1", 1080));
        List<String> dst = translator.translate("中文翻译成英文").getDst();
        System.out.println(StringUtils.join(dst.toArray(), ","));
    }

    @Test
    public void testBaidu() throws Exception {
        Translator translator = factory.get(Trans.Baidu);
        List<String> dst = translator.translate("中文翻译成英文").getDst();
        System.out.println(StringUtils.join(dst.toArray(), ","));
    }

    @Test
    public void testYoudao() throws Exception {
        Translator translator = factory.get(Trans.Youdao);
        List<String> dst = translator.translate("中文翻译成英文\n英文翻译成中文").getDst();
        System.out.println(StringUtils.join(dst.toArray(), ","));
    }
}
