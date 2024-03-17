package fr.uge.myproject.game;

public class Player {
    public Element getElement() {
		return element;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public int getHealth() {
		return health;
	}

	private final Element element;
    @Override
	public String toString() {
		return "Player [element=" + element + ", isPlayer=" + isPlayer + ", health=" + health + "]";
	}

	private final boolean isPlayer;
    private final int health;

    public Player(Element element, boolean isPlayer, int health) {
        this.element = element;
        this.isPlayer = isPlayer;
        this.health = health;
    }
}