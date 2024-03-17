package fr.uge.myproject.game;

public class Item {
    public Element getElement() {
		return element;
	}


	public String getKind() {
		return kind;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public String toString() {
		return "Item [element=" + element + ", kind=" + kind + ", damage=" + damage + "]";
	}

	private final Element element;
    private final String kind;
    private final int damage;

    public Item(Element element, String kind, int damage) {
        this.element = element;
        this.kind = kind;
        this.damage = damage;
    }}