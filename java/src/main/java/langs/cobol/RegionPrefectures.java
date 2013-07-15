package langs.cobol;

import langs.cobol.framework.Pic9;
import langs.cobol.framework.PicX;
import langs.cobol.framework.Program;

/**
 * 地方に属する都道府県の数を表示します。
 */
public class RegionPrefectures extends Program {

    final String[] args;

    // WORKING-STORAGE SECTION.
    final Pic9 argc                = new Pic9(2);
    final PicX dataDir             = new PicX(80);
    final PicX regionsFilename     = new PicX(80);
    final PicX prefecturesFilename = new PicX(80);
    final Pic9 n                   = new Pic9(2);

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
            PicX.of("../data").moveTo(dataDir);
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
        PicX s;

        public Strlen using(PicX s) {
            this.s = s;
            return this;
        }

        // PROCEDURE DIVISION.
        public void execute() {
            n.set(s.length());
        }

    }

}
