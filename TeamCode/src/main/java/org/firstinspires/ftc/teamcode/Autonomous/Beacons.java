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
@Autonomous(name = "Beacons", group = Group.DEV)
public class Beacons extends OpMode {
    private AutonomousBase auto;

    private final double[][] redSteps = new double[][]{
            {0, 1.5, 1.5, Constants.DEFAULT_SPEED},//forward
            {0, 2, 5, Constants.TURNING_SPEED},//slight turn
            {0, 1.67, 1.67, Constants.DEFAULT_SPEED},//move forward again
            {0, 3.5, 0.3, Constants.TURNING_SPEED},//turn to go parallel to the wall
            {3},//align with the first beacon
            {0, -0.2, -0.2, Constants.DEFAULT_SPEED},//align with left beacon side
            {4,0.5},//move pusher forward
            {6},//read left beacon color and store it
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
                int[] left = robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR);
                int[] right = robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR);
                if (movementMode == 3) {
                    //both are off line
                    if (left[0] < 3000 && right[0] < 3000) {
                        robot.leftMotor.setPower(0.1);
                        robot.rightMotor.setPower(0.1);

                        //left is on line
                    } else if (left[0] > 3000 && right[0] < 3000) {
                        robot.leftMotor.setPower(0);
                        robot.rightMotor.setPower(0.3);

                        //right is on line
                    } else if (left[0] > 3000 && right[0] < 3000) {
                        robot.leftMotor.setPower(0.3);
                        robot.rightMotor.setPower(0);

                        //both on line
                    } else if (left[0] > 3000 && right[0] > 3000) {
                        robot.leftMotor.setPower(0);
                        robot.rightMotor.setPower(0);
                        return true;
                    }

                } else if (movementMode == 4) {
                    //wait for robot to be done moving servo
                    if(robot.time.milliseconds() > robot.waitTime) {
                        return true;
                    }
                }  else if (movementMode == 5) {
                    if (!robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
                        robot.launcher.setPower(0);
                        return true;
                    }
                    //NOTE: beacon detect mode does not need a check state so it just returns true
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
                    robot.leftMotor.setPower(0.1);
                    robot.rightMotor.setPower(0.1);

                    robot.colorSensors.startPolling();
                }else if (movementMode == 4){
                    //move bumper to a position and wait for some time to make sure it gets there
                    robot.bumper.setPosition(this.getSteps()[robot.currentStep][1]);
                    robot.waitTime = robot.time.milliseconds() + 3000;// FIXME: 2/7/2017 Change this
                } else if (movementMode == 5) {
                    robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //the LAUNCHER has a gear ratio of 2 to 1
                    robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
                    robot.launcher.setPower(0.75);
                }else if (movementMode == 6){
                    int[] left = robot.colorSensors.getCRGB(Constants.Robot.BEACON_COLOR);
                    //if red is greater than blue
                    if (left[1] > left[3]) {
                        leftBeacon= Team.RED;
                    }else{
                        leftBeacon = Team.BLUE;
                    }
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
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_40);

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
        telemetry.addLine(auto.getRobot().debug());
    }
}
