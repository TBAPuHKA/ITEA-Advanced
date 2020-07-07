package hw;

public class McApp {

	public static void main(String[] args) {
		
		Cashier cs = new Cashier();
		
		Client cl1 = new Client("Rylay",cs);
		Client cl2 = new Client("Tiny",cs);
		Client cl3 = new Client("Vamp",cs);
		Client cl4 = new Client("FireSource",cs);
		Client cl5 = new Client("Jaggernaut",cs);
		
		while(cs.isOpen())
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//ТАКАЯ РЕАЛИЗАЦИЯ ИСХОДЯ ИЗ ТОГО ЧТО ГОВОРИЛИ НЕ ДЕЛАТЬ КАССИРА ПОТОКОМ, ЕСТЬ ВИДЕОФИКСАЦИЯ

	}
}
