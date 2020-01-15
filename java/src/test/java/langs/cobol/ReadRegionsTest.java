package langs.cobol;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import langs.cobol.framework.PicX;

public class ReadRegionsTest {

    ReadRegions program;

    @Before
    public void setUp() throws Exception {
        program = new ReadRegions();
    }

    @After
    public void tearDown() throws Exception {
        program = null;
    }

    @Test
    public void testExecute() {
        PicX filename = PicX.of("../data/Regions.txt");
        Regions regions = new Regions();
        program.using(filename, regions).execute();
        assertThat(regions.regionsCount.intValue()).isEqualTo(8);
    }

}
