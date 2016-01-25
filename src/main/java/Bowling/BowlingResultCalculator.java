package Bowling;

public class BowlingResultCalculator implements BowlingGameResultCalculator {
	private int round;
	private boolean firstThrowInRound;
	private int factorCurrentRound;
	private int factorNextRound;
	private int scores;
	private int firstRollScore;

	public BowlingResultCalculator() {
		this.round = 1;
		this.firstThrowInRound = true;
		this.factorCurrentRound = 1;
		this.factorNextRound = 1;
	}
	
	public void roll(int numberOfPins) {
		if (numberOfPins < 0)
			return;
		if (this.isFinished())
			return;
		if (numberOfPins > 10)
			numberOfPins = 10;
		if (!this.firstThrowInRound && this.firstRollScore + numberOfPins > 10)
			numberOfPins = 10 - this.firstRollScore;
		this.addRoll(numberOfPins);
		if (this.isFinished()) 
			this.addScoresFromMultipliersAtEndOfGame();		
	}

	private void addRoll(int numberOfPins) {
		this.scores += numberOfPins*factorCurrentRound;
		if (this.firstThrowInRound) {
			if (numberOfPins == 10) {
				this.round++;
				this.factorCurrentRound = this.factorNextRound + 1;
				this.factorNextRound = 2;
			} else {
				this.factorCurrentRound = this.factorNextRound;
				this.factorNextRound = 1;					
				this.firstThrowInRound = false;
			}
			this.firstRollScore = numberOfPins;
		} else {
			if (this.firstRollScore + numberOfPins == 10) {
				this.factorCurrentRound = this.factorNextRound + 1;				
			} else {
				this.factorCurrentRound = this.factorNextRound;				
			}
			this.round++;
			this.factorNextRound = 1;
			this.firstThrowInRound = true;
		}	
	}
	
	private void addScoresFromMultipliersAtEndOfGame() {
		//przyjąłem założenie, że jeśli w dziesiątej rundzie gracz zbije 10 kręgli jednym rzutem, dostaje 20 punktów extra. Jeśli zbije 10 kręgli dwoma rzutami, dostaje 10 punktów ekstra
		this.scores += (10*(this.factorCurrentRound - 1));
		this.scores += (10*(this.factorNextRound - 1));
	}

	public int score() {
		return this.scores;
	}

	public boolean isFinished() {
		return this.round > 10;
	}
}