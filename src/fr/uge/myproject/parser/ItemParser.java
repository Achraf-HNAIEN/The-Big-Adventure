package fr.uge.myproject.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uge.myproject.game.*;

public class ItemParser {

    // Regular expressions
    private static final Pattern NAME_PATTERN = Pattern.compile("name:\\s*(\\w+)");
    private static final Pattern SKIN_PATTERN = Pattern.compile("skin:\\s*(\\w+)");
    private static final Pattern POSITION_PATTERN = Pattern.compile("position:\\s*\\((\\d+),(\\d+)\\)");
    private static final Pattern KIND_PATTERN = Pattern.compile("kind:\\s*(\\w+)");
    private static final Pattern DAMAGE_PATTERN = Pattern.compile("damage:\\s*(\\d+)");

    public static Item parseItem(String elementText) throws IllegalArgumentException {
        String[] lines = elementText.split("\\r?\\n");

        String name = null, skin = null, kind = null;
        int x = -1, y = -1, damage = 0; // Default damage is set to 0
        int lineNum = 0;

        for (String line : lines) {
            lineNum++;
            Matcher nameMatcher = NAME_PATTERN.matcher(line);
            Matcher skinMatcher = SKIN_PATTERN.matcher(line);
            Matcher positionMatcher = POSITION_PATTERN.matcher(line);
            Matcher kindMatcher = KIND_PATTERN.matcher(line);
            Matcher damageMatcher = DAMAGE_PATTERN.matcher(line);

            if (nameMatcher.find()) name = nameMatcher.group(1);
            if (skinMatcher.find()) skin = skinMatcher.group(1);
            if (positionMatcher.find()) {
                x = Integer.parseInt(positionMatcher.group(1));
                y = Integer.parseInt(positionMatcher.group(2));
            }
            if (kindMatcher.find()) kind = kindMatcher.group(1);
            if (damageMatcher.find()) {
                damage = Integer.parseInt(damageMatcher.group(1));
            }
        }

        extracted(name, skin, kind, x, y, lineNum);

        Position position = new Position(x, y);
        Element element = new Element(name, skin, position);
        return new Item(element, kind, damage);
    }

	private static void extracted(String name, String skin, String kind, int x, int y, int lineNum) {
		if (name == null) throw new IllegalArgumentException("Item name is missing at line " + lineNum);
        if (skin == null) throw new IllegalArgumentException("Item skin is missing at line " + lineNum);
        if (x == -1 || y == -1) throw new IllegalArgumentException("Item position is missing at line " + lineNum);
        if (kind == null) throw new IllegalArgumentException("Item kind is missing at line " + lineNum);
	}
}
