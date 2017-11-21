public class Simulator{

    private static int readRangeInteger(String prompt, int minimum, int maximum){
	System.out.println(prompt);
	String input = System.console().readLine();

	while (true){
	    try {
		if (!input.matches("^-?[0-9]+$")){
		    System.out.println("Please enter a number.");
		} else if (Integer.parseInt(input) < minimum){
		    System.out.println("The smallest number you can enter is "+minimum);
		} else if (Integer.parseInt(input) > maximum){
		    System.out.println("The biggest number you can enter is "+maximum);
		} else {
		    int result = Integer.parseInt(input);
		    assert result >= minimum;
		    assert result <= maximum;
		    return result;
		}
	    } catch(NumberFormatException nfe) {
		System.out.println("That number is far too big. Try something smaller.");
	    }
	    input = System.console().readLine();
	}
    }
    
    public static void main(String args[]){

	int amountOfRegisters = readRangeInteger("How many registers would you like?", 1, Integer.MAX_VALUE);
	int intensity = readRangeInteger("How intense would you like the store to be?", 0, 100);
	int maxGroceries = readRangeInteger("How many groceries can a customer start with?", 1, Integer.MAX_VALUE);
	int thresholdForNewRegister = readRangeInteger("How long can the average queue get before a new one opens?", 1, Integer.MAX_VALUE);
	int simLength = readRangeInteger("How long should the simulation run for?", 0, Integer.MAX_VALUE);
	
	Simulation sim = new Simulation(amountOfRegisters, intensity, maxGroceries, thresholdForNewRegister);

	for (int i = 0; i < simLength; i++){
	    sim.step();
	    System.out.println("\033[2J\033[;H" + sim.toString());
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
	    }
	}
    }
}
