package langs.cobol;

import langs.cobol.framework.File;
import langs.cobol.framework.GroupItem;
import langs.cobol.framework.Organization;
import langs.cobol.framework.Pic_N;
import langs.cobol.framework.Pic_X;
import langs.cobol.framework.Program;

/**
 * 都道府県の一覧を読み込みます。
 */
public class ReadPrefectures extends Program {

    // FILE SECTION.
    File prefecturesFile;
    final PrefectureRecord prefectureRecord = new PrefectureRecord();

    // WORKING-STORAGE SECTION.
    final Pic_X prefecturesStatus = new Pic_X(2);

    // LINKAGE SECTION.
    Pic_X prefecturesFilename;
    Regions regions;

    public ReadPrefectures using(
            Pic_X prefecturesFilename, Regions regions) {
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

    static class PrefectureRecord extends GroupItem<PrefectureRecord> {

        public final Pic_X prefectureCode = new Pic_X(2);
        public final Pic_N name           = new Pic_N(5);
        public final Pic_X regionCode     = new Pic_X(2);

        public void moveTo(Regions.Prefecture target) {
            prefectureCode.moveTo(target.prefectureCode);
            name.moveTo(target.prefectureName);
        }

        @Override
        public void readFrom(byte[] buf, int offset) {
            prefectureCode.readFrom(buf, offset);
            name.readFrom(buf, offset + 2);
            regionCode.readFrom(buf, offset + 12);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {prefectureCode=" + prefectureCode
                    + ", name=" + name
                    + ", regionCode=" + regionCode
                    + '}';
        }

    }

}
