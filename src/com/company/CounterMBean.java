package com.company;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface CounterMBean {
    public void mBeanCheck(ArrayList<Point2D.Double> shots);
    public void countFourFails();
    public void checkShotInAria(Point2D.Double shot);
    public void checkByFive();
    public void findSuccessPercent();
    public double countInterval(double x1, double y1, double x2, double y2);
    public double countAverageInterval(double interval);
    public void findAverageInterval(ArrayList<Point2D.Double> shots);

    public int getShotCount();
//    public void setShotCount(int shotCount);

    public int getFailShotCount();
//    public void setFailShotCount(int failShotCount);
    public int getSuccessShotCount();
//    public void setSuccessShotCount(int successShotCount);
    public double getSuccessPercent();
//    public void setSuccessPercent(double successPercent);
    public double getAverageInterval();
//    public void setAverageInterval(double averageInterval);
    public int getCountFails();
//    public void setCountFails(int countFails);
}