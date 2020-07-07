package hw;

public class MagicLauncher {
	
	public static void main(String[] args) {
		
		Mine mn = Mine.getMine();
		GreatHall sh = new GreatHall(mn);
		OrcWorker orc01 = new OrcWorker(mn);
		OrcWorker orc02 = new OrcWorker(mn);
		OrcWorker orc03 = new OrcWorker(mn);
		
	
	}



}
