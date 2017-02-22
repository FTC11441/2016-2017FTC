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
    /**
     *
     * @param left distance in feet
     * @param right distance in feet
     * @param speed a speed from 0 to 1
     */
    public EncoderMove(double left, double right, double speed) {
        //sets var variables to proper values so program can store them
        super(new double[]{left,right,speed});
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

        //multiply by 12 because it is in feet
        double leftMotorDistance = vars[0] * 12;
        double rightMotorDistance = vars[1] * 12;

        double leftMotorRotations = leftMotorDistance / Constants.WHEEL_CIRCUMFERENCE;
        double rightMotorRotations = rightMotorDistance / Constants.WHEEL_CIRCUMFERENCE;
        double speed = vars[2];
        robot.setDrive(leftMotorRotations, rightMotorRotations, speed);//starts robot driving
    }
}
