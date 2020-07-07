package hw;

import java.util.concurrent.TimeUnit;

public class GreatHall implements Runnable {

	private static final int RESPAWNTIME = 1;
	Mine mine;

	public GreatHall(Mine mine) {
		this.mine = mine;

		Thread thread = new Thread(this);
		thread.start();
	}

	private void respawn() {
		if (mine.getCapacity() > 0) {
			new OrcWorker(mine);
			try {
				TimeUnit.SECONDS.sleep(RESPAWNTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (mine.getCapacity() > 0) {
			this.respawn();
		}

	}

}
