package tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * this is the tree object class A randomly generated tree (within limits) is
 * created using recursion. Essentially one branch is created and then two more
 * split off of it. The length and width of the branches is determined by a
 * simple linear algorithm that will be descirbed below. If there are 4 or less
 * branches remaining to be drawn, leaves are drawn as well. A stroke is used to
 * thicken the normally standard line.
 * 
 * @author omermirza
 *
 */

public class TreeObject {

	/*
	 * member variables and new colors created.
	 */

	private int maxSegments, nx, ny;
	private double splitAngle, a1, a2;
	private Color brown = new Color(160, 82, 45), forrestGreen = new Color(34, 139, 34),
			otherGreen = new Color(60, 179, 113), pink = new Color(255, 192, 203);

	public TreeObject(double splitAngle, int maxSegments) { // constructor
		this.splitAngle = splitAngle;
		this.maxSegments = maxSegments;

	}

	public TreeObject(int maxSegments) { // another constructor
		this.splitAngle = 20;
		this.maxSegments = maxSegments;

	}

	public TreeObject() { // one more constructor
		this.splitAngle = 25;
		this.maxSegments = 10;

	}

	/**
	 * 
	 * this is a public algorithm that kickes off the private draw tree
	 * algorithm
	 */

	public void drawOn(Graphics g, int x, int y, double angle) {
		drawTree(g, x, y, angle, this.maxSegments);

	}

	/**
	 * 
	 * @param g
	 *            is the graphics object
	 * @param sx
	 *            is the starting x coordinate
	 * @param sy
	 *            is the starting y coordinate
	 * @param sAngle
	 *            is the angle of growth
	 * @param segsR
	 *            is segments remaining
	 * 
	 *            The idea of this algorithm is to use recursion to draw a tree.
	 *            If there are more than 0 segments remaining another branch is
	 *            drawn. if there are fewer than 5 branches remaining left
	 *            leaves are drawn at the end of the segment. the graphics
	 *            object is converted to a graphics 2D object and a stroke is
	 *            given to the line created. The new angles are then calculated
	 *            but are fed into another instance of the draw tree method in
	 *            the draw tree method
	 */

	private void drawTree(Graphics g, int sx, int sy, double sAngle, int segsR) {

		Graphics2D g2 = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke((widthAmount(segsR)));
		g2.setStroke(stroke);

		if (segsR > 0) {
			/*
			 * Math.cos & math.sin opperate in radians instead of degrees so a
			 * conversion must be made
			 */
			nx = (int) (sx + branLen(segsR) * Math.cos(Math.toRadians(sAngle)));
			ny = (int) (sy - branLen(segsR) * Math.sin(Math.toRadians(sAngle)));
			g2.setColor(brown);
			g2.drawLine(sx, sy, nx, ny);
			if (segsR < 5) {
				drawLeaf(g, nx, ny, sAngle);

			}

			a1 = sAngle + this.splitAngle + randomNoise();
			drawTree(g, nx, ny, a1, segsR - 1);
			nx = (int) (sx + branLen(segsR) * Math.cos(Math.toRadians(sAngle)));
			ny = (int) (sy - branLen(segsR) * Math.sin(Math.toRadians(sAngle)));
			a2 = sAngle - this.splitAngle + randomNoise();
			drawTree(g, nx, ny, a2, segsR - 1);

		}

	}

	/**
	 * 
	 * 
	 * The leaf is drawn in such a way that the current location of the branch
	 * is transformed into the origin, that is why when the leaf is draw as if
	 * its on the origin it appears at the end of the branch. For the leaves I
	 * want to be set to green I have used a method that returns a shade of
	 * green randomly as the set color
	 */

	private void drawLeaf(Graphics g, int x, int y, double theta) {

		/*
		 * the affine transform converts the 0,0 to the current x and y
		 * coordinate to draw the leaves
		 */
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform xf = g2.getTransform();
		g2.translate(x, y);
		g2.rotate(Math.toRadians(theta));

		g2.setColor(randomColor());
		g2.fillOval(0, -5, 25, 10);
		g2.setColor(randomColor());
		g2.fillOval(-5, 0, 10, 25);
		g2.setColor(randomColor());
		g2.fillOval(0, 0, 25, 10);

		g2.setColor(pink);
		g2.fillOval(0, -5, 10, 10);
		g2.setTransform(xf);

	}

	private int branLen(int segs) { // calculating length of branch

		return segs * 10;

	}

	private int randomNoise() { // adds random noise
		Random rand = new Random();

		return rand.nextInt(21) - 10;
	}

	private Color randomColor() { // returns random shade of green
		Random rand = new Random();
		int x = rand.nextInt(100);
		if (x < 50) {
			return forrestGreen;
		} else {
			return otherGreen;
		}
	}

	/*
	 * randonly generated getters and setters
	 */

	public int getMaxSegments() {
		return maxSegments;
	}

	public void setMaxSegments(int maxSegments) {
		this.maxSegments = maxSegments;
	}

	public double getSplitAngle() {
		return splitAngle;
	}

	public void setSplitAngle(double splitAngle) {
		this.splitAngle = splitAngle;
	}

	public int widthAmount(int segsR) {
		return (segsR*segsR)/3;
	}

}
