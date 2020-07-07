package hw;

import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

	private String name;
	private Cashier cashier;

	public Client() {

	}

	public Client(String name, Cashier cashier) {
		this.name = name;
		this.cashier = cashier;

		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		while(cashier.isOpen()) {
			cashier.service(this);
		}
		
	}

	public Cashier getCashier() {
		return cashier;
	}

	public String getName() {
		return name;
	}

}
