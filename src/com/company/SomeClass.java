package com.company;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SomeClass {

    public static double R = 50;
    public static double averageInterval = 0.0;
    private ArrayList<Point2D.Double> shotsVector;

    public SomeClass() {
        this.shotsVector = new ArrayList<Point2D.Double>();
    }

    public ArrayList<Point2D.Double> getShotsVector() {
        return shotsVector;
    }

    public void checkShot(Point2D.Double shot)
    {
        if(Math.pow(shot.x*shot.x+shot.y*shot.y, 0.5) <= R)
            System.out.println("Попадание!");
        else
            System.out.println("Промах!");
    }
}
