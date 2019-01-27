import javax.swing.JOptionPane;

/* Simple 1 player Poker
 * Deal 5 cards, decide if want to swap any
 * Then compare against a computers hand and choose winner
 * No betting folding etc.
 */
public class BasicPoker
{
	private static Deck d;
	private static Hand h1;
	private static Hand CPU;
	private static int winStreak = 0;
	private static int highestStreak = 0;
	private static String CPUHandValues;
	private static String CPUHandSuits;
	private static String h1HandValues;
	private static int playerValue;
	private static int CPUValue;
	//private static String h1HandSuits = "";
	
	public static void playPoker(Hand p1)
	{
		h1 = p1;
		setUp();
		start();
	}
	
	private static void start()
	{
		String winner = "";
		String type = "";
		String s = JOptionPane.showInputDialog(null,"These are your cards:\n" + h1 + "Type the positions of the cards you want to switch(1-5)");
		swapCards(s, h1);
		JOptionPane.showMessageDialog(null, "These are your new cards\n" + h1);
		winner = decideWinner();
		if(winner.equalsIgnoreCase("Computer"))
		{
			type = whatInHand(CPUValue);
			winStreak = 0;
		}
		else
		{
			type = whatInHand(playerValue);
			winStreak++;
		}
		
		String finalResult = "Your hand:\n";
		for(int i = 0; i < 5; i++)
			finalResult += (i+1) + ".  " + h1.get(i) + "\n";
		finalResult += "\nComputer's hand:\n";
		for(int i = 0; i < 5; i++)
			finalResult += (i+1) + ".  " + CPU.get(i) + "\n";
		finalResult += "\nThe winner is " + winner + "\nWith " + type;
		JOptionPane.showMessageDialog(null, finalResult);
		if(winStreak > highestStreak)
			highestStreak = winStreak;
		JOptionPane.showMessageDialog(null, "Your current win streak: " + winStreak +
									  "\nHighest Streak this session: " + highestStreak);
			
	}
	
	private static void setUp()
	{
		CPU = new Hand();
		CPUHandValues = "";
		CPUHandSuits = "";
		playerValue = 0;
		CPUValue = 0;
		h1.clear();
		CPU.clear();
		d = new Deck();
		d.clearDeck();
		d.fillDeck();
		d.shuffleDeck();
		dealCards();
	}
	
	private static String decideWinner()
	{
		h1HandValues = "";
		playerValue = checkHandStrength(h1);
		CPUHandValues = "";
		CPUValue = checkHandStrength(CPU);
		CPUChoice(CPUValue);
		CPUHandValues = "";
		CPUValue = checkHandStrength(CPU); // Update CPU value incase of potential swaps
		if(playerValue > CPUValue)
			return h1.getPlayerName();
		else if(CPUValue > playerValue)
			return "Computer";
		else
			return equalValue(playerValue, CPUValue);
	}
	
	private static String whatInHand(int value)
	{
		switch(value)
		{
		case 1: return "One Pair"; 
		case 2: return "Two Pair";
		case 3: return "Three Of A Kind";
		case 4: return "A Straight";
		case 5: return "A Flush";
		case 6: return "A Full House";
		case 7: return "Four Of A Kind";
		case 8: return "Straight Flush";
		case 9: return "Royal Flush";
		default: return "A High Card";	
		}
	}
	
	private static void swapCards(String whichCards, Hand handToSwap)
	{
		if(whichCards != null)
		{
			whichCards = whichCards.replaceAll("[^1-5]", "");
			int index = 0;
			String done = "";
			for(int i = 0; i < whichCards.length(); i++)
			{
				index = Integer.parseInt(whichCards.substring(i, i + 1));
				if(done.indexOf(whichCards.substring(i, i + 1)) == -1)
				{
					handToSwap.getHand().set(index - 1, d.getCard(0));
					d.removeCard(0);
					done += whichCards.substring(i, i + 1);
				}
			}
			h1.sortByValue();
			CPU.sortByValue();
		}
	}
	
	private static int checkHandStrength(Hand givenHand)
	{
		boolean sameSuit = true;
		boolean cardsInSequence = true;
		int value = 0;
		Card c1, c2;
		for(int i = 0; i < givenHand.size() - 1; i++)
		{
			c1 = givenHand.get(i);
			c2 = givenHand.get(i + 1);
			if(!(c1.getSuit().equals(c2.getSuit())) && sameSuit)
				sameSuit = false;
			else
			{
				if(CPUHandSuits.indexOf(i) == -1 && givenHand.equals(CPU))
					CPUHandSuits += i + "" + (i+1);
//				else
//					h1HandSuits += i + "" + (i+1);
			}
			if((c1.getValue() - c2.getValue()) != 1 && cardsInSequence)
			{
				if(c1.getValue() - c2.getValue() != 9)
					cardsInSequence = false;
			}
			
		}
		c1 = givenHand.get(0);
		value = checkForPairs(givenHand);
		if(value == 6)
			value++;
		if(value == 4)
			value = 6;
		if(sameSuit && cardsInSequence && c1.getValue() == 14)
			return 9;
		else if(sameSuit && cardsInSequence)
			return 8;
		else if(value > 4)
			return value;
		else if(sameSuit)
			return 5;
		else if(cardsInSequence)
			return 4;
		else
			return value;
	}
	
