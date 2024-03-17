package fr.uge.myproject.parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uge.myproject.game.Obstacle;
import fr.uge.myproject.game.Position;

public class ObstacleParser {

    // Regular expressions for parsing obstacle attributes
    private static final Pattern NAME_PATTERN = Pattern.compile("name:\\s*(\\w+)");
    private static final Pattern SKIN_PATTERN = Pattern.compile("skin:\\s*(\\w+)");
    private static final Pattern POSITION_PATTERN = Pattern.compile("position:\\s*\\((\\d+),(\\d+)\\)");
    private static final Pattern KIND_PATTERN = Pattern.compile("kind:\\s*obstacle");

    public static Obstacle parseObstacle(String elementText) throws IllegalArgumentException {
        String[] lines = elementText.split("\\r?\\n");

        String name = null, skin = null;
        int x = -1, y = -1;
        int lineNum = 0;

        for (String line : lines) {
            lineNum++;
            Matcher nameMatcher = NAME_PATTERN.matcher(line);
            Matcher skinMatcher = SKIN_PATTERN.matcher(line);
            Matcher positionMatcher = POSITION_PATTERN.matcher(line);
            Matcher kindMatcher = KIND_PATTERN.matcher(line);

            if (nameMatcher.find()) name = nameMatcher.group(1);
            if (skinMatcher.find()) skin = skinMatcher.group(1);
            if (positionMatcher.find()) {
                x = Integer.parseInt(positionMatcher.group(1));
                y = Integer.parseInt(positionMatcher.group(2));
            }

            // Confirm this line is part of an obstacle
            if (kindMatcher.find()) {
                if (name == null) throw new IllegalArgumentException("Obstacle name is missing at line " + lineNum);
                if (skin == null) throw new IllegalArgumentException("Obstacle skin is missing at line " + lineNum);
                if (x == -1 || y == -1) throw new IllegalArgumentException("Obstacle position is missing at line " + lineNum);

                Position position = new Position(x, y);
                return new Obstacle(name, skin, position, "obstacle");
            }
        }

        throw new IllegalArgumentException("Invalid obstacle element format");
    }
}
