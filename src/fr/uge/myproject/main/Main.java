package fr.uge.myproject.main;

import fr.uge.myproject.gameLogic.GameElements;
import fr.uge.myproject.graphic.GameGraphicsManager;
import fr.uge.myproject.graphic.GameScreenDisplay;
import fr.uge.myproject.graphic.ImageLoader;
import fr.uge.myproject.graphic.Inventory;
import fr.uge.myproject.parser.*;
import fr.uge.myproject.graphic.Direction;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;

public class Main {
    public static void main(String[] args) {
        String mapFile = null;
        boolean validateOnly = false;

        // List all maps and ask for user input
        try (Stream<Path> stream = Files.list(Paths.get("maps"))) {
            stream.filter(file -> !Files.isDirectory(file))
                  .map(Path::getFileName)
                  .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice (--level [map name] or --validate [map name]):");
        String userInput = scanner.nextLine();
        String[] commands = userInput.split(" ");

        if (commands.length == 2 && commands[0].equals("--level")) {
            mapFile = "maps/" + commands[1];
        } else if (commands.length == 2 && commands[0].equals("--validate")) {
            mapFile = "maps/" + commands[1];
            validateOnly = true;
        }

        if (mapFile == null) {
            System.out.println("Invalid input or map file not specified.");
            scanner.close();
            return;
        }

        // Load and validate the map
        try {
            Path mapPath = Paths.get(mapFile);
            String fileContent = Files.readString(mapPath);

            EncodingReader encodingReader = new EncodingReader();
            Map<String, String> encodings = encodingReader.readEncodings(fileContent);
            EncodingReader.printEncodingMap(encodings);
            GridReader gridReader = new GridReader();
            Grid grid = gridReader.readGrid(fileContent, encodings);
            SizeParser sizeParser = new SizeParser();
            sizeParser.validateSize(fileContent, grid.getNumberOfColumns(), grid.getNumberOfRows());

            if (validateOnly) {
                System.out.println("Validation successful.");
                scanner.close();
                return;
            }
            GameElements gameElements = new GameElements();
            gameElements.parseGameElements(fileContent);

            ImageLoader imageLoader = new ImageLoader();
            imageLoader.loadImages(encodings);
            imageLoader.loadGameElementImages(gameElements);
            Inventory inventory = new Inventory(9); 

            int cellSize = 24;

            Application.run(Color.WHITE, ctx -> {
                GameGraphicsManager graphicsManager = new GameGraphicsManager(ctx, imageLoader, grid, gameElements, encodings, cellSize, inventory, false);
                boolean exit = false;

                while (!exit) {
                    Event event = ctx.pollOrWaitEvent(50); 
                    if (event == null) {
                        continue;
                    }

                    if (event.getAction() == Event.Action.KEY_PRESSED) {
                        if (graphicsManager.isInventoryOpen()) {
                            switch (event.getKey() ) {
                                case LEFT:
                                    graphicsManager.selectPreviousItem();
                                    break;
                                case RIGHT:
                                    graphicsManager.selectNextItem();
                                    break;
                                case SPACE:
                                    graphicsManager.dropItem();
                                    break;
                                case I:
                                    graphicsManager.toggleInventory();
                                    break;
							default:
								break;
                            }
                        } else {
                            switch (event.getKey()) {
                                case UP:
                                    graphicsManager.movePlayer(Direction.UP);
                                    break;
                                case DOWN:
                                    graphicsManager.movePlayer(Direction.DOWN);
                                    break;
                                case LEFT:
                                    graphicsManager.movePlayer(Direction.LEFT);
                                    break;
                                case RIGHT:
                                    graphicsManager.movePlayer(Direction.RIGHT);
                                    break;
                                case SPACE:
                                    graphicsManager.pickUpItem();
                                    break;
                                case I:
                                    graphicsManager.toggleInventory();
                                    break;
                                case Q:
                                    exit = true;
                                    break;
							default:
								break;
                            }
                        }
                    }

                    graphicsManager.draw();
                }
            });


            // Rest of your game initialization and logic...
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing content: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}