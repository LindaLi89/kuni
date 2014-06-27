package ctrl;

import java.util.Iterator;
import java.util.List;

import record.Record;
import record.Rule;
import spielbrettview.customviews.groups.ArrowControlsGroup;

public class StrategyController {
	
	private ArrowControlsGroup arrowControlsGroup;

	
	public StrategyController(ArrowControlsGroup arrowControlsGroup) {
		super();
		this.arrowControlsGroup = arrowControlsGroup;
	}



	public void fillTextView() {
		Record record = Controller.getMouseCtrl().getRecord();
		List<String> list = record.getSequenz();
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
			arrowControlsGroup.appendTextWithContents(s + "\n");
		}
		
		List<Rule> rules = record.getRules(); 
		Iterator<Rule> keyIterator = rules.iterator();
		while(keyIterator.hasNext()) {
			Rule rule = keyIterator.next();
			String key = rule.getIfCond();
			String value = rule.getThen();
			arrowControlsGroup.appendTextWithContents(key, value+ "\n");
		}
		
	}
	
	
	public void updateTextView(String text) {
		arrowControlsGroup.appendTextWithContents(text + "\n");
	}
	
	public void updateTextView(String cond, String then) {
		arrowControlsGroup.appendTextWithContents(cond, then + "\n");
	}
	
	public void resetTextViewActions() {
		arrowControlsGroup.resetText1();
	}

	public void resetTextViewRules() {
		arrowControlsGroup.resetText2();
	}
}
