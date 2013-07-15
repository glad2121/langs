package langs.cobol;

import langs.cobol.framework.Pic_9;
import langs.cobol.framework.Pic_X;
import langs.cobol.framework.Program;

/**
 * 地方に属する都道府県の数を表示します。
 */
public class RegionPrefectures extends Program {

    final String[] args;

    // WORKING-STORAGE SECTION.
    final Pic_9 argc                = new Pic_9(2);
    final Pic_X dataDir             = new Pic_X(80);
    final Pic_X regionsFilename     = new Pic_X(80);
    final Pic_X prefecturesFilename = new Pic_X(80);
    final Pic_9 n                   = new Pic_9(2);

    /**
     * 地方の一覧。
     */
    final Regions regions = new Regions();

    final Strlen strlen = new Strlen();
    final ReadRegions readRegions = new ReadRegions();
    final ReadPrefectures readPrefectures = new ReadPrefectures();
    final ShowRegions showRegions = new ShowRegions();

    public static void main(String[] args) {
        new RegionPrefectures(args).execute();
    }

    public RegionPrefectures(String[] args) {
        this.args = args;
    }

    // PROCEDURE DIVISION.
    public void execute() {
        logger.debug("ACCEPT FILENAMES");
        acceptFilenames();
        logger.debug("INITIALIZE REGIONS");
        initialize(regions);
        logger.debug("CALL READ-REGIONS");
        call(readRegions.using(regionsFilename, regions));
        logger.debug("CALL READ-PREFECTURES");
        call(readPrefectures.using(prefecturesFilename, regions));
        logger.debug("CALL SHOW-REGIONS");
        call(showRegions.using(regions));
    }

    /**
     * コマンドライン引数からファイル名を受け取ります。
     */
    void acceptFilenames() {
        argc.set(args.length);
        if (argc.eq(0)) {
            Pic_X.of("../data").moveTo(dataDir);
        } else {
            dataDir.set(args[0]);
        }
        call(strlen.using(dataDir));
        regionsFilename.set(dataDir + "/Regions.txt");
        prefecturesFilename.set(dataDir + "/PrefectureRegions.txt");
    }

    /**
     * 文字列の長さを求めます。
     */
    class Strlen extends Program {

        // LINKAGE SECTION.
        Pic_X s;

        public Strlen using(Pic_X s) {
            this.s = s;
            return this;
        }

        // PROCEDURE DIVISION.
        public void execute() {
            n.set(s.length());
        }

    }

}
