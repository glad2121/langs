package langs.cobol;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import langs.cobol.framework.PicX;

public class ReadPrefecturesTest {

    ReadPrefectures program;

    Regions regions;

    @Before
    public void setUp() throws Exception {
        program = new ReadPrefectures();
        regions = new Regions();
        regions.addRegion("01", "北海道");
        regions.addRegion("02", "東北");
        regions.addRegion("03", "関東");
        regions.addRegion("04", "中部");
        regions.addRegion("05", "近畿");
        regions.addRegion("06", "中国");
        regions.addRegion("07", "四国");
        regions.addRegion("08", "九州");
    }

    @After
    public void tearDown() throws Exception {
        regions = null;
        program = null;
    }

    @Test
    public void testExecute() {
        PicX filename = PicX.of("../data/PrefectureRegions.txt");
        program.using(filename, regions).execute();
        assertThat(regions.prefecturesCount(1).intValue()).isEqualTo(1);
        assertThat(regions.prefectureCode(1, 1).value()).isEqualTo("01");
        assertThat(regions.prefecturesCount(2).intValue()).isEqualTo(6);
        assertThat(regions.prefectureName(2, 1).value()).isEqualTo("青森県　　");
        assertThat(regions.prefecturesCount(8).intValue()).isEqualTo(8);
        assertThat(regions.prefectureName(8, 8).value()).isEqualTo("沖縄県　　");
    }

}
