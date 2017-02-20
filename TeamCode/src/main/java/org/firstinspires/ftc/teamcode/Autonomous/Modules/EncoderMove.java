package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;

/**
 * Created by ethan.hampton on 2/20/2017.
 * <p>
 * move using encoders
 */

public class EncoderMove extends Module {

    EncoderMove(double[] vars) {
        super(vars);
    }

    @Override
    public void checkMovement(RobotAuto robot) {
        if (robot.time.milliseconds() < robot.startTime + Constants.TIMEOUT) {//insure that robot is no waiting for something that won't happen
            if ((!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) || (robot.motorDone(robot.leftMotor) && robot.motorDone(robot.rightMotor))) {
                robot.stopMotors();
                robot.nextStep();
            }
        } else {//if we think that robot has timed out
            robot.nextStep();
        }
    }

    @Override
    public void startMovement(RobotAuto robot) {
        //sets variables to input into drive method of robot
        double leftMotorRotations = vars[0];
        double rightMotorRotations = vars[1];
        double speed = vars[2];
        robot.setDrive(leftMotorRotations, rightMotorRotations, speed);//starts robot driving
    }
}
