package langs.cobol;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        assertThat(regions.regionsCount.intValue(), is(1));
        assertThat(regions.region(1).regionCode.value(), is("01"));
        assertThat(regions.region(1).regionName.value(), is("北海道"));
    }

}
