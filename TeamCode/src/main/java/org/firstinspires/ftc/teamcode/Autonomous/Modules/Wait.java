package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 2/20/2017.
 *
 * waits for a time
 */

public class Wait extends Module {


    protected Wait(double secondsWait) {
        super(new double[]{secondsWait});
    }

    @Override
    public void checkMovement(RobotAuto robot) {
        if (robot.time.milliseconds() >= robot.waitTime) {
            robot.nextStep();
        }
    }

    @Override
    public void startMovement(RobotAuto robot) {
        double timeInMilliseconds = vars[0] * 1000;
        robot.waitTime = robot.startTime + timeInMilliseconds;
        robot.stopMotors();
    }
}
