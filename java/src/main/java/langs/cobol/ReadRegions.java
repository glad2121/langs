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

    static class RegionRecord extends GroupItem<RegionRecord> {
        final PicX regionCode = new PicX(2);
        final PicN name       = new PicN(4);
    }

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

}
