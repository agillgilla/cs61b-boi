import java.io.*;
//import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created by Arjun on 2/17/2017.
 */

public class Table<Item> {
    public ArrayDeque<Column<Item>> columns = new ArrayDeque<Column<Item>>();

    //public Table(String filename) {
    public Table(String... columns) {
        for (String columnName : columns) {
            this.columns.addLast(new Column(columnName, new ArrayDeque<Item>()));
        }
    }

    public Table(ArrayDeque<Column<Item>>... columnLists) {
        for (ArrayDeque<Column<Item>> columnList : columnLists) {
            for (int i = 0; i < columnList.size(); i++){
                this.columns.addLast(columnList.get(i));
            }
        }
    }

    public Table(ArrayDeque<Column<Item>> columnList1, ArrayDeque<Column<Item>> columnList2, ArrayDeque<Column<Item>> columnList3) {
        for (int i = 0; i < columnList1.size(); i++) {
            this.columns.addLast(columnList1.get(i));
        }
        for (int i = 0; i < columnList2.size(); i++) {
            this.columns.addLast(columnList2.get(i));
        }
        for (int i = 0; i < columnList3.size(); i++) {
            this.columns.addLast(columnList3.get(i));
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
        /*for (String columnName : this.columnNames) {
            System.out.print(columnName + "\t");
        }*/
        for (int i = 0; i < this.columns.size(); i++) {
            System.out.print(this.columns.get(i).getName() + "\t \t");
        }
        System.out.println("");
        for (int row = 0; row < this.columns.get(0).size(); row++) {
            for (int column = 0; column < this.columns.size(); column++) {
                System.out.print(this.columns.get(column).get(row) + "\t \t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * The column order of a join is defined as follows:
     * All shared columns come first, in the relative order they were in the left table of the join (the one listed first in the select clause)
     * The unshared columns from the left table come next, in the same relative order they were before.
     * The unshared columns from the right table come last, in the same relative order they were before
     *
     * @param other Table to join with
     *
     * Source: http://datastructur.es/sp17/materials/proj/proj2/proj2.html#joins
     */
    public Table<Item> join(Table<Item> other) {

        ArrayDeque<Column<Item>> sharedColumns = new ArrayDeque<>();
        ArrayDeque<Column<Item>> leftUnsharedColumns = new ArrayDeque<>();
        ArrayDeque<Column<Item>> rightUnsharedColumns = new ArrayDeque<>();

        boolean foundShared = false;

        for (int i = 0; i < this.columns.size(); i++) {
            foundShared = false;
            for (int j = 0; j < other.columns.size(); j++) {
                if (this.columns.get(i).getName().equals(other.columns.get(j).getName())) {
                    sharedColumns.addLast(this.columns.get(i));
                    foundShared = true;
                }
            }
            if (!foundShared) {
                leftUnsharedColumns.addLast(this.columns.get(i));
            }
        }
        foundShared = false;
        for (int i = 0; i < other.columns.size(); i++) {
            foundShared = false;
            for (int j = 0; j < this.columns.size(); j++) {
                if (this.columns.get(i).getName().equals(other.columns.get(j).getName())) {
                    foundShared = true;
                }
            }
            if (!foundShared) {
                rightUnsharedColumns.addLast(other.columns.get(i));
            }
        }

        return new Table(sharedColumns, leftUnsharedColumns, rightUnsharedColumns);

    }

    public static void main(String[] args) {
        Table T1 = new Table("A", "B");
        T1.addRow(2, 5);
        T1.addRow(8, 3);
        T1.addRow(13, 7);
        T1.printTable();

        Table T2 = new Table("A", "C");
        T2.addRow(2, 7);
        T2.addRow(10, 6);
        T2.printTable();

        Table joined = T1.join(T2);
        joined.printTable();
    }
}