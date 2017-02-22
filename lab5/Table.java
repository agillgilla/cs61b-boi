import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Arjun on 2/17/2017.
 */
public class Table {
    private ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

    //public Table(String filename) {
    public Table(int numColumns) {
        for (int i = 0; i < numColumns; i++) {
            this.columns.add(new ArrayList<Integer>());
        }

        /*try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split(",");
                int columnIndex = 0;
                if (firstLine) {
                    for (String name : entries) {
                        this.columns.add(new ArrayList<String>());
                    }
                    firstLine = false;
                }
                else {
                    for (String entry : entries) {
                        this.columns.get(columnIndex).add(entry);
                        columnIndex++;
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
    }

    public void addRow(Integer... entries) {
        int columnIndex = 0;
        for (Integer entry : entries) {
            this.columns.get(columnIndex).add(entry);
            columnIndex++;
        }
    }

    public static void main(String[] args) {
        Table T1 = new Table(2);
        T1.addRow(2, 5);
        T1.addRow(8, 3);
        T1.addRow(13, 7);
    }
}
