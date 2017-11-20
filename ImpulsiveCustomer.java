/**
 * A customer with a chance of adding
 * more groceries while checking out.
 *
 * @version %I%, %G%
 */

public class ImpulsiveCustomer extends Customer {
    private int impulsivity;

    /**
     * Default constructor.
     *
     * @param bornTime the time at which the customer stood in the queue
     * @param groceries the amount of groceries the customer starts off with
     * @param impulsivity the percentage chance that the customer will add a grocery
     */
    public ImpulsiveCustomer(int bornTime, int groceries, int impulsivity){
	super(bornTime, groceries);
	this.impulsivity = impulsivity;
    }

    /**
     * Serve the customer with a possibility of them adding a grocery.
     */
    public void serve(){
	if (Math.random() * 100 < this.impulsivity){
	    this.groceries++;
	}
	super.serve();
    }

    public String toString(){
	return "O";
    }
}
