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
	if (amountOfRegisters > 0){
	    this.registers = new Register[amountOfRegisters];
	    for (int i = 0; i < amountOfRegisters; i++){
	        registers[i] = new Register();
	    }
	    this.registers[0].open();
	} else {
	    throw new IllegalArgumentException("Store must have at least 1 register.");
	}
    }

    /**
     * Get the average queue length of all the store's registers
     *
     * @return the average queue length
     */
    public int getAverageQueueLength(){
	int totalCustomers = 0;
	int openRegisters = 0;
	for (Register r : this.registers){
	    if (r.isOpen()){
		openRegisters++;
		totalCustomers += r.getQueueLength();
	    }
	}
	return (totalCustomers / openRegisters);
    }

    /**
     * Let a customer enter its desired queue
     *
     * @param c the customer to insert
     */
    public void newCustomer(Customer c){
	c.chooseQueue(this.registers);
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

    public String toString(){
	String result = "";
	for (Register r : this.registers){
	    result += r.toString() + "\n";
	}
	return result;
    }
}
