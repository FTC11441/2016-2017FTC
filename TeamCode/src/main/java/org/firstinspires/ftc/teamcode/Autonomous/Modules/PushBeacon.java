package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 2/21/2017.
 * moves the beacon
 */

public class PushBeacon extends Module {

    protected PushBeacon(double position) {
        super(new double[]{position});
    }

    @Override
    public void checkMovement(RobotAuto robot) {
        if (robot.time.milliseconds() > robot.waitTime) {
            robot.nextStep();
        }
    }

    @Override
    public void startMovement(RobotAuto robot) {
        //move bumper to a position and wait for some time to make sure it gets there
        robot.bumper.setPosition(vars[0]);
        robot.waitTime = robot.time.milliseconds() + 1000;//wait one second to let beacon pusher extend
    }
}
