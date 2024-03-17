package fr.uge.myproject.game;

public class Enemy {
    public String getName() {
		return name;
	}

	public String getSkin() {
		return skin;
	}

	public Position getPosition() {
		return position;
	}

	public String getKind() {
		return kind;
	}

	public int getHealth() {
		return health;
	}

	public Zone getZone() {
		return zone;
	}

	public String getBehavior() {
		return behavior;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public String toString() {
		return "Enemy [name=" + name + ", skin=" + skin + ", position=" + position + ", kind=" + kind + ", health="
				+ health + ", zone=" + zone + ", behavior=" + behavior + ", damage=" + damage + "]";
	}

	private final String name;
    private final String skin;
    private final Position position;
    private final String kind;
    private final int health;
    private final Zone zone;
    private final String behavior;
    private final int damage;

    public Enemy(String name, String skin, Position position, String kind, int health, Zone zone, String behavior, int damage) {
        this.name = name;
        this.skin = skin;
        this.position = position;
        this.kind = kind;
        this.health = health;
        this.zone = zone;
        this.behavior = behavior;
        this.damage = damage;
    }

}