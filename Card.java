import java.util.*;

/**
 * Implementation of Card class uses ArrayList, Stack, and Map to create a deck of fifty-two cards
 */
public class Card{
	/**
	 * Number of cards in a deck
	 */
	public static final int NUM_CARDS = 52;
	
	/**
	 * Contains all the thirteen card values
	 */
	private String[] cardValues = new String[]{"A","2","3","4","5","6","7","8","9","X","J","Q","K"};

	/**
	 * Stores suits and card values
	 */
	private Map<String,ArrayList<String>> suits = new TreeMap<>();
	
	/**
	 * Stores fifty-two cards
	 */
	public Stack<String> stackDeck = new Stack<>();

	/**
	 * Creates a deck of fifty-two cards containing four suits of thirteen values
	 */
	public Card(){
		ArrayList<String> club = new ArrayList<>();
		ArrayList<String> diamond = new ArrayList<>();
		ArrayList<String> heart = new ArrayList<>();
		ArrayList<String> spade = new ArrayList<>();
		
		for(String e: cardValues)
			club.add(e);
		for(String e: cardValues)
			diamond.add(e);
		for(String e: cardValues)
			heart.add(e);
		for(String e: cardValues)
			spade.add(e);
		
		suits.put("c",club);
        suits.put("h",diamond);
        suits.put("d",heart);
        suits.put("s",spade);
		
		ArrayList<String> deck = new ArrayList<>(); //deck of 52 cards
		for(String key: suits.keySet()){
            ArrayList<String> keyElement = suits.get(key);
            for (String e: keyElement){
                deck.add(key + e);
            }  
        }
		Collections.shuffle(deck);
	
		for(int i = 0; i < NUM_CARDS; i++)
			stackDeck.push(deck.get(i)); //push into stack of 52 cards
	}
	
	/**
	 * Returns the stack of fifty-two shuffled cards
	 * @return stack of fifty-two shuffled cards
	 */
	public Stack<String> getStackCards(){
		return stackDeck;
	}
}