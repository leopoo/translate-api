# translate-api 
forked from lsj9383/translate-set 改造成maven项目,增加文字语言识别,网络代理

支持的翻译来源:
* [谷歌翻译](http://translate.google.com/)
* [百度翻译](http://fanyi.baidu.com/)
* [有道翻译](http://fanyi.youdao.com/)

以后增加相关api翻译

##  文字语言检测
```java
//参考 https://github.com/shuyo/language-detection
public class DetectorTest {
    @Before
    public void initData() {
        try {
            // 加载语料文件
            File f = new File(this.getClass().getClassLoader().getResource("lang/data").getFile());
            DetectorFactory.loadProfile(f);
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void detectorTest() {
        try {
            Detector detector1 = DetectorFactory.create();
            detector1.append("中文识别混加英文abc");
            System.out.println(detector1.detect());
            Detector detector2 = DetectorFactory.create();
            detector2.append("English Identification 混加中文");
            System.out.println(detector2.detect());
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
    }
}
```
## 翻译
```java
public class TranslatorTest {
    TFactory factory = null;

    @Before
    public void initTFactory() throws Exception {
        // 初始化翻译工厂
        factory = new TranslatorFactory();
    }

    @Test
    public void doTranslate() throws Exception {
        System.out.println(factory.get(Trans.Baidu).translate("中文翻译成英文"));
        System.out.println(factory.get(Trans.Google).translate("中文翻译成英文"));
        System.out.println(factory.get(Trans.Youdao).translate("中文翻译成英文"));
        System.out.println(factory.get(Trans.Baidu).translate("English translation into Chinese"));
        System.out.println(factory.get(Trans.Google).translate("English translation into Chinese"));
        System.out.println(factory.get(Trans.Youdao).translate("English translation into Chinese"));
    }
}
```

## 谷歌翻译tk JavaScript算法
```javascript
//参考 https://github.com/Stichoza/google-translate-php/issues/32
var TKK = ((function() {
    var a = 561666268;
    var b = 1526272306;
    return 406398 + '.' + (a + b);
})());

function b(a, b) {
    for (var d = 0; d < b.length - 2; d += 3) {
        var c = b.charAt(d + 2),
            c = "a" <= c ? c.charCodeAt(0) - 87 : Number(c),
            c = "+" == b.charAt(d + 1) ? a >>> c : a << c;
        a = "+" == b.charAt(d) ? a + c & 4294967295 : a ^ c
    }
    return a
}

function tk(a) {
    for (var e = TKK.split("."), h = Number(e[0]) || 0, g = [], d = 0, f = 0; f < a.length; f++) {
        var c = a.charCodeAt(f);
        128 > c ? g[d++] = c : (2048 > c ? g[d++] = c >> 6 | 192 : (55296 == (c & 64512) && f + 1 < a.length && 56320 == (a.charCodeAt(f + 1) & 64512) ? (c = 65536 + ((c & 1023) << 10) + (a.charCodeAt(++f) & 1023), g[d++] = c >> 18 | 240, g[d++] = c >> 12 & 63 | 128) : g[d++] = c >> 12 | 224, g[d++] = c >> 6 & 63 | 128), g[d++] = c & 63 | 128)
    }
    a = h;
    for (d = 0; d < g.length; d++) a += g[d], a = b(a, "+-a^+6");
    a = b(a, "+-3^+b+-f");
    a ^= Number(e[1]) || 0;
    0 > a && (a = (a & 2147483647) + 2147483648);
    a %= 1E6;
    return a.toString() + "." + (a ^ h)
}
```