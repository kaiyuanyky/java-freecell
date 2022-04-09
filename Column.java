import java.util.*;

/**
 * Creates an Array with thirteen ArrayList containing of four piles and nine columns
 */
public class Column extends Card{
	/**
	 * Number of cards to be distributed to each column
	 */
	private static int numCardsPerCol = 7;
	
	/**
	 * Array with four piles and nine columns
	 */
	public ArrayList<String>[] columns = new ArrayList[13];
	
	/**
	 * Assigns seven cards for first column to fourth column, and six cards for fifth column to eighth column while ninth column is left empty
	 */
	public Column(){
		Card _cards = new Card();
		Stack<String> deck = _cards.getStackCards();
		
		//Create Array of 13 columns(ArrayList)
		//Columns: index 0-8, Piles: index 9-12
		//Club: index 9, Diamond: index 10, Heart: index 11, Spade: index 12
		for(int i = 0; i < 13; i++)
			columns[i] = new ArrayList<>();
		
		for(int i = 0; i < 8; i++){
			if(i == 4) // 7 cards for columns 1-4, 6 cards for columns 5-8 (as in PDF)
				numCardsPerCol--;//After 4th column, 6 cards per consecutive column
				
			for(int j = numCardsPerCol; j > 0; j--)
				columns[i].add(deck.pop());
			
			if(i == 7)
				numCardsPerCol++; //set back to 7 after last column for the next usage of restarting program
		}
	}
	
	/**
	 * Returns the Array with ArrayList consisting of distributed cards
	 * @return Array with ArrayList consisting of distributed cards
	 */
	public ArrayList<String>[] getColumns(){
		return columns;
	}
	
	/**
	 * Displays the Array with four empty piles and nine columns
	 * @param arrCardsAndPiles Array with four empty piles and nine columns
	 */
	public void displayCards(ArrayList<String>[] arrCardsAndPiles){
		System.out.println("Pile   c: " + arrCardsAndPiles[9]);  //club
		System.out.println("Pile   d: " + arrCardsAndPiles[10]); //diamond
		System.out.println("Pile   h: " + arrCardsAndPiles[11]); //heart
		System.out.println("Pile   s: " + arrCardsAndPiles[12]); //spade
		
		for(int i = 0; i < 9; i++)
			System.out.println("Column " + (i+1) + ": " + arrCardsAndPiles[i]);
	}
}