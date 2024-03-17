package fr.uge.myproject.graphic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;

import fr.uge.myproject.game.Enemy;
import fr.uge.myproject.game.Item;
import fr.uge.myproject.game.Obstacle;
import fr.uge.myproject.game.Player;
import fr.uge.myproject.gameLogic.GameElements;

public class ImageLoader {
    private final Map<String, BufferedImage> images = new HashMap<>();
    private final String tempImageDir = "tempImages";

    public BufferedImage getImage(String name) {
        return images.get(name);
    }

    public void loadGameElementImages(GameElements gameElements) throws IOException {
        // Load images for players
        for (Player player : gameElements.getPlayers()) {
        	System.out.println(player.getElement().getSkin());
            getImage(player.getElement().getSkin());
        }
        
        // Load images for items
        for (Item item : gameElements.getItems()) {
            getImage(item.getElement().getSkin()); 
        }
        
        // Load images for enemies
        for (Enemy enemy : gameElements.getEnemies()) {
            getImage(enemy.getSkin()); 
        }
        
        // Load images for obstacles
        for (Obstacle obstacle : gameElements.getObstacles()) {
            getImage(obstacle.getSkin()); 
        }
    }


    public void loadImages(Map<String, String> encodingMap) throws IOException {
    	extractAndLoadImages();
        for (Map.Entry<String, String> entry : encodingMap.entrySet()) {
            //String code = entry.getKey(); 
            String imageName = entry.getValue(); // "WALL", "BOG", etc.
            getImage(imageName);
        }
    }

    private void extractAndLoadImages() throws IOException {
        // Extract images from JAR file
        String jarPath = "images.jar"; 
        extractImagesFromJar(jarPath, tempImageDir);

        Files.list(Paths.get(tempImageDir)).forEach(path -> {
            try {
                String imageName = path.getFileName().toString().replace(".png", "");
                if (!images.containsKey(imageName)) {
                    BufferedImage image = ImageIO.read(path.toFile());
                    images.put(imageName, image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void extractImagesFromJar(String jarPath, String destDirPath) throws IOException {
        Path destDir = Paths.get(destDirPath);
        Files.createDirectories(destDir); 

        try (JarFile jar = new JarFile(jarPath)) {
            jar.stream().forEach(entry -> {
                if (entry.getName().startsWith("img/") && !entry.isDirectory()) {
                    Path destFile = destDir.resolve(entry.getName().substring("img/".length()));
                    try {
                        Files.copy(jar.getInputStream(entry), destFile, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException("Error extracting file: " + entry.getName(), e);
                    }
                }
            });
        }
    }
}
