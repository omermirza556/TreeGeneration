package tree;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * This class extends the JFrame to house the tree panel
 * 
 * @author omermirza
 *
 */
public class TreeFrame extends JFrame {

	JPanel mainPanel; // panel in which everything resides
	TreePanel myPanel; // panel in which the tree is drawn

	/**
	 * 
	 * This algorithm creates a tree panel and it is then hooked up a helper
	 * panel. the helper panel is then added to the frame.
	 */

	public TreeFrame(String title) {

		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(800, 600));
		myPanel = new TreePanel();
		mainPanel.add(myPanel);

		this.getContentPane().add(myPanel);
		this.pack();
		this.setVisible(true);

	}

}
