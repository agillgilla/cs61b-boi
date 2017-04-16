/**
 * Created by Arjun on 4/11/2017.
 */
public class Tile {

    private String imgPath;
    private double ulLon;
    private double ulLat;
    private double lrLon;
    private double lrLat;

    private double lonDPP;

    public Tile(String imgPath, double ulLon, double ulLat, double lrLon, double lrLat) {
        this.imgPath = imgPath;
        this.lrLon = lrLon;
        this.ulLon = ulLon;
        this.ulLat = ulLat;
        this.lrLat = lrLat;
        this.lonDPP = (lrLon - ulLon) / 256;
    }
    
    public Tile(Tile parent, int direction) {
        switch (direction) {
            case 1: // NW
                this.imgPath = parent.getImgPath() + direction;
                this.ulLon = parent.getUlLon();
                this.ulLat = parent.getUlLat();
                this.lrLon = parent.getUlLon() + (parent.getLrLon() - parent.getUlLon()) / 2;
                this.lrLat = parent.getUlLat() - (parent.getUlLat() - parent.getLrLat()) / 2;
                break;
            case 2: // NE
                this.imgPath = parent.getImgPath() + direction;
                this.ulLon = parent.getUlLon() + (parent.getLrLon() - parent.getUlLon()) / 2;
                this.ulLat = parent.getUlLat();
                this.lrLon = parent.getLrLon();
                this.lrLat = parent.getUlLat() - (parent.getUlLat() - parent.getLrLat()) / 2;
                break;
            case 3: // SW
                this.imgPath = parent.getImgPath() + direction;
                this.ulLon = parent.getUlLon();
                this.ulLat = parent.getUlLat() - (parent.getUlLat() - parent.getLrLat()) / 2;
                this.lrLon = parent.getUlLon() + (parent.getLrLon() - parent.getUlLon()) / 2;
                this.lrLat = parent.getLrLat();
                break;
            case 4: // SE
                this.imgPath = parent.getImgPath() + direction;
                this.ulLon = parent.getUlLon() + (parent.getLrLon() - parent.getUlLon()) / 2;
                this.ulLat = parent.getUlLat() - (parent.getUlLat() - parent.getLrLat()) / 2;
                this.lrLon = parent.getLrLon();
                this.lrLat = parent.getLrLat();
                break;
            default:
                throw new RuntimeException("ERROR: UNKNOWN DIRECTION TYPE: " + direction);
        }
        this.lonDPP = Math.abs(this.ulLon - this.lrLon) / 256;
        this.imgPath = this.imgPath.replaceAll("[^0-9.]", "");
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getImgPathActual() {
        return Rasterer.imgRoot + this.imgPath + ".png";
    }

    public double getLrLon() {
        return lrLon;
    }

    public double getUlLon() {
        return ulLon;
    }

    public double getUlLat() {
        return ulLat;
    }

    public double getLrLat() {
        return lrLat;
    }

    public boolean intersects(double ulLonQuery,
                              double ulLatQuery,
                              double lrLonQuery,
                              double lrLatQuery) {
        /*System.out.println("INTERSECTION (" + this.getImgPathActual() + "):");
        System.out.println("(" + this.lrLon + " >= " + ulLonQuery + " && " + this.lrLon + " <= " + lrLonQuery + " ||");
        System.out.println(this.ulLon + " >= " + ulLonQuery + " && " + this.ulLon + " <= " + lrLonQuery + ") &&");
        System.out.println("(" + this.lrLat + " >= " + lrLatQuery + " && " + this.lrLat + " <= " + ulLatQuery + " ||");
        System.out.println(this.ulLat + " >= " + lrLatQuery + " && " + this.ulLat + " <= " + ulLatQuery + ")");*/
        return (((this.lrLon >= ulLonQuery && this.lrLon <= lrLonQuery)
                        || (this.ulLon >= ulLonQuery && this.ulLon <= lrLonQuery)
                        || (this.ulLon <= ulLonQuery && this.lrLon >= lrLonQuery))
                    && ((this.lrLat >= lrLatQuery && this.lrLat <= ulLatQuery)
                        || (this.ulLat >= lrLatQuery && this.ulLat <= ulLatQuery)
                        || (this.ulLat >= ulLatQuery && this.lrLat <= lrLatQuery)));
                //|| this.flippedIntersects(ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery);
    }

    public boolean flippedIntersects(double ulLonQuery,
                              double ulLatQuery,
                              double lrLonQuery,
                              double lrLatQuery) {
        /*System.out.println("FLIPPED INTERSECTION (" + this.getImgPathActual() + "):");
        System.out.println("(" + lrLonQuery + " >= " + this.ulLon + " && " + lrLonQuery + " <= " + this.lrLon + " ||");
        System.out.println(ulLonQuery + " >= " + this.ulLon + " && " + ulLonQuery + " <= " + this.lrLon + ") &&");
        System.out.println("(" + lrLatQuery + " >= " + this.lrLat + " && " + lrLatQuery + " <= " + this.ulLat + " ||");
        System.out.println(ulLatQuery + " >= " + this.lrLat + " && " + ulLatQuery + " <= " + this.ulLat + ")");*/
        return (((lrLonQuery >= this.ulLon && lrLonQuery <= this.lrLon)
                || (ulLonQuery >= this.ulLon && ulLonQuery <= this.lrLon))
                && ((lrLatQuery >= this.lrLat && lrLatQuery <= this.ulLat)
                || (ulLatQuery >= this.lrLat && ulLatQuery <= this.ulLat)));
    }

    public double lonDPP() {
        return this.lonDPP;
    }

    public boolean attrLessThan(Tile other, String attr) {
        if (attr.equals("lon")) {
            return this.ulLon < other.ulLon;
        } else if (attr.equals("lat")) {
            return this.ulLat < other.ulLat;
        } else {
            throw new RuntimeException("ERROR: Unkown attribute " + attr);
        }
    }

    public boolean attrGreaterThan(Tile other, String attr) {
        if (attr.equals("lon")) {
            return this.ulLon > other.ulLon;
        } else if (attr.equals("lat")) {
            return this.ulLat > other.ulLat;
        } else {
            throw new RuntimeException("ERROR: Unkown attribute " + attr);
        }
    }
}
