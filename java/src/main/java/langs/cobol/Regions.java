package langs.cobol;

import langs.cobol.framework.Pic_9;
import langs.cobol.framework.Pic_N;
import langs.cobol.framework.Pic_X;
import langs.cobol.framework.Table;

/**
 * 地方。
 */
public class Regions {

    public final Pic_9 regionsCount = new Pic_9(2);

    public final Table<Region> region =
            Table.create(new Region[10]);

    public Region region(int i) {
        return region.get(i);
    }

    public Pic_9 prefecturesCount(int i) {
        return region(i).prefecturesCount;
    }

    public Prefecture prefecture(int i, int j) {
        return region(i).prefecture(j);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {regionsCount=" + regionsCount
                + ", region=" + region
                + '}';
    }

    public static class Region {

        public final Pic_X regionCode       = new Pic_X(2);
        public final Pic_N regionName       = new Pic_N(4);
        public final Pic_9 prefecturesCount = new Pic_9(2);

        public final Table<Prefecture> prefecture =
                Table.create(new Prefecture[10]);

        public Prefecture prefecture(int j) {
            return prefecture(j);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {regionCode=" + regionCode
                    + ", regionName=" + regionName
                    + ", prefecturesCount=" + prefecturesCount
                    + ", prefecture=" + prefecture
                    + '}';
        }

    }

    public static class Prefecture {

        public final Pic_X prefectureCode = new Pic_X(2);
        public final Pic_N prefectureName = new Pic_N(5);

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {prefectureCode=" + prefectureCode
                    + ", prefectureName=" + prefectureName
                    + '}';
        }

    }

}
