package fr.uge.myproject.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GridReader {

    private static final String DATA_BLOCK_START = "data:";
    private static final String TRIPLE_QUOTES = "\"\"\"";

    public Grid readGrid(String fileContent, Map<String, String> encodings) throws IllegalArgumentException {
        String dataBlock = extractDataBlock(fileContent);
        String[] dataLines = dataBlock.split("\\r?\\n");

        List<List<Cell>> gridData = new ArrayList<>();
        int rowNumber = 0;

        for (String dataLine : dataLines) {
            rowNumber++;
            String processedLine = dataLine.replaceAll("^\\s+|\\s+$", "");
            List<Cell> rowData = new ArrayList<>();
            for (char character : processedLine.toCharArray()) {
                String charAsString = String.valueOf(character);

                if (character == ' ') {
                    rowData.add(new Cell("empty"));
                } else if (!encodings.containsKey(charAsString)) {
                    throw new IllegalArgumentException("Unrecognized character '" + charAsString + "' at line " + rowNumber);
                } else {
                    rowData.add(new Cell(encodings.get(charAsString)));
                }
            }
            gridData.add(rowData);
        }

        int rows = gridData.size();
        int columns = gridData.isEmpty() ? 0 : gridData.get(0).size();
        Grid grid = new Grid(rows, columns);

        for (int i = 0; i < rows; i++) {
            List<Cell> rowData = gridData.get(i);
            for (int j = 0; j < columns; j++) {
                Cell tile = rowData.get(j);
                grid.setCell(i, j, tile);
            }
        }
        return grid;
    }

    private String extractDataBlock(String fileContent) {
        int dataStartIndex = fileContent.indexOf(DATA_BLOCK_START);
        int startOfDataBlock = fileContent.indexOf(TRIPLE_QUOTES, dataStartIndex) + TRIPLE_QUOTES.length();
        int endOfDataBlock = fileContent.indexOf(TRIPLE_QUOTES, startOfDataBlock);
        if (dataStartIndex == -1 || startOfDataBlock == -1 || endOfDataBlock == -1) {
            throw new IllegalArgumentException("Data block not properly defined.");
        }
        return fileContent.substring(startOfDataBlock, endOfDataBlock).trim();
    }
    public static void printGrid(Grid grid) {
        for (int i = 0; i < grid.getNumberOfRows(); i++) {
            for (int j = 0; j < grid.getNumberOfColumns(); j++) {
                Cell tile = grid.getCell(i, j);
                String tileType = tile.getType();
                System.out.print(tileType);
            }
            System.out.println();
        }
}
}
