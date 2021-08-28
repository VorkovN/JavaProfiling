package com.company;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcasterSupport;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Counter extends NotificationBroadcasterSupport implements CounterMBean {
    public int  shotCount;
    public int  failShotCount;
    public int  successShotCount;
    public double successPercent;
    public double averageInterval;
    public int countFails;
    public int intervalsCount;

    public Counter(){
        shotCount = 0;
        failShotCount = 0;
        successShotCount = 0;
        countFails = 0;
        successPercent = 0.0;
        averageInterval = 0.0;
        intervalsCount = 0;
    }

    @Override
    public void mBeanCheck(ArrayList<Point2D.Double> shots) {
        Point2D.Double shot = shots.get(shots.size() - 1);
        ++shotCount;
        if(Math.pow(shot.x*shot.x+shot.y*shot.y, 0.5) <= SomeClass.R)
        {
            ++successShotCount;
            countFails = 0;
        }
        else
        {
            ++failShotCount;
            ++countFails;
        }
        findAverageInterval(shots);
        countFourFails();
        checkShotInAria(shot);
        checkByFive();
        findSuccessPercent();
    }

    @Override
    public void countFourFails() {
        if (countFails >= 4)
            sendNotification(new AttributeChangeNotification(this, 0, System.currentTimeMillis(), "four fails", null, null, null, null ));
    }
    @Override
    public void checkShotInAria(Point2D.Double shot) {
        if(Math.pow(shot.x*shot.x+shot.y*shot.y, 0.5) >= 2*SomeClass.R)
            sendNotification(new AttributeChangeNotification(this, 0, System.currentTimeMillis(), "visible area outside", null, null, null, null ));
    }
    @Override
    public void checkByFive() {
        if(shotCount % 5 == 0)
            sendNotification(new AttributeChangeNotification(this, 0, System.currentTimeMillis(), "devided by five", null, null, null, null ));
    }

    @Override
    public void findSuccessPercent() {
        successPercent =  (double)successShotCount/shotCount;
    }

    @Override
    public double countInterval(double x1, double y1, double x2, double y2) {
        return Math.pow(Math.pow((x1 - x2), 2)+Math.pow((y1 - y2), 2), 0.5);
    }
    @Override
    public double countAverageInterval(double interval) {
        return (averageInterval*intervalsCount + interval)/(double)++intervalsCount;
    }
    @Override
    public void findAverageInterval(ArrayList<Point2D.Double> shots) {
        for(int i = 0; i < shots.size()-1; ++i) {
            double interval = countInterval(shots.get(i).x, shots.get(i).y, shots.get(shots.size()-1).x, shots.get(shots.size()-1).y);
            averageInterval = countAverageInterval(interval);
        }
    }

    @Override
    public int getShotCount() {
        return shotCount;
    }
//    @Override
//    public void setShotCount(int shotCount) {
//        this.shotCount = shotCount;
//    }
    @Override
    public int getFailShotCount() {
        return failShotCount;
    }
//    @Override
//    public void setFailShotCount(int failShotCount) {
//        this.failShotCount = failShotCount;
//    }
    @Override
    public int getSuccessShotCount() {
        return successShotCount;
    }
//    @Override
//    public void setSuccessShotCount(int successShotCount) {
//        this.successShotCount = successShotCount;
//    }
    @Override
    public double getSuccessPercent() {
        return successPercent;
    }
//    @Override
//    public void setSuccessPercent(double successPercent) {
//        this.successPercent = successPercent;
//    }
    @Override
    public double getAverageInterval() {
        return averageInterval;
    }
//    @Override
//    public void setAverageInterval(double averageInterval) {
//        this.averageInterval = averageInterval;
//    }
    @Override
    public int getCountFails() {
        return countFails;
    }
//    @Override
//    public void setCountFails(int countFails) {
//        this.countFails = countFails;
//    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo()
    {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "Doing";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
