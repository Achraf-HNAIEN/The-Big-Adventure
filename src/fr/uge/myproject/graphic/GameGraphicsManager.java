package fr.uge.myproject.graphic;

import java.util.Map;

import fr.uge.myproject.database.ItemDatabase;
import fr.uge.myproject.game.Element;
import fr.uge.myproject.game.Item;
import fr.uge.myproject.game.Player;
import fr.uge.myproject.game.Position;
import fr.uge.myproject.gameLogic.GameElements;
import fr.uge.myproject.parser.Grid;
import fr.umlv.zen5.ApplicationContext;

public class GameGraphicsManager {


    private final GameScreenDisplay gameScreenDisplay;
    private final ApplicationContext context;
    private final Grid grid;
    private final GameElements gameElements;
    private final Map<String, String> encodings;
    private int cellSize;
    private Inventory inventory; //
    private boolean isInventoryOpen;


    public GameGraphicsManager(ApplicationContext context, ImageLoader imageLoader, Grid grid, GameElements gameElements, Map<String, String> encodings, int cellSize,Inventory inventory,boolean isInventoryOpen) {
        this.context = context;
        this.grid = grid;
        this.gameElements = gameElements;
        this.encodings = encodings;
        this.cellSize = cellSize;
        this.inventory = inventory;
        this.isInventoryOpen = isInventoryOpen;
        this.gameScreenDisplay = new GameScreenDisplay(context, imageLoader, cellSize, grid,inventory);
    }

    public void draw() {
        gameScreenDisplay.draw(encodings, gameElements,isInventoryOpen);

    }
    public void toggleInventory() {
        isInventoryOpen = !isInventoryOpen;
    }
    public void selectNextItem() {
        inventory.setSelectedItemIndex((inventory.getSelectedItemIndex() + 1) % inventory.getSlots().length);
    }
    public boolean isInventoryOpen() {
        return isInventoryOpen;
    }


    public void selectPreviousItem() {
        int newIndex = inventory.getSelectedItemIndex() - 1;
        if (newIndex < 0) newIndex = inventory.getSlots().length - 1;
        inventory.setSelectedItemIndex(newIndex);
    }
    public void pickUpItem() {
        if (isInventoryOpen) return;

        // Get the current player and their position
        Player player = gameElements.getPlayers().get(0);
        Position playerPos = player.getElement().getPosition();
        System.out.println("Player position: " + playerPos.getX() + ", " + playerPos.getY());

        // Check if there's an item at the player's position
        boolean itemPresent = gameElements.isItemAtPosition(playerPos);
        System.out.println("Is there an item at the player's position? " + itemPresent);

        // If an item is present, attempt to pick it up
        if (itemPresent) {
            String itemName = gameElements.getItemAtPosition(playerPos);
            System.out.println("Item at position: " + itemName);

            if (itemName != null && inventory.addItem(itemName)) {
                System.out.println("Picked up item: " + itemName);
                gameElements.removeItemAtPosition(playerPos);
            } else {
                System.out.println("Failed to add item to inventory. Inventory might be full.");
            }
        } else {
            System.out.println("No item at player position to pick up.");
        }
    }


    public void movePlayer(Direction direction) {
        Player player = gameElements.getPlayers().get(0);
        Element currentElement = player.getElement();
        Position currentPosition = currentElement.getPosition();

        int newX = currentPosition.getX();
        int newY = currentPosition.getY();

        switch (direction) {
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
        }

        // Check boundaries
        if (newX < 0 || newX >= grid.getNumberOfColumns() || newY < 0 || newY >= grid.getNumberOfRows()) {
            return; // Out of bounds, don't update position
        }

        // Check for obstacles
        String cellType = grid.getCell(newY, newX).getType();
        if (ItemDatabase.getItemBase().getOrDefault(cellType, "").equals("obstacles")) {
            return;
        }

        Position newPosition = new Position(newX, newY);
        Element newElement = new Element(currentElement.getName(), currentElement.getSkin(), newPosition);
        Player updatedPlayer = new Player(newElement, player.isPlayer(), player.getHealth());
        gameElements.getPlayers().set(0, updatedPlayer);
    }
    public void dropItem() {
        if (!isInventoryOpen) return;

        Player player = gameElements.getPlayers().get(0);
        Position playerPos = player.getElement().getPosition();
        int selectedIndex = inventory.getSelectedItemIndex();
        String itemName = inventory.getItem(selectedIndex);

        if (itemName != null) {
            String itemKind = "Inventory"; // Default kind
            int itemDamage = 0; // Default damage

            if (itemKind.equals("weapon")) {
                Item weapon = gameElements.getItems().stream()
                                .filter(i -> i.getElement().getName().equals(itemName))
                                .findFirst()
                                .orElse(null);

                if (weapon != null) {
                    itemDamage = weapon.getDamage();
                }
            }

            gameElements.addItemAtPosition(playerPos, itemName, itemKind, itemDamage);

            inventory.removeItem(selectedIndex);
        }
    }
}

