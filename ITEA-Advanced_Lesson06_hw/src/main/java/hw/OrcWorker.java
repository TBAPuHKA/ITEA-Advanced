package hw;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class OrcWorker implements Runnable {

	private static int AMOUNT = 3;
	private static int SPEED = 1;
	private String name;
	private static int count;
	private Mine mine;
	private int recievedGold = 0;
	public static final Logger logger = Logger.getLogger(OrcWorker.class.getName());

	public OrcWorker(Mine mine) {
		this.name = "AgentCodeName-" + ++count;
		this.mine = mine;

		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrcWorker [name=" + name + "]";
	}

	private void mining() {
		if (mine.getCapacity() > 0) {
			if (mine.getCapacity() > 0 & mine.getCapacity() < AMOUNT) {
				int temp = mine.getCapacity();
				this.recievedGold += temp;
				mine.setCapacity(mine.getCapacity() - temp);
				logger.info("\n" + "|------------- " + this.getName() + " taken " + temp + "gold" + " --------------|" + "\n"
						+ "|------- " + this.getName() + " is total mined: " + recievedGold + " gold" + " -------|" + "\n"
						+ "|----------------- " + "Mine storage now: " + mine.getCapacity() + " -----------------|" + "\n");
			} else {
				mine.setCapacity(mine.getCapacity() - AMOUNT);
				this.recievedGold += AMOUNT;
				// System.out.println(this.getName() + " " + mine.getCapacity());
				logger.info("\n" + "|----------- " + this.getName() + " taken " + AMOUNT + "gold" + " ------------|" + "\n"
						+ "|------ " + this.getName() + " is total mined: " + recievedGold + " gold" + " ------|" + "\n"
						+ "|--------------- " + "Mine storage now: " + mine.getCapacity() + " ---------------|" + "\n");
			}
		}
	}

	@Override
	public void run() {
		while (mine.getCapacity() > 0) {
			synchronized (mine) {
				this.mining();
				try {
					TimeUnit.SECONDS.sleep(SPEED);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 

		}
	}

}
