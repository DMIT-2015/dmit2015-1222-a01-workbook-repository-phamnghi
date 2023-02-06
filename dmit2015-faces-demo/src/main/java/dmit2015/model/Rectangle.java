package dmit2015.model;
import lombok.Data;
/**
        * This class model a Rectangle shape
        *
        * @author phamnghi
        * @version 2023.01.20
        * */
@Data
public class Rectangle {
    /**
     * The length of this rectangle
     */
    private double length;
    /**
     * The width of this rectangle
     */
    private double width;

    /**
     * Compute and return the area of this rectangle
     * @return area of this rectangle
     */
    public double area() {
        return width * length;
    }

    /**
     * Compute and return the perimeter of this rectangle
     * @return perimeter of this rectangle
     */
    public double perimeter() {
        return 2 * (length + width);
    }

    /**
     * Compute and return the diagonal of this rectangle
     * @return diagonal of this rectangle
     */
    public double diagonal() {
        return Math.sqrt(Math.pow(length,2) + Math.pow(width,2));
    }
}
