package model;

import java.util.Date;

import ctrl.Controller;

public class Score {
	protected int score;
	protected Date time;
	protected Mouse mouse;
	
	public Score() {
		this.score = 0;
	}
	
	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
	}
	
	public void resetScore(){
		this.score = 0;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getName() {
		return mouse.getName();
	}
	
	public void updateName() {
		Controller.getScoreCtrl().updateNameView();
	}

	@Override
	public String toString() {
		return "" + score;
	}
	
	
}
