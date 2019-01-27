import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Hand
{

	private ArrayList<Card> hd;
	private int cardsInHand;
	private String playerName;

	public Hand()
	{
		this.hd = new ArrayList<>();
		this.cardsInHand = 0;
		this.playerName = null;
	}

	public Hand(String playerName)
	{
		this.hd = new ArrayList<>();
		this.cardsInHand = 0;
		this.playerName = playerName;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public ArrayList<Card> getHand()
	{
		return hd;
	}
	
	public Card get(int index)
	{
		return hd.get(index);
	}
	
	public int size()
	{
		return hd.size();
	}

	public void addToHand(Card c)
	{
		hd.add(c);
		cardsInHand++;
	}

	public void addToHand(Card c1, Card c2)
	{
		hd.add(c1);
		hd.add(c2);
		cardsInHand += 2;
	}

	public void addToHand(Hand h)
	{
		hd.addAll(h.getHand());
		cardsInHand += h.getHand().size();
	}

	public void currentHand()
	{
		System.out.println("You have: " + cardsInHand);
		for (int i = 0; i < cardsInHand; i++)
		{
			System.out.println(hd.get(i));
		}
	}

	public Card playCard()
	{
		Card c;
		if (cardsInHand == 0)
		{
			System.out.println("You have no cards.");
			return null;
		}
		c = hd.get(0);
		hd.remove(0);
		cardsInHand--;
		return c;
	}

	public void clear()
	{
		hd.clear();
		cardsInHand = 0;
	}

	public void shuffleHand()
	{
		Collections.shuffle(hd, new Random(System.currentTimeMillis()));
	}
	
	public void sortByValue()
	{
		Collections.sort(hd);
	}
	
	public String toString()
	{
		String SHand = "";
		for(int i = 0; i < hd.size(); i++)
			SHand += (i + 1) + ".  " + hd.get(i) + "\n";
		return SHand;
	}
}
