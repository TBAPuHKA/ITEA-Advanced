package hw;

public class Mine {

	private volatile int capacity;
	private static Mine mine;

	public static Mine getMine() {
		if (mine == null) {
			mine = new Mine();
			return mine;
		} else {
			return mine;
		}
	}

	private Mine() {
		this.capacity = 100;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
}
