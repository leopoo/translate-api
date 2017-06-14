import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.leopoo.language.Detector;
import com.leopoo.language.DetectorFactory;
import com.leopoo.language.util.LangDetectException;

/**
 * @author zbb
 * @create 2017-06-14 15:12
 */
public class DetectorTest {


    @Before
    public void initData() {

        try {
            File f = new File(this.getClass().getClassLoader().getResource("lang/data").getFile());
            DetectorFactory.loadProfile(f);
        } catch (LangDetectException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void detectorTest() {
        try {
            Detector detector =  DetectorFactory.create();
            detector.append("中文感觉啊可根据ask感觉啦时间");
            System.out.println(detector.detect());
        } catch (LangDetectException e) {
            e.printStackTrace();
        }

    }
}
