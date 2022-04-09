import java.util.*;

/**
 * Main function of the Simple FreeCell program
 */
public class MainFunction{
	/**
	 * To get user input
	 */
	public static Scanner input = new Scanner(System.in);

	/**
	 * Not in use
	 */
	public MainFunction(){}
	
	/**
	 * Main method of the Simple FreeCell program
	 * @param args takes in null
	 */
	public static void main(String[] args){
		Card _cards = new Card();
		Column _cols = new Column();
		Stack<String> deck = _cards.getStackCards();
		ArrayList<String>[] pilesAndCols = _cols.getColumns();
		ArrayList<String> multipleCards = new ArrayList<>();
		
		String cmd = command(_cards, _cols, deck, pilesAndCols, multipleCards);
		if(cmd.equals("r")) // restart program
			main(null);
	}
	
	/**
	 * Checks the validity of first input
	 * @param moveFromCol represents the column number to move card from, or the command of exit, restart, and rotate
	 * @return returns input entered if input is valid or else returns "i"
	 */
	public static String checkMoveFromCol(String moveFromCol){
		switch(moveFromCol){
			case "x":
			case "r":
			case "rot":
			
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9": return moveFromCol;
			
			default: return "i"; // i = invalid input
		}
	}
	
	/**
	 * Checks the validity of column number to move card to
	 * @param moveToCol column number to move card to
	 * @return returns column number if valid or else returns "i"
	 */
	public static String checkMoveToCol(String moveToCol){
		switch(moveToCol){
			case "c": moveToCol = "10"; return moveToCol; //pile c is 10th index in the array
			case "d": moveToCol = "11"; return moveToCol; //pile d is 11th index in the array
			case "h": moveToCol = "12"; return moveToCol; //pile h is 12th index in the array
			case "s": moveToCol = "13"; return moveToCol; //pile s is 13th index in the array
			
			case "1": 
			case "2": 
			case "3": 
			case "4": 
			case "5": 
			case "6": 
			case "7": 
			case "8": 
			case "9": return moveToCol; //valid input, return back
			
			default: return "i"; //invalid input, return "i"
		}
	}
	
	/**
	 * Moves the last element to the front of specified column
	 * @param pilesAndCols Array with four piles and nine columns
	 * @exception InputMismatchException if non-numeric input is entered
	 * @exception ArrayIndexOutOfBoundsException if column is empty
	 */
	public static void rotate(ArrayList<String>[] pilesAndCols){
		System.out.print("Enter the column number to be rotated: ");
		try{
			int colNumToRotate = input.nextInt();
			if(colNumToRotate >= 1 && colNumToRotate <= 9) //rotate only for 9 columns, not for piles
				pilesAndCols[colNumToRotate-1].add(0,pilesAndCols[colNumToRotate-1].remove(pilesAndCols[colNumToRotate-1].size()-1));
			else
				System.out.println("Error: Column input number must be 1 to 9!");
		}
		catch(InputMismatchException ex){ //if input is not numeric
			System.out.println("Error: Column input number must be numeric!");
			input.next();
		}
		catch(ArrayIndexOutOfBoundsException ex){ //if column is empty, no more rotation
			System.out.println("Error: No more cards to rotate!");
		}
	}
	
