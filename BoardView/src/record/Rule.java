package record;

public class Rule {
	String ifCond;
	String then;
	
	
	
	public Rule(String ifCond, String then) {
		super();
		this.ifCond = ifCond;
		this.then = then;
	}
	public String getIfCond() {
		return ifCond;
	}
	@Override
	public String toString() {
		return "Rule [ifCond=" + ifCond + ", then=" + then + "]";
	}
	public String getThen() {
		return then;
	}
	
	
}
