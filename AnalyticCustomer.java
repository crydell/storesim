public class AnalyticCustomer extends Customer{

    public AnalyticCustomer(int bornTime, int groceries){
	super(bornTime, groceries);
    }

    protected int getRegisterScore(Register register){
	if (register.isOpen()){
	    int score = 0;
	    for(Customer c : register){
		score += c.groceries;
	    }
	    return score;
	} else {
	    return -1;
	}
    }

    public String toString(){
	return "8";
    }
}
