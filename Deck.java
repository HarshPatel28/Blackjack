// This class represents the deck of cards from which cards are dealt to players.
import java.util.Random;
public class Deck
{
	Card[] listOfCards = new Card[52];
	
	
	// This constructor builds a deck of 52 cards.
	public Deck()
	{
		int a = 0;
		for(int i = 1; i <= 13; i++){
			for(int j = 0; j < 4; j++) {
				Card card = new Card(j, i);
				listOfCards[a] = card;
				a++;
			}
		}
	}

	
	// This method takes the top card off the deck and returns it.
	public Card deal()
	{
		for(int i = 0; i < listOfCards.length; i++) {
			if(listOfCards[i] != null) {
				Card placeHolder = listOfCards[i];
				listOfCards[i] = null;
				return placeHolder;
			}
		}
		return null;
	}
	
	
	// this method returns true if there are no more cards to deal, false otherwise
	public boolean isEmpty()
	{
		for(int i = 0; i < listOfCards.length; i++) {
			if(listOfCards[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	//this method puts the deck in some random order 
	public void shuffle(){
		Random randX = new Random();
		Card[] mixed = new Card[52];
		for(int i = 0; i < mixed.length; i++) {
			int x = randX.nextInt(52);
			while(listOfCards[x] == null) {
				x = randX.nextInt(52);
			}
			mixed[i] = listOfCards[x];
			listOfCards[x] = null;
		}
		listOfCards = mixed;
		
	}
	
}