import java.awt.*;
import java.util.ArrayList;
//quetion 2--4
class NestedShape extends RectangleShape {
    private ArrayList<Shape> innerShapes;
    public NestedShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color fillColor, Color borderColor, PathType pathType) {
        super(x, y, width, height, panelWidth, panelHeight, fillColor, borderColor, pathType);
        innerShapes = new ArrayList<>();
        createInnerShape(PathType.BOUNCING, ShapeType.RECTANGLE);
    }

    public NestedShape(int width, int height) {
        super(0, 0, width, height, Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT, Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, PathType.BOUNCING);
        innerShapes = new ArrayList<>();
    }

    public Shape createInnerShape(PathType pt, ShapeType st) {
        Shape innerShape;
        int innerWidth = this.width / 4;
        int innerHeight = this.height / 4;

        switch (st) {
            case RECTANGLE:
                innerShape = new RectangleShape(0, 0, innerWidth, innerHeight, this.width, this.height, this.getColor(), this.getBorderColor(), pt);
                break;
            case SQUARE:
                int size = Math.min(innerWidth, innerHeight);
                innerShape = new SquareShape(0, 0, size, this.width, this.height, this.getColor(), this.getBorderColor(), pt);
                break;
            case NESTED:
                innerShape = new NestedShape(0, 0, innerWidth, innerHeight, this.width, this.height, this.getColor(), this.getBorderColor(), pt);
                break;
            default:
                throw new IllegalArgumentException("Invalid shape type");
        }

        innerShape.setParent(this);
        innerShapes.add(innerShape);
        return innerShape;
    }

    public Shape getInnerShapeAt(int index) {
        return innerShapes.get(index);
    }

    public int getSize() {
        return innerShapes.size();
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.translate(x, y);
        for (Shape shape : innerShapes) {
            shape.draw(g);
            g.setColor(Color.BLACK);
            g.drawString(shape.getLabel(), shape.getX(), shape.getY());
        }
        g.translate(-x, -y);
    }
    public void move() {
        super.move();
        for (Shape shape : innerShapes) {
            shape.move();
        }
    }
    public int indexOf(Shape s) {
        return innerShapes.indexOf(s);
    }

    public void addInnerShape(Shape s) {
        s.setParent(this);
        innerShapes.add(s);
    }

    public void removeInnerShape(Shape s) {
        s.setParent(null);
        innerShapes.remove(s);
    }

    public void removeInnerShapeAt(int index) {
        Shape shape = innerShapes.get(index);
        if (shape != null) {
            shape.setParent(null);
            innerShapes.remove(index);
        }
    }

    public ArrayList<Shape> getAllInnerShapes() {
        return innerShapes;
    }
}