public class Simulator{
    public static void main(String args[]){
	Simulation sim = new Simulation(5, 40, 20, 5);

	for (int i = 0; i < 30; i++){
	    sim.step();
	    System.out.println("\033[2J\033[;H" + sim.toString());
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
		
	    }
	}
    }
}