	/**
	 * Commands of the moving card from and to columns, and piles
	 * @param _cards object of Card class
	 * @param _cols object of Column class
	 * @param deck stack of fifty-two cards
	 * @param pilesAndCols Array with four piles and nine columns
	 * @param multipleCards ArrayList with multiple consecutive cards
	 * @return input of volumn number to move card from
	 * @exception StringIndexOutOfBoundsException wrong input of one character card
	 * @exception NumberFormatException invalid card input
	 */
	public static String command(Card _cards, Column _cols, Stack<String> deck,
								 ArrayList<String>[] pilesAndCols, ArrayList<String> multipleCards)
	{								 
		String cardToMove = new String();
		String moveFromCol, moveToCol;
		int intMoveFromCol = 0, intMoveToCol = 0;

		while(true){
			System.out.println();
			_cols.displayCards(pilesAndCols);
			
			//game completed if all the piles are fully occupied
			if(pilesAndCols[9].size() == 13 &&
			   pilesAndCols[10].size() == 13 &&
			   pilesAndCols[11].size() == 13 &&
			   pilesAndCols[12].size() == 13){
				System.out.println("You won!\n");
				System.exit(0);
			}
			System.out.print("Command > ");
			
			// -------------------- START CODE FOR moveFromCol --------------------
			moveFromCol = input.next();
			moveFromCol = checkMoveFromCol(moveFromCol);
			if(moveFromCol.equals("x") || moveFromCol.equals("r")){ //exit or restart program
				switch(moveFromCol){
					case "x": System.out.println("Program exit successfully!\n"); break;
					case "r": System.out.println("Program restart successfully!\n"); break;
				}
				break;
			}
			if(moveFromCol.equals("rot")){ //rotate the specified column
				rotate(pilesAndCols);
				continue;
			}
			if(moveFromCol.equals("9") && pilesAndCols[Integer.parseInt(moveFromCol)-1].size() == 0)
				System.out.println("Error: Empty column!"); //if try to move from initial empty column 9
			if(moveFromCol.equals("i")){ // invalid input
				System.out.println("Error: Invalid input column to move from!");
				moveFromCol = input.nextLine();
				continue;
			}
			// -------------------- END CODE FOR moveFromCol --------------------
			
			
			// -------------------- START CODE FOR cardToMove --------------------
			try{
				cardToMove = input.next();
				switch(cardToMove.charAt(1)){ //to convert user's lower case input of a,x,j,q,k to uppercase
				case 'a':
				case 'x':
				case 'j':
				case 'q':
				case 'k': StringBuilder tempCardToMove = new StringBuilder(cardToMove);
						  //convert uppercase using StringBuilder, setCharAt()
						  tempCardToMove.setCharAt(1,Character.toUpperCase(cardToMove.charAt(1)));
						  //set the lower case to upper case
						  cardToMove = tempCardToMove.toString();
						  //convert back to String and assign back 
				}
				
				if(cardToMove.length() != 2) //not a valid card input if card input is not length of 2
					throw new StringIndexOutOfBoundsException("Error: Invalid card input!");
				if(!_cards.getStackCards().contains(cardToMove))
					throw new NumberFormatException("Error: Invalid card input!");
			}
			catch(StringIndexOutOfBoundsException ex){
				System.out.println(ex.getMessage());
				input.next();
				continue;
			}
			catch(NumberFormatException ex){
				System.out.println(ex.getMessage());
				input.next();
				continue;
			}
			// -------------------- END CODE FOR cardToMove --------------------
		
		
			// -------------------- START CODE FOR moveToCol --------------------
			moveToCol = input.next();
			moveToCol = checkMoveToCol(moveToCol);
			if(moveToCol.equals("i")){ //invalid input of column number to move to
				System.out.println("Error: Invalid input column to move to!");
				continue;
			}
			// -------------------- END CODE FOR moveToCol --------------------
			
			
			intMoveFromCol = Integer.parseInt(moveFromCol);
			intMoveToCol = Integer.parseInt(moveToCol);
			
			String nextCardToMove = new String();
			int cardToMoveValue = 0;
			int nextCardToMoveValue = 0;
			
			OrderedStack _checking = new OrderedStack(pilesAndCols);
			if(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove) != pilesAndCols[intMoveFromCol-1].size()-1)
			{// if card to move is not at the last index, check card value next to it to see whether there exist consecutive cards
				nextCardToMove = pilesAndCols[intMoveFromCol-1].get(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove)+1);
				cardToMoveValue = Integer.parseInt(_checking.checkCardValue(cardToMove.substring(1,2)));
				nextCardToMoveValue = Integer.parseInt(_checking.checkCardValue(nextCardToMove.substring(1,2)));
			}
			
			try{
				if(pilesAndCols[intMoveFromCol-1].indexOf(cardToMove) == pilesAndCols[intMoveFromCol-1].size()-1){
					if(intMoveToCol == 9) // move to column 9 with no restriction
						pilesAndCols = _checking.push(intMoveFromCol,cardToMove,intMoveToCol);
					else if(_checking.checkCardIndexCompatibility(intMoveFromCol,cardToMove,intMoveToCol))
						//check the card values before moving to other column
						pilesAndCols = _checking.push(intMoveFromCol,cardToMove,intMoveToCol);
					else
						System.out.println("Error: Invalid attempt to move!");
				}
				else if(cardToMoveValue - 1 == nextCardToMoveValue &&
						_checking.checkCardIndexCompatibility(intMoveFromCol,cardToMove,intMoveToCol))
					//checks existence of multiple cards to be moved
					pilesAndCols = _checking.pushMultiple(intMoveFromCol,cardToMove,intMoveToCol);
				else if(intMoveToCol > 9 && _checking.checkSameSuit())
					//checks multiple consecutive cards of same suit to move to pile
					pilesAndCols = _checking.pushMultiple(intMoveFromCol,cardToMove,intMoveToCol);
				else
					System.out.println("Error: Invalid attempt to move!");
				
			}
			catch(ArrayIndexOutOfBoundsException ex){
				System.out.println("Error: Invalid attempt to move!");
			}
		}
		
		return moveFromCol;
	}
}