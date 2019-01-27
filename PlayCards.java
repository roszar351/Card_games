import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class PlayCards
{

	public static void main(String[] args)
	{
//		Hand h1 = new Hand("Yes");
//		Hand h2 = new Hand("No");
//		War_Game.simulateGameOfWar(h1, h2);
		UIManager.put("OptionPane.minimumSize",new Dimension(350,100));
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 18));
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 16));
		String name = JOptionPane.showInputDialog(null, "Input your player name:\n");
		if(name != null && !(name.equals("")))
		{
			Hand p1 = new Hand(name);
			while(JOptionPane.showConfirmDialog(null, "Do you want to play?", "Hello", JOptionPane.YES_NO_OPTION) == 0)
				BasicPoker.playPoker(p1);
		}
	}
}
