
public class Dealer {
	int faceUpCounter = 0;
	int faceDownCounter = 0;
	
	public void addToFaceUpCounter(Card card) {
		faceUpCounter += card.getValue();
	}
	
	public void addToFaceDownCounter(Card card) {
		faceDownCounter += card.getValue();
	}
	
	public void flipOver(){
		faceUpCounter += faceDownCounter;
		faceDownCounter = 0;
	}
	
	public boolean isBust(){
		if(faceUpCounter > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	public void Reset() {
		faceUpCounter = 0;
	}
	
}