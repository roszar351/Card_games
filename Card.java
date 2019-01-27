public class Card implements Comparable<Card>
{

	private final String suit;
	private final String name;
	private final int value;

	// Hearts Diamonds Clubs Spades 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
	public Card(String suit, String name, int value)
	{
		this.suit = suit;
		this.name = name;
		this.value = value;
	}

	public String getSuit()
	{
		return suit;
	}

	public String getName()
	{
		return name;
	}

	public int getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		String s = name + " of " + suit;
		return s;
	}
	
	@Override
	public int compareTo(Card c)
	{
		//descending order
		if(this.value < c.getValue())
			return 1;
		else if(c.getValue() < this.value)
			return -11;
		return 0;
	}
}
