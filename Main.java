package com.company;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Paint class represents a paint program with three types of shape : triangle , circle , rectangle
 * @author Sarvin Nami
 */
class Paint{
    //List of shapes
    private ArrayList<Shape> shapes;
    /**
     * constructor for paint class
     */
    public Paint()
    {
        shapes=new ArrayList<Shape>();
    }
    /**
     * method drawall for show the information of the shape
     */
    public void drawAll()
    {
        for(Shape shps : shapes)
            shps.draw();
        System.out.println();
    }

    /**
     * add a shape to the list
     * @param shape
     */
    public void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    /**
     * print all the shapes of the list in string version
     */
    public void printAll()
    {
        for(Shape shape : shapes)
        {
            System.out.println(shape.toString());
            System.out.println();
        }
    }

    /**
     * this method check if we have square or equilateral in our list of shapes
     */
    public void describeEqualSides()
    {
        for(Shape shape : shapes)
        {
            if(shape instanceof Triangle && ((Triangle) shape).isEquilateral())
            {
                System.out.println("This Triangle is Equilateral");
                System.out.println(shape.toString());
                System.out.println();
            }
            if(shape instanceof Rectangle && ((Rectangle) shape).isSquare())
                System.out.println("This Rectangle Is Square");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Paint))
            return false;
        Paint paint = (Paint) o;
        return Objects.equals(shapes, paint.shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapes);
    }
}

/**
 * shape class that extends from paint class
 * @author Sarvin Nami
 */
abstract class Shape extends Paint {
    /**
     * method calculatePerimeter calculate the perimeter of shape
     * @return perimeter of the shape
     */
    public abstract double calculatePerimeter();

    /**
     * method calculateArea claculate the area of the shape
     * @return area of the shape
     */
    public abstract double calculateArea();

    /**
     * this method draw the information of the shapes such as perimeter and area and the shape kind
     */
    public abstract void draw();

    @Override
    public String toString()
    {
        return "Shape{}";
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}
class Circle extends Shape{
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    //radius of the circle
    private int radius;
    /**
     * constructor for circle class
     */
    public Circle(int radius)
    {
        this.radius=radius;
    }
    /**
     * getter for radius
     * @return radius
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * overriding toString method for circle
     * @return circle info in string type
     */
    @Override
    public String toString()
    {
        return "Circle - radius : " +radius;
    }

    /**
     * overriding calculatePerimeter
     * @return perimeter
     */
    @Override
    public double calculatePerimeter()
    {
        double perimeter;
        perimeter=2*3.14*radius;
        return perimeter;
    }

    /**
     * overriding calculateArea
     * @return Area
     */
    @Override
    public double calculateArea()
    {
        double area;
        area=3.14*radius*radius;
        return area;
    }

    /**
     * overriding draw method
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Circle. ");
        System.out.println("-----------------------");
        System.out.println("Perimeter : " + df2.format(calculatePerimeter()));
        System.out.println("Area : " + df2.format(calculateArea()));
        System.out.println();
    }

}

/**
 * polygon class show the rectangle and triangle information
 * @author Sarvin Nami
 */
abstract class Polygon extends Shape {
    //sides of each polygon
    ArrayList<Integer> sides;
    /**
     * constructor for polygon class
     * @param args sides
     */
    public Polygon(Integer ... args)
    {
        sides=new ArrayList<Integer>();
        for(Integer side : args)
        {
            sides.add(side);
        }
    }

