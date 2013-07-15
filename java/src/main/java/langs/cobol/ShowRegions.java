package langs.cobol;

import langs.cobol.framework.Program;

public class ShowRegions extends Program {

    // LINKAGE SECTION.
    Regions regions;

    public ShowRegions using(Regions regions) {
        this.regions = regions;
        return this;
    }

    // PROCEDURE DIVISION.
    public void execute() {
        for (int i = 1;
                !(i > regions.regionsCount.intValue()); i += 1) {
            display(regions.regionCode(i),
                    ":", regions.regionName(i),
                    "(", regions.prefecturesCount(i), ")");
            for (int j = 1;
                    !(j > regions.prefecturesCount(i).intValue()); j += 1) {
                display("  ", regions.prefectureCode(i, j),
                        ":", regions.prefectureName(i, j));
            }
        }
    }

}
