package langs.cobol;

import langs.cobol.framework.File;
import langs.cobol.framework.GroupItem;
import langs.cobol.framework.Organization;
import langs.cobol.framework.PicN;
import langs.cobol.framework.PicX;
import langs.cobol.framework.Program;

/**
 * 都道府県の一覧を読み込みます。
 */
public class ReadPrefectures extends Program {

    // FILE SECTION.
    File prefecturesFile;
    final PrefectureRecord prefectureRecord = new PrefectureRecord();

    static class PrefectureRecord extends GroupItem<PrefectureRecord> {
        final PicX prefectureCode = new PicX(2);
        final PicN name           = new PicN(5);
        final PicX regionCode     = new PicX(2);
    }

    // WORKING-STORAGE SECTION.
    final PicX prefecturesStatus = new PicX(2);

    // LINKAGE SECTION.
    PicX prefecturesFilename;
    Regions regions;

    public ReadPrefectures using(
            PicX prefecturesFilename, Regions regions) {
        this.prefecturesFilename = prefecturesFilename;
        this.regions = regions;
        // FILE-CONTROL.
        this.prefecturesFile = new File(prefectureRecord)
                .assignTo(prefecturesFilename)
                .orginizationIs(Organization.LINE_SEQUENTIAL)
                .fileStatusIs(prefecturesStatus);
        return this;
    }

    // PROCEDURE DIVISION.
    public void execute() {
        prefecturesFile.openInput();
        try {
            while (prefecturesStatus.eq(File.SUCCESS)) {
                prefecturesFile.read();
                if (!prefecturesFile.atEnd()) {
                    addPrefecture();
                }
            }
        } finally {
            prefecturesFile.close();
        }
    }

    /**
     * 地方に都道府県を追加します。
     */
    void addPrefecture() {
        int i = 1;
        for (;;++i) {
            if (regions.region.atEnd(i)) {
                display("REGION NOT-FOUND");
                break;
            }
            if (prefectureRecord.regionCode.eq(
                    regions.region(i).regionCode)) {
                regions.prefecturesCount(i).add(1);
                int j = regions.prefecturesCount(i).intValue();
                prefectureRecord.moveTo(regions.prefecture(i, j));
                break;
            }
        }
    }

}
