package hw;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Cashier {

	private boolean open;
	public static final Logger LOG = Logger.getLogger(Cashier.class.getName());

	public Cashier() {
		this.open = true;
	}

	public void service(Client client) {
		
		synchronized (this) {
			System.out.println("FREE CASHIER");
			LOG.info("Cashier services " + client.getName());
			if (lottery()) {
				closeMac();
			}
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isOpen() {
		return open;
	}

	private boolean lottery() {
		if ((int) (Math.random() * 2) > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void closeMac() {
		LOG.info("McDONALDS CLOSED INTO QUARANTINE");
		open = false;

	}

}
