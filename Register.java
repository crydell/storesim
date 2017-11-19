/**
 * A register used for processing {@link Customer}s.
 * The register keeps a queue of Customers and serves 
 * them when they are at the front of the queue.
 *
 * @version %I%, %G%
 */
public class Register{

    private boolean open;
    private Queue<Customer> queue = new Queue<Customer>();

    /**
     * Default constructor.
     */
    public Register(){
    }

    /**
     * Constructor with specified open state.
     *
     * @param open whether or not the register starts open
     */
    public Register(boolean open){
	this.open = open;
    }

    /**
     * Open the register to customers.
     */
    public void open(){
	this.open = true;
    }

    /**
     * Close the register to customers.
     */
    public void close(){
	this.open = false;
    }

    /**
     * Check if the register is currently open to customers.
     *
     * @return <code>true</code> if the register is open
     */
    public boolean isOpen(){
	return this.open;
    }

    /**
     * Serve the customer first in the register's queue.
     */
    public void step(){
	if (this.isOpen() && this.hasCustomers()){
	    queue.first().serve();
	}
    }

    /**
     * Check if the register has customers in its queue.
     *
     * @return <code>true</code> if the register has customers
     */
    public boolean hasCustomers(){
	return this.queue.length() == 0;
    }

    /**
     * Check if the customer first in queue is done.
     *
     * @return <code>true</code> if the first customer in queue is done
     */
    public boolean currentCustomerIsDone(){
	if (this.hasCustomers()){
	    return this.queue.first().isDone();
	} else {
	    return false;
	}
    }

    /**
     * Add a customer to the register's queue
     *
     * @param c the customer to add to the queue
     */
    public void addToQueue(Customer c){
	this.queue.enqueue(c);
    }

    /**
     * Remove the first customer in the queue and return it.
     *
     * @return the first customer in the register's queue
     */
    public Customer removeCurrentCustomer(){
	return this.queue.dequeue();
    }

    /**
     * Get the current length of the register's queue.
     *
     * @return the length of the register's queue
     */
    public int getQueueLength(){
	return this.queue.length();
    }
}
