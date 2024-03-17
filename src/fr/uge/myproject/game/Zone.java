package fr.uge.myproject.game;

public class Zone {

	@Override
	public String toString() {
		return "Zone [start=" + start + ", end=" + end + "]";
	}

	public Position getStart() {
		return start;
	}

	public Position getEnd() {
		return end;
	}

	private final Position start;
	private final Position end;

    public Zone(Position start, Position end) {
        this.start = start;
        this.end = end;
    }
}