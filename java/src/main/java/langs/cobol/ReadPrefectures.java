package langs.cobol;

import langs.cobol.framework.File;
import langs.cobol.framework.Pic_N;
import langs.cobol.framework.Pic_X;
import langs.cobol.framework.Program;

/**
 * 都道府県の一覧を読み込みます。
 */
public class ReadPrefectures extends Program {

    // FILE SECTION.
    final PrefectureRecord prefectureRecord = new PrefectureRecord();

    // WORKING-STORAGE SECTION.
    final Pic_X prefecturesStatus = new Pic_X(2);

    // LINKAGE SECTION.
    Pic_X prefecturesFilename;
    Regions regions;

    public void execute(Pic_X prefecturesFilename, Regions regions) {
        using(prefecturesFilename, regions);
        File prefecturesFile = new PrefecturesFile(
                prefectureRecord,
                prefecturesFilename.get(),
                File.Organization.LINE_SEQUENTIAL,
                prefecturesStatus);
        prefecturesFile.openInput();
        try {
            while (prefecturesStatus.equalTo(File.SUCCESS)) {
                prefecturesFile.read();
                if (!prefecturesFile.atEnd()) {
                    addPrefecture();
                }
            }
        } finally {
            prefecturesFile.close();
        }
    }

    void using(Pic_X prefecturesFilename, Regions regions) {
        this.prefecturesFilename = prefecturesFilename;
        this.regions = regions;
    }

    /**
     * 地方に都道府県を追加します。
     */
    void addPrefecture() {
        int i = 1;
        for (;;) {
            if (regions.region.atEnd(i)) {
                display("REGION NOT-FOUND");
            } else if (prefectureRecord.regionCode.equalTo(
                    regions.region(i).regionCode)) {
                regions.prefecturesCount(i).add(1);
                int j = regions.prefecturesCount(i).getInt();
                prefectureRecord.moveTo(regions.prefecture(i, j));
            }
        }
    }

    public static class PrefecturesFile extends File {

        private PrefectureRecord record;

        public PrefecturesFile(
                PrefectureRecord record,
                String assignTo,
                Organization organization,
                Pic_X status) {
            super(assignTo, organization, status);
            this.record = record;
        }

        @Override
        public void read() {
            record.read(readLine());
        }

    }

    public static class PrefectureRecord {

        public final Pic_X prefectureCode = new Pic_X(2);
        public final Pic_N name           = new Pic_N(5);
        public final Pic_X regionCode     = new Pic_X(2);

        public void read(String line) {
            prefectureCode.set(line, 0);
            name.set(line, 2);
            regionCode.set(line, 7);
        }

        public void moveTo(Regions.Prefecture target) {
            prefectureCode.moveTo(target.prefectureCode);
            name.moveTo(target.prefectureName);
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
