package fr.uge.myproject.parser;

import java.util.ArrayList;
import java.util.List;


public class Grid {
    private final List<List<Cell>> cells;

    public Grid(int rows, int columns) {
        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new Cell("empty")); 
            }
            cells.add(row);
        }
    }
    
    public List<List<Cell>> getCells() {
        return cells;
    }


    public void setCell(int row, int column, Cell tile) {
        cells.get(row).set(column, tile);
    }

    public Cell getCell(int row, int column) {
        return cells.get(row).get(column);
    }

    public int getNumberOfRows() {
        return cells.size();
    }

    public int getNumberOfColumns() {
        if (!cells.isEmpty()) {
            return cells.get(0).size();
        }
        return 0;
    }


}
