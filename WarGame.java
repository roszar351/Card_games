public class WarGame
{
	private static Deck d = new Deck();

	public static void simulateGameOfWar(Hand p1, Hand p2)
	{
		setUp();
		dealCards(p1, p2);
		Hand neutral = new Hand();
		int limit = 0;
		Card c1 = p1.playCard();
		Card c2 = p2.playCard();

		while (c1 != null && c2 != null)
		{
			System.out.printf("%14s  -----  %-14s\n", c1, c2);
			roundWinner(c1, c2, p1, p2, neutral);
			limit++;

			neutral.clear();
			if (p1.getHand().size() == 0 || p2.getHand().size() == 0)
			{
				break;
			}
			if (limit % 50 == 0)
			{
				p1.shuffleHand();
				p2.shuffleHand();
			}
			c1 = p1.playCard();
			c2 = p2.playCard();
		}
		System.out.println("\n\n");
		p1.currentHand();
		System.out.println("\n\n");
		p2.currentHand();
		winner(p1, p2);
		System.out.println("\n\nIt took " + limit + " turns.");

		// new Frame();

	}

	private static void roundWinner(Card c1, Card c2, Hand h1, Hand h2, Hand h3)
	{
		if (c1.getValue() > c2.getValue())
		{
			h1.addToHand(c1, c2);
		}
		else if (c1.getValue() < c2.getValue())
		{
			h2.addToHand(c1, c2);
		}
		else if (c1.getValue() == c2.getValue())
		{
			equalValue(c1, c2, h1, h2, h3);
		}
	}

	private static void equalValue(Card c1, Card c2, Hand h1, Hand h2, Hand h3)
	{
		if (h1.getHand().size() < 2 || h2.getHand().size() < 2)
		{
			if (h1.getHand().size() < 2)
			{
				h2.addToHand(c1, c2);
			} 
			else
			{
				h1.addToHand(c1, c2);
			}
		} 
		else
		{
			h3.addToHand(c1, c2);
			h3.addToHand(h1.playCard(), h2.playCard());
			c1 = h1.playCard();
			c2 = h2.playCard();
			if (c1.getValue() > c2.getValue())
			{
				h1.addToHand(c1, c2);
				h1.addToHand(h3);
			}
			else if (c1.getValue() < c2.getValue())
			{
				h2.addToHand(c1, c2);
				h2.addToHand(h3);
			}
			else if (c1.getValue() == c2.getValue())
			{
				equalValue(c1, c2, h1, h2, h3);
			}
		}
	}

	private static void winner(Hand h1, Hand h2)
	{
		String won = h1.getPlayerName();
		if (h2.getHand().size() > 0)
		{
			won = h2.getPlayerName();
		}
		System.out.println(won + " has won!!!");
	}

	private static void setUp()
	{
		d.fillDeck();
		d.shuffleDeck();
	}

	private static void dealCards(Hand h1, Hand h2)
	{
		for (int i = d.getNumOfCards(); i != 0; i--)
		{
			if (i % 2 == 0)
			{
				h1.addToHand(d.getCard(0));
				d.removeCard(0);
			} 
			else
			{
				h2.addToHand(d.getCard(0));
				d.removeCard(0);
			}
		}
	}
}
