package fr.uge.myproject.game;

public class Element {


	@Override
	public String toString() {
		return "Element [name=" + name + ", skin=" + skin + ", position=" + position + "]";
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

	private final String name;
	private final String skin;
	private final Position position;

    public Element(String name, String skin, Position position) {
        this.name = name;
        this.skin = skin;
        this.position = position;
    }
}