	private static int checkForPairs(Hand givenHand)
	{
		int value = 0;
		Card c1, c2;
		boolean stop = false;
		for(int i = 0; i < givenHand.size() - 1; i++)
		{
			stop = false;
			c1 = givenHand.get(i);
			for(int j = i + 1; j < givenHand.size() && !stop; j++)
			{
				c2 = givenHand.get(j);
				if(c1.getValue() == c2.getValue())
				{
					value++;
					if(CPUHandValues.indexOf(i) == -1 && givenHand.equals(CPU))
						CPUHandValues += i + "" + (i+1);
					else if(h1HandValues.indexOf(i) == -1 && givenHand.equals(h1))
						h1HandValues += i + "" + (i+1);
				}
				else
					stop = true;
			}
		}
		return value;
	}
	
	// This method needs major reworking
	private static String equalValue(int player, int computer)
	{
		int playerScore = 0, computerScore = 0;
		if(player == 0)
		{
			for(int i = 0; i < 5; i++)
			{
				if(h1.get(i).getValue() > CPU.get(i).getValue())
					return h1.getPlayerName();
				else if(h1.get(i).getValue() < CPU.get(i).getValue())
					return "Computer";
				else if(i == 4)
					return h1.getPlayerName();
			}
		}
		else if(player == 4)
		{
			playerScore = h1.get(2).getValue();
			computerScore = CPU.get(2).getValue();
			if(playerScore == 5 && computerScore == 5)
			{
				if(h1.get(1).getValue() == 14)
					playerScore = 1;
				else
					playerScore = h1.get(1).getValue();
				if(CPU.get(1).getValue() == 14)
					computerScore = 1;
				else
					computerScore = CPU.get(1).getValue();
			}
		}
		else if(player == 5 || player == 8 || player == 9)
		{
			for(int i = 0; i < 5; i++)
			{
				playerScore += h1.get(i).getValue();
				computerScore += CPU.get(i).getValue();
			}
		}
		else if(player == 6)
		{
			playerScore += h1.get(3).getValue();
			computerScore += CPU.get(3).getValue();
		}
		else
		{
			for(int i = 0; i < (CPUHandValues.length() - 1) && CPUHandValues.length() == h1HandValues.length(); i++)
			{
				playerScore += h1.get(Integer.parseInt(h1HandValues.substring(i, (i+1)))).getValue();
				computerScore += CPU.get(Integer.parseInt(CPUHandValues.substring(i, (i+1)))).getValue();
				if(i == 2)
				{
					if(playerScore > computerScore)
						return h1.getPlayerName();
					else if(computerScore > playerScore)
						return "Computer";
				}
				if(player == 2 && i == 2)
				{
					if(playerScore == computerScore)
					{
						playerScore = h1.get(4).getValue();
						computerScore = CPU.get(4).getValue();
					}
				}
			}
			if(playerScore == computerScore)
			{
				playerScore = 0;
				computerScore = 0;
				for(int i = 0; i < 5; i++)
				{
					playerScore += h1.get(i).getValue();
					computerScore += CPU.get(i).getValue();
				}
			}
		}
		if(playerScore >= computerScore)
			return h1.getPlayerName();
		else
			return "Computer";
	}
	
	private static void CPUChoice(int v)
	{
		if(v == 0 && CPUHandSuits.length() >= 3)
			swapCards("12345".replaceAll(CPUHandSuits, ""), CPU);	
		else if(v < 4)
			swapCards("12345".replaceAll(CPUHandValues, ""), CPU);
		else if(v == 0)
			swapCards("12345".replaceAll(CPUHandValues, ""), CPU);	
	}
	
	private static void dealCards()
	{
		for(int i = 0; i < 10; i++)
		{
			if(i % 2 == 0)
			{
				h1.addToHand(d.getCard(0));
				d.removeCard(0);
			}
			else
			{
				CPU.addToHand(d.getCard(0));
				d.removeCard(0);
			}
		}
		h1.sortByValue();
		CPU.sortByValue();
	}
}
