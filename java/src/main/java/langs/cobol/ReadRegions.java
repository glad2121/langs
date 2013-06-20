package langs.cobol;

import langs.cobol.framework.Pic_X;
import langs.cobol.framework.Program;

/**
 * 地方の一覧を読み込みます。
 */
public class ReadRegions extends Program {

    // FILE SECTION.
    final RegionRecord regionRecord = new RegionRecord();

    // WORKING-STORAGE SECTION.
    final Pic_X regionsStatus = new Pic_X(2);

    // LINKAGE SECTION.
    Pic_X regionsFilename;
    Regions regions;

    public void execute(Pic_X regionFilename, Regions regions) {
        using(regionFilename, regions);
        
        // TODO Auto-generated method stub
        
    }

    void using(Pic_X regionFilename, Regions regions) {
        this.regionsFilename = regionFilename;
        this.regions = regions;
    }

    public static class RegionRecord {

        public String regionCode;
        public String name;

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {regionCode=" + regionCode
                    + ", name=" + name
                    + '}';
        }

    }

}
