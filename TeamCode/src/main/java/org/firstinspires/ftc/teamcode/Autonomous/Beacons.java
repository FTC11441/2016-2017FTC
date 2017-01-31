package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Beacons", group = Group.RELEASE)
public class Beacons extends OpMode {
    private AutonomousBase auto;

    private final double[][] redSteps = new double[][]{
            {0, -2, -2, -1},//forward
            {0, -5, -10, -1},//slight turn
            {0, -2, -2, 0.5},//move forward again
            {3},//follow line and get close, select beacon color
            {0, 0.3, 0.3, 0.4},//back up
            {4, 1},//put up correct pusher
            {2, 0.5},//wait to put put beacon
            {0, -0.4, -0.4, 0.4},//drive forward and push beacon
            {0, 1, 1, -1},//back up
            {5},//launch
            {-1}
    };
    private final double[][] blueSteps = new double[][]{
            //{0, -1, -1, -1},
            {-1}
    };

    @Override
    public void init() {
        auto = new AutonomousBase() {
            private Team leftBeacon = Team.UNKNOWN;

            @Override
            public boolean checkMovement(RobotAuto robot, double movementMode) {
                if (movementMode == 3) {
                    if (true/*robot.wallDistance.getLightDetected() > 0.5*/) {
                        //both are off line
                        if (robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] < 3000 && robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0] < 3000) {
                            robot.leftMotor.setPower(-0.1);
                            robot.rightMotor.setPower(-0.1);

                            //left is on line
                        } else if (robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] > 3000 && robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0] < 3000) {
                            robot.leftMotor.setPower(-0.1);
                            robot.rightMotor.setPower(-0.3);

                            //right is on line
                        } else if (robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] > 3000 && robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0] < 3000) {
                            robot.leftMotor.setPower(-0.3);
                            robot.rightMotor.setPower(-0.1);
                        }

                        telemetry.addLine(robot.leftMotor.getCurrentPosition() + ":" + robot.leftMotor.getPower());
                        telemetry.addLine(robot.rightMotor.getCurrentPosition() + ":" + robot.rightMotor.getPower());

                    } else {
                        //if greater than 50% red and less than 50% blue then it must be red
                        if (robot.colorSensors.getCRGB(Constants.Robot.BEACON_COLOR)[1] > 1000 && robot.colorSensors.getCRGB(Constants.Robot.BEACON_COLOR)[3] < 1000) {
                            leftBeacon = Team.RED;
                        } else {
                            leftBeacon = Team.BLUE;
                        }
                        robot.stopMotors();
                        return true;
                    }
                } else if (movementMode == 4) {
                    if (this.getSteps()[robot.currentStep][1] == 1) {
                        if (robot.getTeam() == leftBeacon) {
                            robot.leftBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
                            robot.rightBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
                        } else {
                            robot.rightBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
                            robot.leftBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
                        }
                    } else {
                        robot.leftBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
                        robot.rightBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
                    }
                    return true;
                } else if (movementMode == 5) {
                    if (!robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
                        robot.launcher.setPower(0);
                        return true;
                    }
                } else {
                    return true;
                }


                return false;
            }

            @Override
            public void startMovement(RobotAuto robot, double movementMode) {
                if (movementMode == 3) {
                    robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    robot.leftMotor.setPower(-0.1);
                    robot.rightMotor.setPower(-0.1);

                    robot.colorSensors.startPolling();
                } else if (movementMode == 5) {
                    robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //the LAUNCHER has a gear ratio of 2 to 1
                    robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
                    robot.launcher.setPower(0.75);
                }
            }

            @Override
            public double[][] getSteps() {
                if (auto.getRobot().getTeam().equals(Team.BLUE)) {
                    return blueSteps;
                } else {
                    return redSteps;
                }
            }
        };
        auto.init(hardwareMap);
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_60);

        if (auto.getRobot().teamTouch.isPressed()) {
            auto.getRobot().setTeam(Team.BLUE);
        } else {
            auto.getRobot().setTeam(Team.RED);
        }

        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
