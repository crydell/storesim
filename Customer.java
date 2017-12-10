//package storesim;

/**
 * A customer in a store. Has a certain
 * amount of groceries and will be considered
 * done when all the groceries have been handled.
 *
 * @version %I%, %G%
 */
public class Customer {
    private int bornTime;
    protected int groceries;

    /**
     * Default constructor.
     *
     * @param bornTime the time at which the customer stood in the queue
     * @param groceries the amount of groceries the customer starts off with
     */
    public Customer (int bornTime, int groceries){
	this.bornTime = bornTime;
	this.groceries = groceries;
    }

    /**
     * Serve the customer for one of its groceries. The amount of groceries left will decrease.
     */
    public void serve(){
	if (this.groceries > 0) {
	    this.groceries--;
	}
    }

    /**
     * Check if the customer is done in the store.
     *
     * @return <code>true</code> if the customer has no more groceries to be served for
     */
    public boolean isDone(){
	assert this.groceries >= 0;

	return this.groceries == 0;
    }

    protected int getRegisterScore(Register register){
	return register.isOpen() ? register.getQueueLength() : -1;
    }

    /**
     * Choose a register to queue for.
     *
     * @param registers an array of available registers
     */
    public void chooseQueue(Register registers[]){
	if (registers.length > 0){
	    int bestIndex = 0;
	    int bestScore = -1;

	    for(int i = 0; i < registers.length; i++){
		int currentScore = getRegisterScore(registers[i]);
		if ((currentScore < bestScore && currentScore != -1) || bestScore == -1){
		    bestScore = currentScore;
		    bestIndex = i;
		}
	    }
	    registers[bestIndex].addToQueue(this);
	}
    }

    public String toString(){
	return "@";
    }
}
