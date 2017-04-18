import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    // Recommended: QuadTree instance variable. You'll need to make
    //              your own QuadTree since there is no built-in quadtree in Java.
    TileQuadTree mainTree;
    static String imgRoot;
    ArrayDeque<Tile> render_list;


    /** imgRoot is the name of the directory containing the images.
     *  You may not actually need this for your class. */
    public Rasterer(String imgRoot) {
        this.imgRoot = imgRoot;
        this.mainTree = genQuadTree();
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     * </p>
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified:
     * "render_grid"   -> String[][], the files to display
     * "raster_ul_lon" -> Number, the bounding upper left longitude of the rastered image <br>
     * "raster_ul_lat" -> Number, the bounding upper left latitude of the rastered image <br>
     * "raster_lr_lon" -> Number, the bounding lower right longitude of the rastered image <br>
     * "raster_lr_lat" -> Number, the bounding lower right latitude of the rastered image <br>
     * "depth"         -> Number, the 1-indexed quadtree depth of the nodes of the rastered image.
     *                    Can also be interpreted as the length of the numbers in the image
     *                    string. <br>
     * "query_success" -> Boolean, whether the query was able to successfully complete. Don't
     *                    forget to set this to true! <br>
     * @see #REQUIRED_RASTER_REQUEST_PARAMS
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        /*System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                           + "your browser.");*/



        this.render_list = new ArrayDeque<>();
        findQueryBox(this.mainTree,
                params.get("ullon"),
                params.get("ullat"),
                params.get("lrlon"),
                params.get("lrlat"),
                (params.get("lrlon") - params.get("ullon")) / params.get("w"));

        QuickSortTile sorter = new QuickSortTile();
        Tile[] latSort = this.render_list.toArray(new Tile[this.render_list.size()]);
        sorter.sortDecreasing(latSort, "lat");

        Tile[][] rasterTiles = null;

        double currLat = latSort[0].getUlLat();
        for (int i = 1; i < latSort.length; i++) {
            if (!((Double) latSort[i].getUlLat()).equals((Double) currLat)) {
                rasterTiles = new Tile[(int) (latSort.length / (double) i)][i];
                break;
            }
        }

        String[][] rasterImgPaths = new String[rasterTiles.length][rasterTiles[0].length];

        int n = 0;
        for (int i = 0; i < rasterTiles.length; i++) {
            for (int j = 0; j < rasterTiles[i].length; j++) {
                rasterTiles[i][j] = latSort[n];
                n++;
            }
            sorter.sortIncreasing(rasterTiles[i], "lon");
            for (int k = 0; k < rasterImgPaths[i].length; k++) {
                rasterImgPaths[i][k] = rasterTiles[i][k].getImgPathActual();
            }
        }


        /*for (int i = 0; i < rasterImgPaths.length; i++) {
            for (int j = 0; j < rasterImgPaths[0].length; j++) {
                System.out.print(rasterImgPaths[i][j] + "\t");
            }
            System.out.println("");
        }*/


        results.put("render_grid", rasterImgPaths);
        results.put("raster_ul_lon", rasterTiles[0][0].getUlLon());
        results.put("raster_ul_lat", rasterTiles[0][0].getUlLat());
        results.put("raster_lr_lon", rasterTiles[rasterTiles.length - 1][rasterTiles[0].length - 1].getLrLon());
        results.put("raster_lr_lat", rasterTiles[rasterTiles.length - 1][rasterTiles[0].length - 1].getLrLat());
        results.put("depth", rasterTiles[0][0].getImgPath().length());
        results.put("query_success", true);

        return results;
    }

    public TileQuadTree genQuadTree() {
        Tile root = new Tile("root", MapServer.ROOT_ULLON, MapServer.ROOT_ULLAT, MapServer.ROOT_LRLON, MapServer.ROOT_LRLAT);
        TileQuadTree rootTree = new TileQuadTree(root);
        rootTree.setNW(new TileQuadTree(rootTree, 1));
        rootTree.setNE(new TileQuadTree(rootTree, 2));
        rootTree.setSW(new TileQuadTree(rootTree, 3));
        rootTree.setSE(new TileQuadTree(rootTree, 4));
        return rootTree;
    }

    public void findQueryBox(TileQuadTree currTree,
                             double ulLonQuery,
                             double ulLatQuery,
                             double lrLonQuery,
                             double lrLatQuery,
                             double lonDPPQuery) {
        if (currTree.getElement().intersects(ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery)) {
            if (currTree.getElement().lonDPP() <= lonDPPQuery) {
                this.render_list.add(currTree.getElement());
            } else {
                //System.out.println(currTree.getElement().lonDPP());
                if (currTree.NW != null) {
                    findQueryBox(currTree.NW, ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery, lonDPPQuery);
                    findQueryBox(currTree.NE, ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery, lonDPPQuery);
                    findQueryBox(currTree.SW, ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery, lonDPPQuery);
                    findQueryBox(currTree.SE, ulLonQuery, ulLatQuery, lrLonQuery, lrLatQuery, lonDPPQuery);
                } else {
                    this.render_list.add(currTree.getElement());
                }
            }
            //System.out.println(currTree.getElement().getImgPathActual());
            //System.out.println(currTree.getElement().lonDPP() + " <= " + lonDPPQuery);
        }
    }



}
