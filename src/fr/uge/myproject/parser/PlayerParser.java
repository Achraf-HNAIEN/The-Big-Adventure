package fr.uge.myproject.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uge.myproject.game.*;

public class PlayerParser {

    // Regular expressions for parsing player attributes
    private static final Pattern NAME_PATTERN = Pattern.compile("name:\\s*(\\w+)");
    private static final Pattern PLAYER_PATTERN = Pattern.compile("player:\\s*(true|false)");
    private static final Pattern SKIN_PATTERN = Pattern.compile("skin:\\s*(\\w+)");
    private static final Pattern POSITION_PATTERN = Pattern.compile("position:\\s*\\((\\d+),(\\d+)\\)");
    private static final Pattern HEALTH_PATTERN = Pattern.compile("health:\\s*(\\d+)");

    public static Player parsePlayer(String elementText) throws IllegalArgumentException {
        String[] lines = elementText.split("\\r?\\n");

        String name = null, skin = null, behavior = null;
        int x = -1, y = -1, health = -1;
        boolean isPlayer = false;
        int lineNum = 0;

        for (String line : lines) {
            lineNum++;
            Matcher nameMatcher = NAME_PATTERN.matcher(line);
            Matcher playerMatcher = PLAYER_PATTERN.matcher(line);
            Matcher skinMatcher = SKIN_PATTERN.matcher(line);
            Matcher positionMatcher = POSITION_PATTERN.matcher(line);
            Matcher healthMatcher = HEALTH_PATTERN.matcher(line);

            if (nameMatcher.find()) name = nameMatcher.group(1);
            if (playerMatcher.find()) isPlayer = Boolean.parseBoolean(playerMatcher.group(1));
            if (skinMatcher.find()) skin = skinMatcher.group(1);
            if (positionMatcher.find()) {
                x = Integer.parseInt(positionMatcher.group(1));
                y = Integer.parseInt(positionMatcher.group(2));
            }
            if (healthMatcher.find()) health = Integer.parseInt(healthMatcher.group(1));
        }

        if (name == null) throw new IllegalArgumentException("Player name is missing at line " + lineNum);
        if (skin == null) throw new IllegalArgumentException("Player skin is missing at line " + lineNum);
        if (x == -1 || y == -1) throw new IllegalArgumentException("Player position is missing at line " + lineNum);
        if (health == -1) throw new IllegalArgumentException("Player health is missing at line " + lineNum);

        Position position = new Position(x, y);
        Element element = new Element(name, skin, position);
        return new Player(element, isPlayer, health);
    }
}
