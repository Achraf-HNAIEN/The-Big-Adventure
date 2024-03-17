package fr.uge.myproject.graphic;

import fr.uge.myproject.game.Enemy;
import fr.uge.myproject.game.Item;
import fr.uge.myproject.game.Obstacle;
import fr.uge.myproject.game.Player;
import fr.uge.myproject.game.Position;
import fr.uge.myproject.gameLogic.GameElements;
import fr.uge.myproject.parser.Cell;
import fr.uge.myproject.parser.Grid;
import fr.uge.myproject.graphic.Inventory;
import fr.umlv.zen5.ScreenInfo;

import fr.umlv.zen5.ApplicationContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class GameScreenDisplay {

    private final ImageLoader imageLoader;
    private final ApplicationContext context;
    private final int cellSize;
    private final Grid grid;
    private final int screenWidth;
    private final int screenHeight;
    private final Inventory inventory;


    public GameScreenDisplay(ApplicationContext context, ImageLoader imageLoader, int cellSize, Grid grid, Inventory inventory) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.cellSize = cellSize;
        this.grid = grid;
        this.inventory = inventory;

        ScreenInfo screenInfo = context.getScreenInfo();
        this.screenWidth = (int) screenInfo.getWidth();
        this.screenHeight = (int) screenInfo.getHeight();
    }

    public void draw(Map<String, String> encodingMap, GameElements gameElements,boolean isInventoryOpen) {
    	context.renderFrame(graphics -> {
    	    drawBackground(graphics);
    	    drawGridElements(graphics, encodingMap);
    	    drawGameElements(graphics, gameElements);
    	    drawInventory(graphics,isInventoryOpen); 
    	});

    }

    private void drawBackground(Graphics2D graphics) {
        ScreenInfo screenInfo = context.getScreenInfo();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, (int) screenInfo.getWidth(), (int) screenInfo.getHeight()); 
    }

    private void drawGridElements(Graphics2D graphics, Map<String, String> encodingMap) {
        for (int i = 0; i < grid.getNumberOfRows(); i++) {
            for (int j = 0; j < grid.getNumberOfColumns(); j++) {
                Cell cell = grid.getCell(i, j);
                String imageName = cell.getType(); // This should give us "WALL", "BOG", etc.
                if (imageName.equals("empty")) {
                    continue;
                }
                if (imageName != null) {
                    BufferedImage image = imageLoader.getImage(imageName);
                    if (image != null) {
                        graphics.drawImage(image, j * cellSize, i * cellSize, cellSize, cellSize, null);
                    } else {
                        System.out.println("Image not found for name: " + imageName);
                    }
                }
            }
        }
    }

    private void drawGameElements(Graphics2D graphics, GameElements gameElements) {

    	for (Player player : gameElements.getPlayers()) {
            String skin = player.getElement().getSkin();
            BufferedImage image = imageLoader.getImage(skin);
            if (image != null) {
                int x = player.getElement().getPosition().getX() * cellSize;
                int y = player.getElement().getPosition().getY() * cellSize;

                graphics.drawImage(image, x, y, cellSize, cellSize, null);

                String playerName = player.getElement().getName();
                Font font = new Font("Arial", Font.BOLD, 12);
                graphics.setFont(font);
                graphics.setColor(Color.BLACK);
                int nameWidth = graphics.getFontMetrics().stringWidth(playerName);
                graphics.drawString(playerName, x + (cellSize - nameWidth) / 2, y + 35);

                // Draw the health bar
                int healthBarWidth = 40;
                int healthBarHeight = 5;
                int healthPercentage = (player.getHealth() * 10);
                int filledWidth = (int) (healthPercentage / 100 * healthBarWidth);
                graphics.setColor(Color.RED);
                graphics.fillRect(x + (cellSize - healthBarWidth) / 2, y - 15, filledWidth, healthBarHeight);
                graphics.setColor(Color.GRAY);
                graphics.drawRect(x + (cellSize - healthBarWidth) / 2, y - 15, healthBarWidth, healthBarHeight);
            }
        }
    	
        for (Item item : gameElements.getItems()) {
            String skin = item.getElement().getSkin();
            BufferedImage image = imageLoader.getImage(skin);
            if (image != null) {
                int x = item.getElement().getPosition().getX() * cellSize;
                int y = item.getElement().getPosition().getY() * cellSize;
                graphics.drawImage(image, x, y, cellSize, cellSize, null);
            }
        }
        for (Obstacle obstacle : gameElements.getObstacles()) {
            String skin = obstacle.getSkin();
            BufferedImage image = imageLoader.getImage(skin);
            if (image != null) {
                int x = obstacle.getPosition().getX() * cellSize;
                int y = obstacle.getPosition().getY() * cellSize;
                graphics.drawImage(image, x, y, cellSize, cellSize, null);
            }
        }
        for (Enemy enemy : gameElements.getEnemies()) {
            String skin = enemy.getSkin();
            BufferedImage image = imageLoader.getImage(skin);
            if (image != null) {
                int x = enemy.getPosition().getX() * cellSize;
                int y = enemy.getPosition().getY() * cellSize;
                graphics.drawImage(image, x, y, cellSize, cellSize, null);
            }
        }

    }
    public boolean isItemAtPosition(Position position , GameElements gameElements) {
        for (Item item : gameElements.getItems()) {
            if (item.getElement().getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public static String getItemAtPosition(Position position, GameElements gameElements) {
        for (Item item : gameElements.getItems()) {
            if (item.getElement().getPosition().equals(position)) {
                return item.getElement().getName();
            }
        }
        return null;
    }
    private void drawInventory(Graphics2D graphics,boolean isInventoryOpen) {
        if (!isInventoryOpen) return;

        int slotSize = 30; 
        int spacing = 10; 
        int inventoryWidth = spacing*11 + slotSize* 9; 
        int inventoryHeight = 80; 
        int startX = (screenWidth - inventoryWidth) / 2;
        int startY = (screenHeight - inventoryHeight) / 2; 

        // Draw the inventory background
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(startX, startY, inventoryWidth, inventoryHeight);

        // Draw inventory slots
        for (int i = 0; i < inventory.getSlots().length; i++) {
            int x = startX + spacing + (i * (slotSize + spacing));
            int y = startY + spacing;

            // Highlight the selected slot
            if (i == inventory.getSelectedItemIndex()) {
                graphics.setColor(Color.YELLOW);
            } else {
                graphics.setColor(Color.WHITE);
            }
            graphics.fillRect(x, y, slotSize, slotSize);

            // Draw item name if the slot is not empty
            String itemName = inventory.getSlots()[i];
            if (itemName != null) {
                graphics.setColor(Color.BLACK);
                graphics.drawString(itemName, x + 5, y + slotSize / 2); // Adjust text positioning as needed
            }
        }
    }
    

}
