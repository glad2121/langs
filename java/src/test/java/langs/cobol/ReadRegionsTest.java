package langs.cobol;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import langs.cobol.framework.Pic_X;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Pic_X filename = Pic_X.of("../data/Regions.txt");
        Regions regions = new Regions();
        program.using(filename, regions).execute();
        assertThat(regions.regionsCount.intValue(), is(8));
    }

}
