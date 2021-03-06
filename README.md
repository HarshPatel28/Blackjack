# Blackjack

## What is Blackjack ##
  This program is a multiplayer Blackjack game which allows multiple players to play against a built in dealer. In addition to the basic rules, the game contains advanced features that allow doubling down, splitting, and buying insurance. The players start out with a balance of 100 and the game runs until all players run out of money or if the players decide to quit the game after responding 'No' when asked if they want to continue the game after a round. At the end of the game, the game shows total winnings for each player. Throughout the game, a player can ask for a hint if the player is unsure of what to do, and the game will suggest whether the player should double down, split (if possible), buy insurance (if possible), hit, or stand depending on probabilities of card counting. 

## Why Blackjack ##
  This program was created to solidify knowledge of object-oriented programming and understand how different classes can work together to create one whole program. The program contains a class for a single Card, a Deck, a Dealer, a Player, and the main Blackjack driver class that runs the game. Creating this game strengthened my knowledge of how objects work in Java.
  
## Problems/Struggles ##
  There were a few struggles that came with creating this game. Adding the hinting system was the biggest struggle as I had to keep track of all the "face up" cards that were already played and determine the running count and give advice based on that. Another struggle was keeping track of who has an ACE, for if they bust, the ACE's value can go back being 1 instead of 11, and they can have another shot at hitting and not being bust. Lastly, creating special cases for when certain users could split and them deciding to do so and certain players buying insurance when possible took some time as well, as they weren't playing like the "regular" players anymore, with more money at stake.
