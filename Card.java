// This class represents one playing card.
public class Card
{

	public static final int SPADES   = 0;
	public static final int HEARTS   = 1;
	public static final int CLUBS    = 2;
	public static final int DIAMONDS = 3;

	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;

	
	public String toString() {
		String faceOfCard = "";
		String suitOfCard = "";
		String nameOfCard = "";
		if(face == 1) {
			faceOfCard = "ACE";
		} else if(face == 2) {
			faceOfCard = "TWO";
		} else if(face == 3) {
			faceOfCard = "THREE";
		} else if(face == 4) {
			faceOfCard = "FOUR";
		} else if(face == 5) {
			faceOfCard = "FIVE";
		} else if(face == 6) {
			faceOfCard = "SIX";
		} else if(face == 7) {
			faceOfCard = "SEVEN";
		} else if(face == 8) {
			faceOfCard = "EIGHT";
		} else if(face == 9) {
			faceOfCard = "NINE";
		} else if(face == 10) {
			faceOfCard = "TEN";
		} else if(face == 11) {
			faceOfCard = "JACK";
		} else if(face == 12) {
			faceOfCard = "QUEEN";
		} else {
			faceOfCard = "KING";
		} 
		
		if(suit == 0) {
			suitOfCard = "SPADES";
		} else if(suit == 1) {
			suitOfCard = "HEARTS";
		} else if(suit == 2) {
			suitOfCard = "CLUBS";
		} else {
			suitOfCard = "DIAMONDS";
		}
		
		nameOfCard = faceOfCard + " of " + suitOfCard;
		
		return nameOfCard;
	}
	
	int suit;
	int face;
	
	// This constructor builds a card with the given suit and face, turned face down.
	public Card(int cardSuit, int cardFace)
	{
		this.suit = cardSuit;
		this.face = cardFace;
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit()
	{
		return suit;
	}
	
	// This method retrieves the face (ace through king) of this card.
	public int getFace()
	{
		return face;
	}
	
	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue()
	{
		if(face <= 9) {
			return face;
		} else if (face == ACE) {
			return 1;
		} else {
			return 10;
		}
	}

	
}