package com.company;

import javax.management.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

	public static void main( String[] args ) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.company:type=Counter");
		Counter counter = new Counter();
		server.registerMBean(counter, name);

		SomeClass someClass = new SomeClass();

		Scanner in = new Scanner(System.in);

		while(true){
			Point2D.Double shot = new Point2D.Double();
			System.out.println("\nСовершите выстрел: ");
			shot.x = in.nextInt();
			shot.y = in.nextInt();
			someClass.checkShot(shot);
			someClass.getShotsVector().add(shot);
			counter.mBeanCheck(someClass.getShotsVector());
		}
    }
}
