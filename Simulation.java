/**
 * A simulation of a store.
 *
 * @version %I%, %G%
 */

public class Simulation{
    Store store;
    int time = 0;
    int intensity;
    int maxGroceries;
    int thresholdForNewRegister;

    /**
     * Initialize a new simulation
     *
     * @param amountOfRegisters the amount of registers in the simulated store
     * @param intensity the likelyhood of a new customer arriving in percent
     * @param maxGroceries the highest amount of groceries a customer can start with
     * @param thresholdForNewRegister the highest average queue length before a new register opens
     */
    public Simulation(int amountOfRegisters, int intensity, int maxGroceries, int thresholdForNewRegister){
	if (intensity > 100 || intensity < 0 || maxGroceries < 0 || thresholdForNewRegisters < 0){
	    throw new IllegalArgumentException();
	}
	this.store = new Store(amountOfRegisters);
	this.intensity = intensity;
	this.maxGroceries = maxGroceries;
	this.thresholdForNewRegister = thresholdForNewRegister;
    }

    private Customer createNewCustomer(){
	Customer newCustomer;
	int groceryAmount = (int) (Math.random() * this.maxGroceries);
	if (groceryAmount < 1){
	    groceryAmount = 1;
	}

	switch (((int) (Math.random() * 3)) % 3){
	case 0:
	    newCustomer = new ImpulsiveCustomer(this.time, groceryAmount,  (int) (Math.random() * 100));
	    break;
	default:
	    newCustomer = new Customer(this.time, groceryAmount);
	}

	return newCustomer;
    }

    /**
     * Let one time step occur in the simulation
     */
    public void step(){
	this.time++;

	if (Math.random() * 100 < this.intensity){
	    this.store.newCustomer(createNewCustomer());
	}

	if (this.store.getAverageQueueLength() > this.thresholdForNewRegister){
	    this.store.openNewRegister();
	}

	this.store.step();

	Queue<Customer> doneCustomers = this.store.getDoneCustomers();
    }

    public String toString(){
	return this.store.toString();
    }
}
