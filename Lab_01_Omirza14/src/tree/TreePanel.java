package tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * this class creates the tree panel in which the tree itself is drawn
 * 
 * @author omermirza
 *
 */

public class TreePanel extends JPanel {
	TreeObject myTree; // creates the tree object

	/**
	 * 
	 * the Tree panel method creates the tree object and sets its prefered size
	 * to 800 by 600 and sets the background to white
	 */

	public TreePanel() {
		super();
		myTree = new TreeObject(); // initializes the tree object

		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(800, 600));
	}

	/**
	 * kicks of the recursive algorithm in the tree class and draws it onto the
	 * panel
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		myTree.drawOn(g, 400, 600, 90);

	}
}
