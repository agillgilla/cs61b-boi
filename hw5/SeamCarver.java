/**
 * Created by Arjun on 4/25/2017.
 */
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(this.picture);
    }

    public int width() {
        return this.picture.width();
    }

    public int height() {
        return this.picture.height();
    }

    public double energy(int x, int y) { // energy of pixel at column x and row y
        if (x < 0 || x > this.width() - 1) {
            throw new IndexOutOfBoundsException("x is out of range.");
        } else if (y < 0 || y > this.height() - 1) {
            throw new IndexOutOfBoundsException("y is out of range.");
        } else {
            int leftXIndex = this.wrapX(x - 1);
            int rightXIndex = this.wrapX(x + 1);
            int dX = this.energyDiff(leftXIndex, y, rightXIndex, y);

            int topYIndex = this.wrapY(y - 1);
            int bottomYIndex = this.wrapY(y + 1);
            int dY = this.energyDiff(x, topYIndex, x, bottomYIndex);

            return dX + dY;
        }
    }

    public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
        Pixel[][] pixels = new Pixel[this.height()][this.width()];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                pixels[row][col] = new Pixel(energy(col, row), row, col);
            }
        }

        Pixel[][] pixelsTransposed = SeamCarver.transpose(pixels);

        return findVerticalSeamFlipped(pixelsTransposed);
    }

    public int[] findVerticalSeam() { // sequence of indices for vertical seam
        Pixel[][] pixels = new Pixel[this.height()][this.width()];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                pixels[row][col] = new Pixel(energy(col, row), row, col);
            }
        }

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                if (row == 0) {
                    pixels[row][col].setConnection(null);
                } else {
                    if (!validX(col - 1, pixels[0].length)) {
                        if (pixels[row - 1][col].getCumEnergy()
                                < pixels[row - 1][col + 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        } else {
                            pixels[row][col].setConnection(pixels[row - 1][col + 1]);
                        }
                    } else if (!validX(col + 1, pixels[0].length)) {
                        if (pixels[row - 1][col - 1].getCumEnergy()
                                < pixels[row - 1][col].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col - 1]);
                        } else {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        }
                    } else if (!validX(col - 1, pixels[0].length)
                            && !validX(col + 1, pixels[0].length)) {
                        pixels[row][col].setConnection(pixels[row - 1][col]);
                    } else {
                        double minCumulativeEnergy =
                                Math.min(pixels[row - 1][col - 1].getCumEnergy(),
                                        Math.min(pixels[row - 1][col].getCumEnergy(),
                                        pixels[row - 1][col + 1].getCumEnergy()));
                        if (minCumulativeEnergy == pixels[row - 1][col - 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col - 1]);
                        } else if (minCumulativeEnergy == pixels[row - 1][col].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        } else if (minCumulativeEnergy == pixels[row - 1][col + 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col + 1]);
                        } else {
                            throw new RuntimeException("ERROR IN MINCUMULATIVE ENERGY!");
                        }
                    }
                }
            }
        }

        Pixel minTotalCumulativeEnergy = pixels[pixels.length - 1][0];
        for (int col = 1; col < pixels[pixels.length - 1].length; col++) {
            if (pixels[pixels.length - 1][col].getCumEnergy()
                    < minTotalCumulativeEnergy.getCumEnergy()) {
                minTotalCumulativeEnergy = pixels[pixels.length - 1][col];
            }
        }

        int[] seam = new int[pixels.length];
        Pixel currPixel = minTotalCumulativeEnergy;
        int i = seam.length - 1;
        while (currPixel != null) {
            seam[i] = currPixel.getCol();
            i--;
            currPixel = currPixel.getConnection();
        }

        return seam;

    }

    private int[] findVerticalSeamFlipped(Pixel[][] pixels) {

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                if (row == 0) {
                    pixels[row][col].setConnection(null);
                } else {
                    if (!validX(col - 1, pixels[0].length)) {
                        if (pixels[row - 1][col].getCumEnergy()
                                < pixels[row - 1][col + 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        } else {
                            pixels[row][col].setConnection(pixels[row - 1][col + 1]);
                        }
                    } else if (!validX(col + 1, pixels[0].length)) {
                        if (pixels[row - 1][col - 1].getCumEnergy()
                                < pixels[row - 1][col].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col - 1]);
                        } else {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        }
                    } else if (!validX(col - 1, pixels[0].length)
                            && !validX(col + 1, pixels[0].length)) {
                        pixels[row][col].setConnection(pixels[row - 1][col]);
                    } else {
                        double minCumulativeEnergy =
                                Math.min(pixels[row - 1][col - 1].getCumEnergy(),
                                        Math.min(pixels[row - 1][col].getCumEnergy(),
                                        pixels[row - 1][col + 1].getCumEnergy()));
                        if (minCumulativeEnergy == pixels[row - 1][col - 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col - 1]);
                        } else if (minCumulativeEnergy == pixels[row - 1][col].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col]);
                        } else if (minCumulativeEnergy == pixels[row - 1][col + 1].getCumEnergy()) {
                            pixels[row][col].setConnection(pixels[row - 1][col + 1]);
                        } else {
                            throw new RuntimeException("ERROR IN MINCUMULATIVE ENERGY!");
                        }
                    }
                }
            }
        }

        Pixel minTotalCumulativeEnergy = pixels[pixels.length - 1][0];
        for (int col = 1; col < pixels[pixels.length - 1].length; col++) {
            if (pixels[pixels.length - 1][col].getCumEnergy()
                    < minTotalCumulativeEnergy.getCumEnergy()) {
                minTotalCumulativeEnergy = pixels[pixels.length - 1][col];
            }
        }

        int[] seam = new int[pixels.length];
        Pixel currPixel = minTotalCumulativeEnergy;
        int i = seam.length - 1;
        while (currPixel != null) {
            seam[i] = currPixel.getRow();
            i--;
            currPixel = currPixel.getConnection();
        }

        return seam;

    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from picture
        SeamRemover.removeHorizontalSeam(this.picture, seam);
    }

    public void removeVerticalSeam(int[] seam) { // remove vertical seam from picture
        SeamRemover.removeVerticalSeam(this.picture, seam);
    }

    private int energyDiff(int x1, int y1, int x2, int y2) {
        double dXRed = Math.pow(this.picture.get(x1, y1).getRed()
                - this.picture.get(x2, y2).getRed(), 2);
        double dXGreen = Math.pow(this.picture.get(x1, y1).getGreen()
                - this.picture.get(x2, y2).getGreen(), 2);
        double dXBlue = Math.pow(this.picture.get(x1, y1).getBlue()
                - this.picture.get(x2, y2).getBlue(), 2);
        return (int) (dXRed + dXGreen + dXBlue);
    }

    private int wrapX(int x) {
        if (x == -1) {
            return this.width() - 1;
        } else if (x == this.width()) {
            return 0;
        } else {
            return x;
        }
    }

    private int wrapY(int y) {
        if (y == -1) {
            return this.height() - 1;
        } else if (y == this.height()) {
            return 0;
        } else {
            return y;
        }
    }

    private boolean validX(int x, int width) {
        return x >= 0 && x < width;
    }

    private static Pixel[][] transpose(Pixel[][] twoDArr) {
        Pixel[][] temp = new Pixel[twoDArr[0].length][twoDArr.length];
        for (int i = 0; i < twoDArr.length; i++) {
            for (int j = 0; j < twoDArr[0].length; j++) {
                temp[j][i] = twoDArr[i][j];
            }
        }
        return temp;
    }
}
