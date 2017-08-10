import java.util.Scanner;
// JUST IN CASE (3 maybe?)

public class Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// asks user for number of players that will participate in the game
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of players: ");
		int numPlayers = scanner.nextInt();
		while (numPlayers <= 0) {
			System.out.print("Enter a positive number of players greater than 0: ");
			numPlayers = scanner.nextInt();
		}
		System.out.println();

		// creates an array the size of however many players are playing. sets each players' balance equal to 100
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(100);
		}

		// creates a dealer object
		Dealer dealer = new Dealer();

		// this is the start of the main loop that will run an entire round
		String ans = "yes";
		while (ans.equals("yes")) {

			// sets running count to 0
			int runningCount = 0;

			// this loop tells players how much balance they have currently
			// if players have balance of 0, they will not be able to play
			for (int j = 0; j < numPlayers; j++) {
				if (players[j].canPlay() == false) {
					System.out.println("Player" + (j + 1) + "'s current balance: " + players[j].balance);
					System.out.println("Player " + (j + 1) + " cannot play anymore.");
					continue;
				}

				// asks user for what bet they want to place and sets this bet
				// in makeBet method
				System.out.print("Player" + (j + 1) + "'s current balance: " + players[j].balance);
				System.out.print(" Enter player" + (j + 1) + "'s bet: ");
				double currBet = scanner.nextDouble();
				while ((currBet <= 0) || (currBet > players[j].balance)) {
					System.out.print("Bet must be greater than 0 and less than current balance: ");
					currBet = scanner.nextDouble();
				}
				players[j].makeBet(currBet);
			}

			System.out.println();

			// creates a new deck and shuffles it
			Deck deck = new Deck();
			deck.shuffle();

			// deals two face-up cards to each player who is currently in game
			for (int m = 0; m < numPlayers; m++) {
				if (players[m].canPlay() == false) {
					continue;
				}

				// deals face up card and calculates value of hand of the two cards
				// if one card is an ace, it increments the value by 10
				Card card1 = deck.deal();
				System.out.println(card1 + " was dealt to Player" + (m + 1));
				if (card1.face == 1) {
					players[m].addToCounter(card1);
					players[m].counter += 10;
					players[m].hasAnACE = true;
				} else {
					players[m].addToCounter(card1);
				}
				if ((card1.face >= 2) && (card1.face <= 6)) {
					runningCount++;
				} else if (((card1.face >= 10) && (card1.face <= 13)) || (card1.face == 1)) {
					runningCount--;
				} else {

				}
				Card card2 = deck.deal();
				System.out.println(card2 + " was dealt to Player" + (m + 1));
				System.out.println();

				if ((card2.face >= 2) && (card2.face <= 6)) {
					runningCount++;
				} else if (((card2.face >= 10) && (card2.face <= 13)) || (card2.face == 1)) {
					runningCount--;
				} else {

				}

				if ((card1.face == card2.face) && (players[m].balance >= players[m].bet)) {
					players[m].canSplit = true;
				}

				// checks if player already has an ace so value does not
				// increment again with an ace
				if ((players[m].hasAnACE == false) && (card2.face == 1)) {
					players[m].counter += 10;
					players[m].hasAnACE = true;
				} else {
					players[m].addToCounter(card2);
				}
			}

			// deals the dealer's cards - 1 face up and 1 face down
			Card card1 = deck.deal(); // first card - face up
			if (card1.face == 1) {
				dealer.addToFaceUpCounter(card1);
				dealer.faceUpCounter += 10;
			} else {
				dealer.addToFaceUpCounter(card1);
			}
			
			// increments running count according to displayed cards
			if ((card1.face >= 2) && (card1.face <= 6)) {
				runningCount++;
			} else if (((card1.face >= 10) && (card1.face <= 13)) || (card1.face == 1)) {
				runningCount--;
			} else {

			}
			
			System.out.println(card1.toString() + " was dealt to dealer.");	// displays first card of dealer

			Card card2 = deck.deal(); // dealer's second card - face down
			if ((card1.face != 1) && (card2.face == 1)) {
				dealer.addToFaceDownCounter(card2);
				dealer.faceDownCounter += 10;
			} else {
				dealer.addToFaceDownCounter(card2);
			}

			System.out.println("The dealer's face up card value: " + dealer.faceUpCounter);

			System.out.println();

			// this loop asks each player if they want to 'hit' or 'stay' and
			// depending on the decision cards are dealt and value is incremented as such
			for (int j = 0; j < numPlayers; j++) {
				if (players[j].canPlay() == false) { // checks to see if a specific player can play
					continue;
				}
				boolean doneSplit = false; // resets this condition so the while loop can run for each player 

				String decision = "";
				while ((decision.equals("stand") == false) && (doneSplit == false)) { // loop runs as long as player doesn't say 'stand'
																					 // and player is done splitting (if he can)
					System.out.println("Player" + (j + 1) + "'s current hand value: " + players[j].counter);
					
					// gives hints regarding insurance - checks running count (counts cards) and then determines decision
					if (dealer.faceUpCounter == 11) {
						
						System.out.print("Would you like a hint regarding insurance? Enter 'yes' or 'no': ");
						String insuranceHint = scanner.next().toLowerCase();
						if (insuranceHint.equals("yes") == true) {
							System.out.println("The running count is: " + runningCount);
							if (runningCount >= 0) {
								System.out.println(
										"Since running count is higher than 0, you should buy insurance, as dealer might get a high card.");
							} else {
								System.out.println(
										"Since running count is lower than 0, you should most likely not buy insurance as dealer might have a lower value for second card.");
							}
						}
						
						
						// asks player if he wants to buy insurance (if dealer has ACE)
						System.out.print(
								"Player" + (j + 1) + ", would you like to buy insurance? Enter 'yes' or 'no': ");
						String insuranceAns = scanner.next().toLowerCase();
						while ((insuranceAns.equals("yes") != false) && (insuranceAns.equals("no") != false)) {
							System.out.println("Enter 'yes' to buy insurance, otherwise enter 'no'");
							insuranceAns = scanner.next().toLowerCase();
						}
						
						// if player says yes to buy insurance, player is asked to enter insurance bet - also displays max the player can enter
						if (insuranceAns == "yes") {
							double maxInsurance = Math.min((players[j].bet / 2), players[j].balance);
							System.out.print(
									"How much insurance would you like to buy? (Max: " + maxInsurance + ") : ");
							double insuranceBet = scanner.nextDouble();
							while ((insuranceBet > maxInsurance) || (insuranceBet < 0)) {
								System.out.println("Enter valid insurance bet. (Max: " + maxInsurance + ") : ");
								insuranceBet = scanner.nextDouble();
							}
							players[j].insurance = insuranceBet;
							players[j].balance -= insuranceBet;
						}
					}
					
					// determines if player can split and asks if player wants a hint regarding splitting
					
					if (players[j].canSplit == true) {
						
						System.out.print("Would you like a hint regarding splitting? Enter 'yes' or 'no': ");
						String splitHint = scanner.next().toLowerCase();
						
						// gives a hint if player says yes using value of player's hand
						if (splitHint.equals("yes")) {
							if (players[j].counter >= 18) {
								System.out.println("You should not split since your hand value is at least 18.");
							} else {
								System.out.println(
										"You should split your hand as you have a hand value of lower than 18 and a high chance of winning one hand.");
							}
						}
						
						
						// asks player if he wants to split
						System.out.print("Would you like to split? Enter 'yes' or 'no': ");
						String splitDecision = scanner.next();
						splitDecision = splitDecision.toLowerCase();
						while ((splitDecision.equals("yes") == false) && (splitDecision.equals("no") == false)) {
							System.out.print("Enter 'yes' to split, otherwise enter 'no': ");
							splitDecision = scanner.next().toLowerCase();
						}
						
						// if player decides to split, it asks if player wants hint regarding splitting 
						if (splitDecision.equals("yes") == true) {
							players[j].balance -= players[j].bet;
							players[j].Split();
							decision = "";
							while (decision.equals("stand") == false) {
								System.out.println(
										"Player" + (j + 1) + "'s current 1st hand value: " + players[j].counter);
								System.out.print("Would you like a hint regarding your 1st hand? Enter 'yes' or 'no': ");
								String splitHint2 = scanner.next().toLowerCase();
								
								// gives hint regarding splitting if player says 'yes'
								if(splitHint2.equals("yes") == true) {
									if((runningCount > 0) && (players[j].counter >= 16) ) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You should not hit anymore as you might get a high card");
									} else if ((runningCount > 0) && (players[j].counter < 15)) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You should hit as you will most likely get a high card.");
									} else if ((runningCount < 0) && (players[j].counter >= 16)) {
										System.out.println("You should not hit anymore or you may bust.");
									} else if ((runningCount < 0) && (players[j].counter < 16)) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You have a high chance of getting a lower card. You may hit.");
									}
								}
								
								// starts to deal cards on player's 1st split hand 
								System.out.print("Player" + (j + 1) + ", enter 'hit' or 'stand': ");
								decision = scanner.next();
								decision = decision.toLowerCase();
								if (decision.equals("hit")) {
									Card cardx = deck.deal();
									runningCount = cardx.getValue();
									System.out.println(cardx + " was dealt to Player" + (j + 1) + "'s 1st hand.");
									
									// determines if player gets an ACE so as to properly increase/decrease the value by 10
									if ((players[j].hasAnACE == false) && (cardx.face == 1)) {
										players[j].addToCounter(cardx);
										players[j].counter += 10;
										players[j].hasAnACE = true;
									} else {
										players[j].addToCounter(cardx);
									}
									if ((players[j].hasAnACE == true) && (players[j].counter > 21)
											&& (players[j].subtracted == false)) {
										players[j].counter -= 10;
										players[j].subtracted = true;
									}
									if (players[j].isBust()) { // notifies player if 1st hand is bust
										System.out.println("Player" + (j + 1) + "'s current 1st hand value: "
												+ players[j].counter);
										System.out.println("Player" + (j + 1) + "'s 1st hand busts!");
										System.out.println();
										break;
									}
								}
							}

							decision = "";
							
							// moves on to player's 2nd hand
							while (decision.equals("stand") == false) {
								System.out.println(
										"Player" + (j + 1) + "'s current 2nd hand value: " + players[j].secondCounter);
								
								// asks player if player wants a hint
								System.out.println("Would you like a hint regarding your 2nd hand? Enter 'yes' or 'no': ");
								String needsHint = scanner.next().toLowerCase();
								
								// gives a hint
								if(needsHint.equals("yes") == true) {
									if((runningCount > 0) && (players[j].secondCounter >= 16) ) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You should not hit anymore as you might get a high card");
									} else if ((runningCount > 0) && (players[j].secondCounter < 15)) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You should hit as you will most likely get a high card.");
									} else if ((runningCount < 0) && (players[j].secondCounter >= 16)) {
										System.out.println("You should not hit anymore or you may bust.");
									} else if ((runningCount < 0) && (players[j].secondCounter < 16)) {
										System.out.println("The running count is " + runningCount);
										System.out.println("You have a high chance of getting a lower card. You may hit.");
									}
								}
								
								// asks player to hit or stand on 2nd hand
								System.out.print("Player" + (j + 1) + ", enter 'hit' or 'stand': ");
								decision = scanner.next();
								decision = decision.toLowerCase();
								if (decision.equals("hit")) {
									Card cardx = deck.deal();
									runningCount = cardx.getValue();
									System.out.println(cardx + " was dealt to Player" + (j + 1) + "'s 2nd hand.");
									
									// determines if any of the cards were an ACE so as to properly decrease or increase value by 10 
									if ((players[j].secondHasAnACE == false) && (cardx.face == 1)) {
										players[j].secondAddToCounter(cardx);
										players[j].secondCounter += 10;
										players[j].secondHasAnACE = true;
									} else {
										players[j].secondAddToCounter(cardx);
									}
									if ((players[j].secondHasAnACE == true) && (players[j].secondCounter > 21)
											&& (players[j].secondSubtracted == false)) {
										players[j].secondCounter -= 10;
										players[j].secondSubtracted = true;
									}
									if (players[j].secondIsBust()) { // checks if player's 2nd hand is bust
										System.out.println("Player" + (j + 1) + "'s current 2nd hand value: "
												+ players[j].secondCounter);
										System.out.println("Player" + (j + 1) + "'s second hand busts!");
										System.out.println();
										break;
									}
								}
							}
						}
						players[j].canSplit = false;
						doneSplit = true;
					}

					if (players[j].secondCounter > 0) {
						players[j].canDoubleDown = false;
					}
					
					// determines if players can double down depending on balance and asks if player wants a hint  
					if ((players[j].canDoubleDown == true) && (players[j].bet <= players[j].balance)) {
						
						System.out.print("Do you want a hint regarding doubling down? Enter 'yes' or 'no': ");
						String hintDD = scanner.next().toLowerCase();
						
						// gives hint regarding doubling down if player says 'yes'
						if(hintDD.equals("yes") == true) {
							if((runningCount > 0) && (players[j].counter <= 11)) {
								System.out.println("This would be an ideal time to double down as running count is " + runningCount + " and you may get high card.");
							} else if ((runningCount > 0) && (players[j].counter > 11)) {
								System.out.println("Since running count is " + runningCount + ", you might bust with your current hand. Do not double down.");
							} else if ((runningCount <= 0) && (players[j].counter <= 11)) {
								System.out.println("The running count is " + runningCount + ", so you should not double down as you may get a very low card.");
							} else if ((runningCount < 0) && ((players[j].counter > 11) && (players[j].counter <=16))) {
								System.out.println("The running count is " + runningCount + " so your next card will be a lower card, and you may not bust. You could double down." );
							} else {
								System.out.println("You should not double down as you have a perfect value right now.");
							}
							
						}
						
						
						// asks player if the player wants to double down 
						System.out.print("Would you like to double down? Enter 'yes' or 'no': ");
						String ansToDD = scanner.next().toLowerCase();
						while ((ansToDD.equals("yes") == false) && (ansToDD.equals("no") == false)) {
							System.out.print("Enter 'yes' to double down, otherwise enter 'no': ");
							ansToDD = scanner.next();
						}
						
						// if player wants to double down, the player has another bet and is given only one card
						if (ansToDD.equals("yes") == true) {
							players[j].balance -= players[j].bet;
							players[j].bet = (2 * players[j].bet);
							Card cardD = deck.deal();
							System.out.println(cardD + " was dealt to Player" + (j + 1));
							if ((players[j].hasAnACE == false) && (cardD.face == 1)) {
								players[j].addToCounter(cardD);
								players[j].counter += 10;
								players[j].hasAnACE = true;
							} else {
								players[j].addToCounter(cardD);
							}
							if ((players[j].hasAnACE == true) && (players[j].counter > 21)
									&& (players[j].subtracted == false)) {
								players[j].counter -= 10;
								players[j].subtracted = true;
							}
							System.out.println("Player" + (j + 1) + "'s current hand value: " + players[j].counter);
							if (players[j].counter > 21) {
								System.out.println("Player" + (j + 1) + " busts!");
							}
							break;
						}
					} else if ((players[j].canDoubleDown == true) && (players[j].bet > players[j].balance)) {
						System.out.println("Player" + (j + 1) + " has insufficient funds to double down.");
					}
					
					// asks player if he wants a hint regarding his hand
					if (players[j].secondCounter == 0) {
						
						System.out.print("Would you like a hint regarding your hand? Enter 'yes' or 'no': ");
						String needsHint = scanner.next().toLowerCase();
						if(needsHint.equals("yes")) {
							if (runningCount > 0) {
								System.out.println("The running count is " + runningCount + ". Next card will probably be a high value card. Hit if hand value is low.");
							} else {
								System.out.println("The running count is " + runningCount + ". Next card will probably be a lower value card. Hit if hand value is low to mid-high.");
							}
						}
						
						
						// asks player to 'hit' or 'stand'
						System.out.print("Player" + (j + 1) + ", enter 'hit' or 'stand': ");
						decision = scanner.next();
						decision = decision.toLowerCase();
						if (decision.equals("hit")) {
							// deals a new card to a player and checks if it's an ace to increment by 10
							Card cardx = deck.deal();
							runningCount = cardx.getValue();
							System.out.println(cardx + " was dealt to Player" + (j + 1));
							if ((players[j].hasAnACE == false) && (cardx.face == 1)) {
								players[j].addToCounter(cardx);
								players[j].counter += 10;
								players[j].hasAnACE = true;
							} else {
								players[j].addToCounter(cardx);
							}
						}

						// checks if player has an ace and if hand value is over value of over 21 so automatically decreases by 10
						if ((players[j].hasAnACE == true) && (players[j].counter > 21)
								&& (players[j].subtracted == false)) {
							players[j].counter -= 10;
							players[j].subtracted = true;
						}

						// checks if the player's hand value is over 21 and prints out 'Busts!' if it is
						if (players[j].isBust()) {
							System.out.println("Player" + (j + 1) + "'s current hand value: " + players[j].counter);
							System.out.println("Player" + (j + 1) + " Busts!");
							System.out.println();
							break;
						}
					}
					players[j].canDoubleDown = false;
				}
				System.out.println();
			}
			System.out.println();

			// notifies all players of final hand values and status before
			// moving on to dealer
			for (int j = 0; j < numPlayers; j++) {
				if (players[j].canPlay() == false) {
					continue;
				}
				if ((players[j].isBust() == false) && players[j].secondCounter == 0) {
					System.out.println("Player" + (j + 1) + "'s hand value: " + players[j].counter);
				} else if (players[j].secondCounter > 0) {
					if (players[j].counter > 21) {
						System.out.println("Player" + (j + 1) + "'s 1st hand has busted.");
					} else {
						System.out.println("Player" + (j + 1) + "'s 1st hand value: " + players[j].counter);
					}
					if (players[j].secondCounter > 21) {
						System.out.println("Player" + (j + 1) + "'s 2nd hand has busted.");
					} else {
						System.out.println("Player" + (j + 1) + "'s 2nd hand value: " + players[j].secondCounter);
					}
				} else {
					System.out.println("Player" + (j + 1) + " has busted.");
				}
			}

			System.out.println();

			// adds up dealer's face up card value and face down card value
			System.out.println(card2 + " is dealer's face down card.");
			dealer.flipOver();
			System.out.println("Dealer's hand value: " + dealer.faceUpCounter);

			System.out.println();

			// notifies players who placed insurance if they won or lost insurance bet
			for (int i = 0; i < players.length; i++) {
				if (players[i].insurance != 0) {
					if (card2.getValue() == 10) {
						players[i].balance += (players[i].insurance * 2);
						System.out.println(
								"Player" + (i + 1) + ", wins " + (players[i].insurance * 2) + " from insurance.");
					} else {
						System.out.println(
								"Player" + (i + 1) + ", loses " + (players[i].insurance * 2) + " from insurance.");
					}
				}
			}

			System.out.println();

			// checks to see if all players have busted
			boolean playersAllBust = true;
			for (int j = 0; j < numPlayers; j++) {
				if (players[j].canPlay() == false) {
					continue;
				}
				if ((players[j].isBust() == false)
						|| ((players[j].secondIsBust() == false) && (players[j].secondCounter > 0))) {
					playersAllBust = false;
					break;
				}
			}

			// if player has busted, then dealer automatically wins
			if (playersAllBust == true) {
				System.out.println();
				System.out.println("The dealer wins!");
				for (int j = 0; j < numPlayers; j++) {
					if (players[j].canPlay() == false) {
						continue;
					}
					players[j].updateBalance("lose");
				}
			} else { // otherwise, the dealer will continue to play until end
				while (dealer.faceUpCounter < 17) { // dealer will play until he
													// has a value of 17 or more
					System.out.println("The dealer hits. ");
					Card card4 = deck.deal(); // deals a new card to dealer
					System.out.println(card4 + " was dealt to the dealer.");
					if ((card1.face != 1) && (card2.face != 1) && (card4.face == 1)) {
						dealer.addToFaceDownCounter(card4);
						dealer.faceUpCounter += 10;
					} else {
						dealer.addToFaceUpCounter(card4);
					}
					if (dealer.faceUpCounter > 21) { // checks to see if dealer has busted
						System.out.println("Dealer's current hand value: " + dealer.faceUpCounter);
						System.out.println("The dealer busts!");
						System.out.println();
						System.out.println("Round is over.");
						System.out.println();
						// updates all players' balances depending on whether
						// they won or lost
						for (int j = 0; j < numPlayers; j++) {
							if (players[j].canPlay() == false) {
								continue;
							} else if (players[j].secondCounter > 0) {
								if (players[j].isBust() == true) {
									players[j].updateBalance("lose");
									System.out.println("Player" + (j + 1) + "'s 1st hand loses.");
								} else {
									players[j].updateBalance("win");
									System.out.println("Player" + (j + 1) + "'s 1st hand wins.");
								}
								if (players[j].secondIsBust() == true) {
									players[j].updateBalance("lose");
									System.out.println("Player" + (j + 1) + "'s 2nd hand loses.");
								} else {
									players[j].updateBalance("win");
									System.out.println("Player" + (j + 1) + "'s 2nd hand wins.");
								}
							} else {
								if (players[j].isBust() == true) {
									players[j].updateBalance("lose");
									System.out.println("Player" + (j + 1) + " loses.");
								} else {
									players[j].updateBalance("win");
									System.out.println("Player" + (j + 1) + " wins.");
								}
							}
						}
					} else { // dealer keeps on playing
						System.out.println("Dealer's current hand value: " + dealer.faceUpCounter);
					}
				}

				System.out.println();

				// if dealer did not bust and has a hand value of 17 or over, it
				// will decide
				// which players have won or lost and will update their balances
				if (dealer.isBust() == false) {
					System.out.println("Round is over.");
					System.out.println();
					for (int j = 0; j < numPlayers; j++) {
						if (players[j].canPlay() == false) {
							continue;
						}
						if (players[j].secondCounter > 0) {
							if (players[j].counter == dealer.faceUpCounter) {
								players[j].updateBalance("tie");
								System.out.println("Player" + (j + 1) + "'s 1st hand ties.");
							}
							if (players[j].secondCounter == dealer.faceUpCounter) {
								players[j].updateBalance("tie");
								System.out.println("Player" + (j + 1) + "'s 2nd hand ties.");
							}
							if ((players[j].isBust() == true) || (players[j].counter < dealer.faceUpCounter)) {
								players[j].updateBalance("lose");
								System.out.println("Player" + (j + 1) + "'s 1st hand loses.");
							}
							if ((players[j].secondIsBust() == true)
									|| (players[j].secondCounter < dealer.faceUpCounter)) {
								players[j].updateBalance("lose");
								System.out.println("Player" + (j + 1) + "'s 2nd hand loses.");
							}
							if ((players[j].isBust() == false) && (players[j].counter > dealer.faceUpCounter)) {
								players[j].updateBalance("win");
								System.out.println("Player" + (j + 1) + "'s 1st hand wins.");
							}
							if ((players[j].secondIsBust() == false)
									&& (players[j].secondCounter > dealer.faceUpCounter)) {
								players[j].updateBalance("win");
								System.out.println("Player" + (j + 1) + "'s 2nd hand wins.");
							}
						} else {
							if (players[j].counter == dealer.faceUpCounter) {
								players[j].updateBalance("tie");
								System.out.println("Player" + (j + 1) + " ties.");
							} else if ((players[j].isBust() == true) || (players[j].counter < dealer.faceUpCounter)) {
								players[j].updateBalance("lose");
								System.out.println("Player" + (j + 1) + " loses.");
							} else {
								players[j].updateBalance("win");
								System.out.println("Player" + (j + 1) + " wins.");

							}
						}
					}
				}

			}

			System.out.println();

			// resets all players bets and counter
			for (int j = 0; j < numPlayers; j++) {
				players[j].Reset();
			}
			dealer.Reset(); // resets dealer's counter

			// after round is over, displays each player's balance
			System.out.println();
			for (int i = 0; i < numPlayers; i++) {
				System.out.println("Player" + (i + 1) + " balance: " + players[i].balance);
			}
			System.out.println();

			// asks if remaining players want to start next round
			System.out.print("Start next round? Enter 'yes' or 'no': ");
			ans = scanner.next();
			while ((ans.equals("yes") == false) && (ans.equals("no") == false)) {
				System.out.print("Enter 'yes' or 'no' for next round: ");
				ans = scanner.next();
			}
			System.out.println();
		}

		// if players decide to end game, this will display their final balance
		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Player" + (i + 1) + " ending balance: " + players[i].balance);
		}

	}

}