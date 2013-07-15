package langs.cobol;

import langs.cobol.framework.File;
import langs.cobol.framework.GroupItem;
import langs.cobol.framework.Organization;
import langs.cobol.framework.Pic9;
import langs.cobol.framework.PicN;
import langs.cobol.framework.PicX;
import langs.cobol.framework.Program;

/**
 * 地方の一覧を読み込みます。
 */
public class ReadRegions extends Program {

    // FILE SECTION.
    File regionsFile;
    final RegionRecord regionRecord = new RegionRecord();

    // WORKING-STORAGE SECTION.
    final PicX regionsStatus = new PicX(2);

    // LINKAGE SECTION.
    PicX regionsFilename;
    Regions regions;

    public ReadRegions using(PicX regionsFilename, Regions regions) {
        this.regionsFilename = regionsFilename;
        this.regions = regions;
        // FILE-CONTROL.
        this.regionsFile = new File(regionRecord)
                .assignTo(regionsFilename)
                .orginizationIs(Organization.LINE_SEQUENTIAL)
                .fileStatusIs(regionsStatus);
        return this;
    }

    // PROCEDURE DIVISION.
    public void execute() {
        regionsFile.openInput();
        try {
            int i = 1;
            while (regionsStatus.eq(File.SUCCESS)) {
                regionsFile.read();
                if (!regionsFile.atEnd()) {
                    regions.regionsCount.add(1);
                    regionRecord.moveTo(regions.region(i));
                    Pic9.of(0).moveTo(regions.prefecturesCount(i));
                    i += 1;
                }
            }
        } finally {
            regionsFile.close();
        }
    }

    static class RegionRecord extends GroupItem<RegionRecord> {

        public PicX regionCode = new PicX(2);
        public PicN name       = new PicN(4);

        public void moveTo(Regions.Region region) {
            regionCode.moveTo(region.regionCode);
            name.moveTo(region.regionName);
        }

        @Override
        public void readFrom(byte[] buf, int offset) {
            regionCode.readFrom(buf, offset);
            name.readFrom(buf, offset + 2);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {regionCode=" + regionCode
                    + ", name=" + name
                    + '}';
        }

    }

}
