package controller;

import java.awt.*;

public class CollisionController {
    public static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        Point r1BottomRightPoint = new Point((int) r1.getX() + 48, (int) r1.getY() + 48);
        Point r2BottomRightPoint = new Point((int) r2.getX() + 48, (int) r2.getY() + 48);
        return (int) r1.getX() < r2BottomRightPoint.getX() && r1BottomRightPoint.getX() > (int) r2.getX() && (int) r1.getY() < r2BottomRightPoint.getY() && r1BottomRightPoint.getY() > (int) r2.getY();
    }
}
