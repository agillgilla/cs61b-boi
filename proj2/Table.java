import java.io.*;
//import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created by Arjun on 2/17/2017.
 */

public class Table<Item> {
    public ArrayDeque<ArrayDeque<Item>> columns = new ArrayDeque<ArrayDeque<Item>>();
    public ArrayDeque<String> columnNames;
    //public Table(String filename) {
    public Table(String... columns) {
        this.columnNames = new ArrayDeque(columns);
        for (int i = 0; i < columns.length; i++) {
            this.columns.addLast(new ArrayDeque<Item>());
        }
    }

    public void addRow(Item... entries) {
        int columnIndex = 0;
        for (Item entry : entries) {
            this.columns.get(columnIndex).addLast(entry);
            columnIndex++;
        }
    }

    public void printTable() {
        for (String columnName : this.columnNames) {
            System.out.print(columnName + "\t");
        }
        System.out.println("");
        for (int row = 0; row < this.columns.get(0).size(); row++) {
            for (int column = 0; column < this.columns.size(); column++) {
                System.out.print(this.columns.get(column).get(row) + "\t");
            }
            System.out.println("");
        }
    }

    public void join(Table other) {
        int newColumnNamesLength = this.columnNames.length + other.columnNames.length;
        for (int i = 0; i < this.columnNames.length; i++) {
            for (int j = 0; j < other.columnNames.length; j++) {
                if (this.columnNames[i] == other.columnNames[j]) {
                    newColumnNamesLength--;

                }
            }
        }
        String[] newColumnNames = new String[newColumnNamesLength];

    }

    public static void main(String[] args) {
        Table T1 = new Table("A", "B");
        T1.addRow(2, 5);
        T1.addRow(8, 3);
        T1.addRow(13, 7);
        T1.printTable();
    }
}