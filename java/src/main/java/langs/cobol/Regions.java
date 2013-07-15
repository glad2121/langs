package langs.cobol;

import langs.cobol.framework.GroupItem;
import langs.cobol.framework.Pic9;
import langs.cobol.framework.PicN;
import langs.cobol.framework.PicX;
import langs.cobol.framework.Table;

/**
 * 地方。
 */
public class Regions extends GroupItem<Regions> {

    public final Pic9 regionsCount = new Pic9(2);

    public final Table<Region> region =
            Table.of(Region.class, 10);

    public Region region(int i) {
        return region.get(i);
    }

    public PicX regionCode(int i) {
        return region(i).regionCode;
    }

    public PicN regionName(int i) {
        return region(i).regionName;
    }

    void addRegion(String code, String name) {
        region(regionsCount.add(1).intValue()).set(code, name);
    }

    public Pic9 prefecturesCount(int i) {
        return region(i).prefecturesCount;
    }

    public Prefecture prefecture(int i, int j) {
        return region(i).prefecture(j);
    }

    public PicX prefectureCode(int i, int j) {
        return prefecture(i, j).prefectureCode;
    }

    public PicN prefectureName(int i, int j) {
        return prefecture(i, j).prefectureName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {regionsCount=" + regionsCount
                + ", region=" + region
                + '}';
    }

    public static class Region {

        public final PicX regionCode       = new PicX(2);
        public final PicN regionName       = new PicN(4);
        public final Pic9 prefecturesCount = new Pic9(2);

        public final Table<Prefecture> prefecture =
                Table.of(Prefecture.class, 10);

        void set(String code, String name) {
            this.regionCode.set(code);
            this.regionName.set(name);
        }

        public Prefecture prefecture(int j) {
            return prefecture.get(j);
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

        public final PicX prefectureCode = new PicX(2);
        public final PicN prefectureName = new PicN(5);

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + " {prefectureCode=" + prefectureCode
                    + ", prefectureName=" + prefectureName
                    + '}';
        }

    }

}
