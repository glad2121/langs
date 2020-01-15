package langs.cobol;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegionsTest {

    Regions regions;

    @Before
    public void setUp() throws Exception {
        regions = new Regions();
    }

    @After
    public void tearDown() throws Exception {
        regions = null;
    }

    @Test
    public void testAddRegion() {
        regions.addRegion("01", "北海道");
        assertThat(regions.regionsCount.intValue()).isEqualTo(1);
        assertThat(regions.region(1).regionCode.value()).isEqualTo("01");
        assertThat(regions.region(1).regionName.value()).isEqualTo("北海道");
    }

}
