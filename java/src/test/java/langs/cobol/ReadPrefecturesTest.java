package langs.cobol;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import langs.cobol.framework.PicX;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertThat(regions.prefecturesCount(1).intValue(), is(1));
        assertThat(regions.prefectureCode(1, 1).value(), is("01"));
        assertThat(regions.prefecturesCount(2).intValue(), is(6));
        assertThat(regions.prefectureName(2, 1).value(), is("青森県　　"));
        assertThat(regions.prefecturesCount(8).intValue(), is(8));
        assertThat(regions.prefectureName(8, 8).value(), is("沖縄県　　"));
    }

}
