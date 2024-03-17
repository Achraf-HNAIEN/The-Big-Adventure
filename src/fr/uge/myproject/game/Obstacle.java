package fr.uge.myproject.game;

public class Obstacle {
    @Override
	public String toString() {
		return "Obstacle [name=" + name + ", skin=" + skin + ", position=" + position + ", kind=" + kind + "]";
	}

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

	private final String name;
    private final String skin;
    private final Position position;
    private final String kind;

    public Obstacle(String name, String skin, Position position, String kind) {
        this.name = name;
        this.skin = skin;
        this.position = position;
        this.kind = kind;
    }

}