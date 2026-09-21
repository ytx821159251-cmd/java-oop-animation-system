/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * YOUR UPI:
 * ==========================================================================================
 */

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.*;

class AnimationViewer extends JComponent implements Runnable ,TreeModel{

	private Thread animationThread = null; // the thread for animation
	private static int DELAY = 120; // the current animation speed
	private ShapeType currentShapeType = Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType = Shape.DEFAULT_PATHTYPE; // the current path type
	private Color currentColor = Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth = Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT,currentWidth = Shape.DEFAULT_WIDTH, currentHeight = Shape.DEFAULT_HEIGHT;
	private String currentLabel = Shape.DEFAULT_LABEL;
	ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
	//question 5
	protected NestedShape root;
	//question 6
	private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<>();

	//question 5
	public AnimationViewer() {
		root = new NestedShape(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT);
		start();
	}
	//question 5
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape currentShape : root.getAllInnerShapes()) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawString(g);
		}
	}

	public void resetMarginSize() {
		currentPanelWidth = getWidth();
		currentPanelHeight = getHeight();
		for (Shape currentShape : root.getAllInnerShapes())
			currentShape.resetPanelSize(currentPanelWidth, currentPanelHeight);
	}


//question 6
	public NestedShape getRoot() {
		return this.root;
	}


	public boolean isLeaf(Object node) {
		if(node instanceof NestedShape)
			return false;
		return true;
	}

	boolean isRoot(Shape selectedNode){
		return this.root == selectedNode;
	}


	public Object getChild(Object parent, int index) {
		if(!(parent instanceof NestedShape))
			return null;
		NestedShape p = (NestedShape) parent;
		if(index >= p.getAllInnerShapes().size())
			return null;
		return p.getInnerShapeAt(index);
	}


	public int getChildCount(Object parent) {
		if(!(parent instanceof NestedShape))
			return 0;
		NestedShape p = (NestedShape) parent;
		return p.getAllInnerShapes().size();
	}


	public int getIndexOfChild(Object parent, Object child) {
		if(!(parent instanceof NestedShape))
			return -1;
		if(!(child instanceof Shape))
			return -1;
		NestedShape p = (NestedShape) parent;
		return p.indexOf((Shape)child);
	}


	public void addTreeModelListener(final TreeModelListener tml) {
		this.treeModelListeners.add(tml);
	}


	public void removeTreeModelListener(final TreeModelListener tml) {
		this.treeModelListeners.remove(tml);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {

	}
//question 7
	public void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children){
		TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
		for(TreeModelListener l : this.treeModelListeners){
			l.treeNodesInserted(event);
		}
		System.out.printf("Called fireTreeNodesInserted: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
	}

	public void addShapeNode(NestedShape selectedNode){
		Shape innerShape = selectedNode.createInnerShape(this.getCurrentPathType(), this.getCurrentShapeType());
		Object [] newChild = new Object[]{innerShape};
		this.fireTreeNodesInserted((Object)this, selectedNode.getPath(), new int[]{this.getIndexOfChild(selectedNode, innerShape)}, newChild);
	}

	//question 8
	public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,Object[] children){
		TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
		for(TreeModelListener l : this.treeModelListeners){
			l.treeNodesRemoved(event);
		}
		System.out.printf("Called fireTreeNodesRemoved: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
	}

	public void removeNodeFromParent(Shape selectedNode){
		NestedShape parent = selectedNode.getParent();
		int index = parent.indexOf(selectedNode);
		parent.removeInnerShape(selectedNode);
		Object [] newChild = new Object[]{selectedNode};
		this.fireTreeNodesRemoved((Object)this, parent.getPath(), new int[]{index}, newChild);
	}


	protected void createNewShape(int x, int y) {
		int min_size = Math.min(currentWidth, currentHeight);
		switch (currentShapeType) {
			case RECTANGLE: {
			shapes.add( new RectangleShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
			break;
			}  case SQUARE: {
			shapes.add( new SquareShape(x, y,min_size,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
			break;
			}
		}
	}


	// you don't need to make any changes after this line ______________
	public String getCurrentLabel() {return currentLabel;}
	public int getCurrentHeight() { return currentHeight; }
	public int getCurrentWidth() { return currentWidth; }
	public Color getCurrentColor() { return currentColor; }
	public Color getCurrentBorderColor() { return currentBorderColor; }
	public void setCurrentShapeType(ShapeType value) {currentShapeType = value;}
	public void setCurrentPathType(PathType value) {currentPathType = value;}
	public ShapeType getCurrentShapeType() {return currentShapeType;}
	public PathType getCurrentPathType() {return currentPathType;}
	public void update(Graphics g) {
		paint(g);
	}
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}
	public void run() {
		Thread myThread = Thread.currentThread();
		while (animationThread == myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long) milliseconds);
		} catch (InterruptedException ie) {}
	}
}
