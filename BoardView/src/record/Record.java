package record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Constants;
import ctrl.Controller;

public class Record {

	

	public List<String> sequence = new ArrayList<String>();
	public List<Rule> rules = new ArrayList<Rule>();

	public void recordAction(String action) {
		sequence.add(action);
		Controller.updateActionList(action);
	}
	
	public void recordRule(String ifCondition, String then) {
		rules.add(new Rule(ifCondition, then));
		saveRules();
		Controller.updateRuleList(ifCondition, then);
	}
	
	public void deleteRules() {
		rules = new ArrayList<Rule>();
		saveRules();
		Controller.emptyRuleList();
	}
	

	public List<String> getSequenz() {
		try {
			saveStrategy(Constants.FileNames.strategy, sequence);
		} catch (IOException e) {
			//TODO Dörsam: was soll im Fehlerfall passieren?
			e.printStackTrace();
		}
		return sequence;
	}

	public List<Rule> getRules() {
		return rules;
	}
	
	public void saveRules() {
		try {
			saveRules(Constants.FileNames.rules, rules);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveStrategy(String filename, List<String> sequenz)
			throws IOException {
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		Iterator<String> i = sequenz.iterator();
		String zeile;

		while (i.hasNext()) {
			zeile = i.next();
			pw.println(zeile);
		}
		pw.flush();
		pw.close();
	}
	
	private void saveRules(String filename, List<Rule> rules)
			throws IOException {
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		Iterator<Rule> i = rules.iterator();
		Rule rule;

		while (i.hasNext()) {
			rule = i.next();
			pw.println(rule.getIfCond());
			pw.println(rule.getThen());
		}
		pw.flush();
		pw.close();
	}

	public void readStrategy(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		String zeile;
		while ((zeile = br.readLine()) != null) {
			sequence.add(zeile);
		}
		br.close();
	}
	
	public void readRules(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		String zeile;
		while ((zeile = br.readLine()) != null) {
			String zeile2;
			zeile2 = br.readLine();
			rules.add(new Rule(zeile, zeile2));
		}
		br.close();
	}
}