    /**
     * getter for sides
     * @return sides of each polygon
     */
    public ArrayList<Integer> getSides() {
        return sides;
    }
}

/**
 * Rectangle class represents a rectangle with four sides
 * this class inherits from polygan class
 * @author Sarvin Nami
 */
class Rectangle extends Polygon{
    /**
     * constructor for rectangle class
     * @param args sides
     */
    public Rectangle(Integer ... args)
    {
        super(args);
    }
    /**
     * this method checks if rectangle is square or not
     * @return true if it is a square
     */
    public boolean isSquare()
    {
        if(sides.get(0) == sides.get(1) && sides.get(0) == sides.get(3))
            return true;
        else
            return false;
    }
    /**
     * method that calculate perimeter of the rectangle
     * @return perimeter of rectangle
     */
    @Override
    public double calculatePerimeter()
    {
        double perimeter=0;
        for(int i=0;i<sides.size();i++)
        {
            if(!isSquare())
            {
                perimeter += sides.get(i);
            }
            if(isSquare())
            {
                perimeter  = 4 * sides.get(i);
            }
        }
        return perimeter;
    }
    /**
     * method that calculate area of rectangle
     * @return area of rectangle
     */
    @Override
    public double calculateArea()
    {
        return Math.sqrt(sides.get(0) * sides.get(1) * sides.get(2) * sides.get(3) );
    }

    /**
     * draw the rectangle with its area and perimeter
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Rectangle. ");
        System.out.println("--------------------------");
        System.out.println("Perimeter : " + calculatePerimeter());
        System.out.println("Area : " + calculateArea());
        System.out.println("is Square : " + isSquare());
        System.out.println();
    }

    @Override
    public String toString() {
        return "Rectangle - sides : side1 =" + sides.get(0) + " |side2 = " + sides.get(1) + " |side3 = " + sides.get(2) + " |side4 = " + sides.get(3);
    }
}

/**
 * Triangle class extends from shape and represents a triangle with 3 sides
 */
class Triangle extends Polygon{
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    /**
     * constructor for triangle class
     * @param args sides
     */
    public Triangle(Integer ... args)
    {
        super(args);
    }
    /**
     * the methos that checks if the triangle is equilateral
     * @return true if it is equilateral
     */
    public boolean isEquilateral()
    {
        if (sides.get(0) == sides.get(1) && sides.get(0) == sides.get(2))
            return true;
        else
            return false;
    }
    /**
     * calculate triangle's perimeter
     * @return the perimeter
     */

    @Override
    public double calculatePerimeter()
    {
        double perimeter=0;
        for(int i=0;i<sides.size();i++)
            perimeter += sides.get(i);
        return perimeter;
    }
    /**
     * calculate triangle's area
     * @return
     */
    @Override
    public double calculateArea()
    {
        double p = calculatePerimeter() / 2;
        return  Math.sqrt(p * (p - sides.get(0)) * (p - sides.get(1)) * (p - sides.get(2)));
    }

    /**
     * show the states of triangle
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Triangle. ");
        System.out.println("-------------------------");
        System.out.println("Perimeter : " + calculatePerimeter());
        System.out.println("Area : " + df2.format(calculateArea()));
        System.out.println("is Equilateral : " + isEquilateral());
        System.out.println();
    }
    @Override
    public String toString()
    {
        return "Triangle- sides : side1 =" + sides.get(0) + " |side2 = " + sides.get(1) + " |side3 = " + sides.get(2);
    }
}

public class Main {

    public static void main(String[] args)
    {
        Circle circle1 = new Circle(19);
        Circle circle2 = new Circle(3);

        Rectangle rect1 = new Rectangle(1,4,1,4);
        Rectangle rect2 = new Rectangle(8,5,8,5);
        Rectangle rect3 = new Rectangle(6,6,6,6);

        Triangle tri1 = new Triangle(2,2,2);
        Triangle tri2 = new Triangle(4,4,6);

        Paint paint = new Paint();

        paint.addShape(circle1);
        paint.addShape(circle2);
        paint.addShape(rect1);
        paint.addShape(rect2);
        paint.addShape(rect3);
        paint.addShape(tri1);
        paint.addShape(tri2);
        paint.drawAll();
        System.out.println();
        paint.printAll();

        paint.describeEqualSides();
        //check equals and hashcodes
        System.out.println("Check hashCode and equals override :");
        System.out.println("------------------------------------");
        Rectangle rectangle1 = new Rectangle(1,4,1,4);
        Rectangle rectangle2 = new Rectangle(1,4,1,4);
        System.out.println(rectangle1.equals(rectangle2));


    }
}