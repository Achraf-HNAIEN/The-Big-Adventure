package fr.uge.myproject.gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.uge.myproject.game.*;
import fr.uge.myproject.parser.*;

public class GameElements {
    
    private List<Player> players = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    public void addItemAtPosition(Position position, String itemName, String kind, int damage) {
        Element itemElement = new Element(itemName, "itemSkin", position);
        Item newItem = new Item(itemElement, kind, damage);
        this.items.add(newItem);
    }

    
    public void removeItemAtPosition(Position position) {
        items.removeIf(item -> item.getElement().getPosition().equals(position));
    }


    public void parseGameElements(String fileContent) {
    	Pattern elementPattern = Pattern.compile("\\[element\\](.+?)(?=\\[element\\]|$)", Pattern.DOTALL);
        Matcher elementMatcher = elementPattern.matcher(fileContent);

        while (elementMatcher.find()) {
            String elementText = elementMatcher.group(1).trim();
            parseElement(elementText);
        }
    }
    public boolean isItemAtPosition(Position position) {
        return items.stream().anyMatch(item -> item.getElement().getPosition().equals(position));
    }

    public String getItemAtPosition(Position position) {
        return items.stream()
                    .filter(item -> item.getElement().getPosition().equals(position))
                    .findFirst()
                    .map(Item::getElement)
                    .map(Element::getName)
                    .orElse(null);
    }
    private void parseElement(String elementText) {
        if (elementText.contains("player: true")) {
            Player player = PlayerParser.parsePlayer(elementText);
            
            addPlayer(player);
        } else if (elementText.contains("kind: item")) {
            Item item = ItemParser.parseItem(elementText);
            addItem(item);
        } else if (elementText.contains("kind: enemy")) {
            Enemy enemy = EnemyParser.parseEnemy(elementText);
            addEnemy(enemy);
        } else if (elementText.contains("kind: obstacle")) {
            Obstacle obstacle = ObstacleParser.parseObstacle(elementText);
            addObstacle(obstacle);
        }
        //if needed for next phases add here 
    }
}