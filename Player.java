
public class Player {
	double balance;
	double bet = 0;
	int counter = 0;
	boolean hasAnACE = false;
	boolean subtracted = false;
	boolean canSplit = false;
	int secondCounter = 0;
	boolean secondHasAnACE = false;
	boolean secondSubtracted = false;
	boolean canDoubleDown = true;
	double insurance = 0;

	public Player(double balance) {
		this.balance = balance;
	}

	public void makeBet(double bet) {
		this.bet = bet;
		balance -= bet;
	}

	public void addToCounter(Card card) {
		counter += card.getValue();
	}

	public boolean isBust() {
		if (counter > 21) {
			return true;
		} else {
			return false;
		}
	}

	public void Reset() {
		bet = 0;
		counter = 0;
		secondCounter = 0;
		hasAnACE = false;
		subtracted = false;
		canSplit = false;
		secondHasAnACE = false;
		secondSubtracted = false;
		canDoubleDown = true;
		insurance = 0;
	}

	public boolean canPlay() {
		if (balance > 0 || bet > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateBalance(String result) {
		if (result == "win") {
			balance += (bet * 2);
		} else if (result == "tie") {
			balance += bet;
		} else {

		}
	}

	public boolean subtracted() {
		return subtracted;
	}

	public boolean hasAnACE() {
		return hasAnACE;
	}

	public void Split() {
		if (hasAnACE == true) {
			secondCounter = 11;
			counter = 11;
			hasAnACE = true;
			secondHasAnACE = true;
			subtracted = false;
			secondSubtracted = false;
		} else {
			secondCounter = (counter / 2);
			counter = (counter / 2);
		}
	}

	public boolean secondHasAnACE() {
		return secondHasAnACE;
	}

	public boolean secondSubtracted() {
		return secondSubtracted;
	}

	public void secondAddToCounter(Card card) {
		secondCounter += card.getValue();
	}

	public boolean secondIsBust() {
		if (secondCounter > 21) {
			return true;
		} else {
			return false;
		}
	}

}
