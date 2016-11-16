package org.firstinspires.ftc.teamcode.Tests;

import junit.framework.TestCase;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 11/15/2016.
 * Tests for autonomous robot class
 */
public class RobotAutoTest extends TestCase {
    public void testCalculateTime() throws Exception {
        RobotAuto robot = new RobotAuto();
        int time = robot.calculateTime(1,1);//// FIXME: 11/15/2016 
        
        if(time == 0){
            
        }else{
            fail("TODO");
        }
    }

}