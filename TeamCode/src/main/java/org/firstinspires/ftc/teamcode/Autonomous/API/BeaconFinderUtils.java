package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 2/7/2017.
 * <p>
 * Utility class to find beacons and other tasks
 */

public class BeaconFinderUtils {

    public static void startMovement(RobotAuto robot, double movementMode, double[][] steps) {
        if (movementMode == 3) {
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftMotor.setPower(0.1);
            robot.rightMotor.setPower(0.1);

            robot.colorSensors.startPolling();
        } else if (movementMode == 4) {
            //move bumper to a position and wait for some time to make sure it gets there
            robot.bumper.setPosition(steps[robot.currentStep][1]);
            robot.waitTime = robot.time.milliseconds() + 1000;//wait one second to let beacon pusher extend
        } else if (movementMode == 5) {
            robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //the LAUNCHER has a gear ratio of 2 to 1
            robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
            robot.launcher.setPower(0.75);
        } else if (movementMode == 6) {
            int[] left = robot.colorSensors.getCRGB(Constants.Robot.BEACON_COLOR);
            //if red is greater than blue
            if (left[1] > left[3]) {
                robot.leftBeacon = Team.RED;
            } else {
                robot.leftBeacon = Team.BLUE;
            }
        } else if (movementMode == 7) {

            //if the beacon is in the right position then extend the bumper
            if (robot.getTeam() == robot.leftBeacon) {
                robot.bumper.setPosition(Constants.Teleop.BUMPER_OUT_POSITION);
                robot.nextStep();
                //if not then move forward and then extend the bumper
            } else {
                double move = Constants.Teleop.DISTANCE_BETWEEN_BUTTONS;
                //reverse distance for the blue side because it is reversed
                if (robot.getTeam() == Team.BLUE) {
                    move = -move;
                }
                robot.setDrive(move, move, Constants.DEFAULT_SPEED);// TODO: 2/8/2017 Change this to work correctly
            }
        }
    }

    public static boolean checkMovement(RobotAuto robot, double movementMode) {
        int light = robot.floorReflection;
        final int add = Constants.LIGHT_CHANGE;
        int value = light + add;
        if (movementMode == 3) {
            int[] left = robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR);
            int[] right = robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR);
            //both are off line
            if (left[0] < value && right[0] < value) {
                robot.leftMotor.setPower(0.1);
                robot.rightMotor.setPower(0.1);

                //left is on line
            } else if (left[0] > value && right[0] < value) {
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0.3);

                //right is on line
            } else if (left[0] > value && right[0] < value) {
                robot.leftMotor.setPower(0.3);
                robot.rightMotor.setPower(0);

                //both on line
            } else if (left[0] > value && right[0] > value) {
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);
                return true;
            }

        } else if (movementMode == 4) {
            //wait for robot to be done moving servo
            if (robot.time.milliseconds() > robot.waitTime) {
                return true;
            }
        } else if (movementMode == 5) {
            if (!robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
                robot.launcher.setPower(0);
                return true;
            }
        } else if (movementMode == 7) {
            if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {
                robot.nextStep();
                robot.bumper.setPosition(Constants.Teleop.BUMPER_OUT_POSITION);
            }

            //NOTE: beacon detect mode does not need a check state so it just returns true
        } else {
            return true;
        }
        return false;
    }
}
