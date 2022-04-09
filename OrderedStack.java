import java.util.*;

/**
 * Represents pile rules and column game rules
 */
public class OrderedStack{
	/**
	 * Array with four piles and nine columns
	 */
	public ArrayList<String>[] pilesAndCols;
	
	/**
	 * Stores multiple consecutive cards
	 */
	public ArrayList<String> multipleCards = new ArrayList<>();
	
	/**
	 * Not in use
	 */
	public OrderedStack(){}
	
	/**
	 * Initializes the piles and columns
	 * @param pilesAndCols Array with four empty piles and nine columns
	 */
	public OrderedStack(ArrayList<String>[] pilesAndCols){
		this.pilesAndCols = pilesAndCols;
	}
	
	/**
	 * Determines existence of multiple consecutive cards
	 * @param intMoveFromCol column number to move card from
	 * @param cardToMove card to move
	 * @param intMoveToCol column number to move card to
	 */
	public void checkMultipleCards(int intMoveFromCol,String cardToMove,int intMoveToCol){
		int index = pilesAndCols[intMoveFromCol-1].indexOf(cardToMove);
		if(index == pilesAndCols[intMoveFromCol-1].size() - 1){
			pilesAndCols[intMoveFromCol-1].remove(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove));
			multipleCards.add(cardToMove);
			return;
		}
		
		String nextCardToMove = pilesAndCols[intMoveFromCol-1].get(index + 1);
		
		String nextNextCardToMove = new String();
		int nextNextCardToMoveValue = 0;
		if(index + 2 <= pilesAndCols[intMoveFromCol-1].size()-1){
			nextNextCardToMove = pilesAndCols[intMoveFromCol-1].get(index + 2);
			nextNextCardToMoveValue = Integer.parseInt(checkCardValue(nextNextCardToMove.substring(1,2)));
		}
			
		int cardToMoveValue = Integer.parseInt(checkCardValue(cardToMove.substring(1,2)));
		int nextCardToMoveValue = Integer.parseInt(checkCardValue(nextCardToMove.substring(1,2)));
		
		if(cardToMoveValue - 1 == nextCardToMoveValue){
			pilesAndCols[intMoveFromCol-1].remove(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove));
			multipleCards.add(cardToMove);
			if(nextCardToMoveValue - 1 == nextNextCardToMoveValue)
				checkMultipleCards(intMoveFromCol,nextCardToMove,intMoveToCol);
			else{
				pilesAndCols[intMoveFromCol-1].remove(pilesAndCols[intMoveFromCol-1].indexOf(nextCardToMove));
				multipleCards.add(nextCardToMove);
			}
		}
	}
	
	/**
	 * Checks the value of card
	 * @param cardToMove card to move
	 * @return card to move
	 */
	public String checkCardValue(String cardToMove){
		switch(cardToMove){
			case "A": cardToMove = "1"; return cardToMove;
			case "X": cardToMove = "10"; return cardToMove;
			case "J": cardToMove = "11"; return cardToMove;
			case "Q": cardToMove = "12"; return cardToMove;
			case "K": cardToMove = "13"; return cardToMove;
			
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9": return cardToMove;
		}
		return cardToMove;
	}
	
	/**
	 * Checks the compatibility of the column card value to move from with the column card value to move to
	 * @param intMoveFromCol column number to move card from
	 * @param cardToMove card to move
	 * @param intMoveToCol column number to move card to
	 * @return true if index numbers are compatible
	 */
	public boolean checkCardIndexCompatibility(int intMoveFromCol, String cardToMove, int intMoveToCol){
		String cardInsideToCol = new String();
		int cardNumToCol = 0;
		int cardNumFromCol = 0;
		int sizeMoveTo = pilesAndCols[intMoveToCol-1].size();
		
		if(pilesAndCols[intMoveFromCol-1].size() != 0)
			cardNumFromCol = Integer.parseInt(checkCardValue(cardToMove.substring(1,2)));
		else
			return false;
		
		if(sizeMoveTo != 0){
			cardInsideToCol = pilesAndCols[intMoveToCol-1].get(sizeMoveTo-1);
			cardNumToCol = Integer.parseInt(checkCardValue(cardInsideToCol.substring(1,2)));
		}
		String cardSuit = new String();
		if(intMoveToCol==10 || intMoveToCol==11 || intMoveToCol==12 || intMoveToCol==13){
			switch(intMoveToCol){
				case 10: cardSuit = "c"; break;
				case 11: cardSuit = "d"; break;
				case 12: cardSuit = "h"; break;
				case 13: cardSuit = "s"; break;
			}
			if(cardToMove.substring(0,1).equals(cardSuit))
				return (cardNumFromCol == cardNumToCol+1);
		}

		return (cardNumFromCol == cardNumToCol-1);
	}
	
	/**
	 * Checks whether the multiple consecutive cards are of the same suit before adding to piles
	 * @return true if contains the cards of same suits
	 */
	public boolean checkSameSuit(){
		boolean sameSuit = true;
		
		for(int i = 0; i < multipleCards.size()-1; i++){
			if(!multipleCards.get(i).substring(0,1).equals(multipleCards.get(i+1).substring(0,1))){
				sameSuit = false;
			}
		}
		
		return sameSuit;
	}
	
	/**
	 * Checks whether the new card pushed into the OrderedStack follows the rules
	 * @param intMoveFromCol column number to move card from
	 * @param cardToMove card to move
	 * @param intMoveToCol column number to move card to
	 * @return Array with four piles and nine columns
	 */
	public ArrayList<String>[] push(int intMoveFromCol, String cardToMove, int intMoveToCol){
		pilesAndCols[intMoveFromCol-1].remove(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove));
		pilesAndCols[intMoveToCol-1].add(cardToMove);
		
		return pilesAndCols;
	}
	
	/**
	 * Adds multiple consecutive cards to a column, or consecutive cards of same suit to a its respective pile
	 * @param intMoveFromCol column number to move card from
	 * @param cardToMove card to move
	 * @param intMoveToCol column number to move card to
	 * @return Array with four piles and nine columns
	 */
	public ArrayList<String>[] pushMultiple(int intMoveFromCol,String cardToMove,int intMoveToCol){
		checkMultipleCards(intMoveFromCol,cardToMove,intMoveToCol);
		int sizeMultipleCards = multipleCards.size();
		
		if(intMoveToCol > 9){
			if(checkSameSuit() || intMoveFromCol < 10){
				Collections.reverse(multipleCards);
				for(int i = 0; i < sizeMultipleCards; i++){
					pilesAndCols[intMoveToCol-1].add(multipleCards.get(i));
				}
			}
			else{
				System.out.println("Error: Cards are not of the same suit!");
				for(int i = 0; i < sizeMultipleCards; i++)
					pilesAndCols[intMoveFromCol-1].add(multipleCards.get(i));
			}
				
		}

		if(checkCardIndexCompatibility(intMoveFromCol,cardToMove,intMoveToCol)){
			for(int i = 0; i < sizeMultipleCards; i++){
				pilesAndCols[intMoveToCol-1].add(multipleCards.get(i));
			}
		}
		
		return pilesAndCols;
	}
}