import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck
{
	private int numOfCards;
	private ArrayList<Card> deck;

	// Hearts Diamonds Clubs Spades 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
	public Deck()
	{
		deck = new ArrayList<>();
		numOfCards = 0;
	}

	public ArrayList<Card> getDeck()
	{
		return deck;
	}

	public int getNumOfCards()
	{
		return numOfCards;
	}

	public void addCard(Card c)
	{
		deck.add(c);
	}

	public void addCard(ArrayList<Card> ALC)
	{
		deck.addAll(ALC);
	}

	public Card getCard(int i)
	{
		return deck.get(i);
	}

	public void removeCard(int i)
	{
		deck.remove(i);
		numOfCards--;
	}

	// fills it with 52 unique cards
	public void fillDeck()
	{
		String[] s = {"Hearts", "Diamonds", "Clubs", "Spades"};
		String[] n = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		int[] v = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		while (numOfCards != 52)
		{
			for (int i = 0; i < s.length; i++)
			{
				for (int j = 0; j < v.length; j++)
				{
					Card c = new Card(s[i], n[j], v[j]);
					deck.add(c);
					numOfCards++;
				}
			}
		}
	}

	public void showDeck()
	{
		if (numOfCards == 0)
		{
			System.out.println("Deck is empty.");
		} 
		else
		{
			for (int i = 0; i < numOfCards; i++)
			{
				System.out.println(deck.get(i));
			}
		}
	}

	public void shuffleDeck()
	{
		Collections.shuffle(deck);
		Collections.shuffle(deck, new Random(System.currentTimeMillis()));
	}
	
	public void clearDeck()
	{
		deck.clear();
	}
}
