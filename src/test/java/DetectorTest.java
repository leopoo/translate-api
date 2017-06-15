import java.io.File;

import org.junit.Before;
import org.junit.Test;
import com.leopoo.language.Detector;
import com.leopoo.language.DetectorFactory;
import com.leopoo.language.util.LangDetectException;

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
