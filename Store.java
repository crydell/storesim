/**
 * A store which contains registers that 
 * are able to serve customers.
 *
 * @version %I%, %G%
 */

public class Store{
    private Register registers[];

    /**
     * Initialize the store and open one register
     *
     * @param amountOfRegisters the amount of registers in the store
     * @throws IllegalArgumentException if amountOfRegisters < 1
     */
    public Store (int amountOfRegisters){
	if (amountOfRegisters < 1){
	    this.registers = new Register[amountOfRegisters];
	    for (Register r : this.registers){
	        r = new Register();
	    }
	    this.registers[0].open();
	} else {
	    throw new IllegalArgumentException("Store must have at least 1 customer.");
	}
    }

    /**
     * Get the average queue length of all the store's registers
     *
     * @return the average queue length
     */
    public int getAverageQueueLength(){
	int totalCustomers = 0;
	for (Register r : this.registers){
	    totalCustomers += r.getQueueLength();
	}
	return (totalCustomers / this.registers.length);
    }

    /**
     * Inserts a customer into the shortest queue
     *
     * @param c the customer to insert
     */
    public void newCustomer(Customer c){
	Register shortestQueueRegister = this.registers[0];
	
	for (Register r : this.registers){
	    if (r.getQueueLength() < shortestQueueRegister.getQueueLength()){
		shortestQueueRegister = r;
	    }
	}

	shortestQueueRegister.addToQueue(c);
    }

    /**
     * Let one time step occur in the store.
     */
    public void step(){
	for (Register r : this.registers){
	    r.step();
	}
    }

    /**
     * Open a closed register if one exists.
     */
    public void openNewRegister(){
	for (Register r : this.registers){
	    if (!r.isOpen()){
		r.open();
		break;
	    }
	}
    }

    /**
     * Get a queue of all customers currently done at the store.
     *
     * @return a queue of finished customers
     */
    public Queue<Customer> getDoneCustomers(){
	Queue<Customer> doneQueue = new Queue<Customer>();

	for (Register r : this.registers){
	    if (r.currentCustomerIsDone()){
		doneQueue.enqueue(r.removeCurrentCustomer());
	    }
	}

	return doneQueue;
    }
}
