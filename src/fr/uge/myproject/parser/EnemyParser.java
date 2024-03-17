package fr.uge.myproject.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uge.myproject.game.*;

public class EnemyParser {

    // Regular expressions enemy attributes
    private static final Pattern NAME_PATTERN = Pattern.compile("name:\\s*(\\w+)");
    private static final Pattern SKIN_PATTERN = Pattern.compile("skin:\\s*(\\w+)");
    private static final Pattern POSITION_PATTERN = Pattern.compile("position:\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
    private static final Pattern HEALTH_PATTERN = Pattern.compile("health:\\s*(\\d+)");
    private static final Pattern ZONE_PATTERN = Pattern.compile("zone:\\s*\\((\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*\\((\\d+)\\s*x\\s*(\\s*\\d+)\\s*\\)");
    private static final Pattern BEHAVIOR_PATTERN = Pattern.compile("behavior:\\s*(\\w+)");
    private static final Pattern DAMAGE_PATTERN = Pattern.compile("damage:\\s*(\\d+)");

    public static Enemy parseEnemy(String elementText) throws IllegalArgumentException {
        String[] lines = elementText.split("\\r?\\n");

        String name = null, skin = null, behavior = null;
        int x = -1, y = -1, health = -1, damage = -1, zoneWidth = -1, zoneHeight = -1;
        int lineNum = 0;

        for (String line : lines) {
            lineNum++;
            Matcher nameMatcher = NAME_PATTERN.matcher(line);
            Matcher skinMatcher = SKIN_PATTERN.matcher(line);
            Matcher positionMatcher = POSITION_PATTERN.matcher(line);
            Matcher healthMatcher = HEALTH_PATTERN.matcher(line);
            Matcher zoneMatcher = ZONE_PATTERN.matcher(line);
            Matcher behaviorMatcher = BEHAVIOR_PATTERN.matcher(line);
            Matcher damageMatcher = DAMAGE_PATTERN.matcher(line);

            if (nameMatcher.find()) name = nameMatcher.group(1);
            if (skinMatcher.find()) skin = skinMatcher.group(1);
            if (positionMatcher.find()) {
                x = Integer.parseInt(positionMatcher.group(1));
                y = Integer.parseInt(positionMatcher.group(2));
            }
            if (healthMatcher.find()) health = Integer.parseInt(healthMatcher.group(1));
            if (zoneMatcher.find()) {
                zoneWidth = Integer.parseInt(zoneMatcher.group(3));
                zoneHeight = Integer.parseInt(zoneMatcher.group(4));
            }
            if (behaviorMatcher.find()) behavior = behaviorMatcher.group(1);
            if (damageMatcher.find()) damage = Integer.parseInt(damageMatcher.group(1));
        }

        extracted(name, skin, behavior, x, y, health, damage, zoneWidth, zoneHeight, lineNum);

        Position position = new Position(x, y);
        Zone zone = new Zone(new Position(x, y), new Position(x + zoneWidth, y + zoneHeight));

        return new Enemy(name, skin, position, "enemy", health, zone, behavior, damage);
    }

	private static void extracted(String name, String skin, String behavior, int x, int y, int health, int damage,
			int zoneWidth, int zoneHeight, int lineNum) {
		if (name == null) throw new IllegalArgumentException("Enemy name is missing at line " + lineNum);
        if (skin == null) throw new IllegalArgumentException("Enemy skin is missing at line " + lineNum);
        if (x == -1 || y == -1) throw new IllegalArgumentException("Enemy position is missing at line " + lineNum);
        if (health == -1) throw new IllegalArgumentException("Enemy health is missing at line " + lineNum);
        if (zoneWidth == -1 || zoneHeight == -1) throw new IllegalArgumentException("Enemy zone is missing at line " + lineNum);
        if (behavior == null) throw new IllegalArgumentException("Enemy behavior is missing at line " + lineNum);
        if (damage == -1) throw new IllegalArgumentException("Enemy damage is missing at line " + lineNum);
	}
}
