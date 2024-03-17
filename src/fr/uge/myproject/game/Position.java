package fr.uge.myproject.game;

public class Position {

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private final int x;
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
