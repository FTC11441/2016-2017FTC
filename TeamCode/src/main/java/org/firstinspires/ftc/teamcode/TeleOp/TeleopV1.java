package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Teleop with all the buttons and motors and servos
 */
@TeleOp(name = "Teleop", group = Group.RELEASE)
public class TeleopV1 extends OpMode {
    private static boolean linearSlideMoving = false;
    private static boolean launcherMoving = false;
    private static boolean drivingInversed = false;

    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;
        //if we are driving backwards then change the speeds
        if (drivingInversed) {
            double tempRight = -left;
            double tempLeft = -right;
            right = tempRight;
            left = tempLeft;
        }
        //sets the robots motor power for both motors
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);

        //Inverse Driving controller
        if (gamepad1.dpad_up) {
            drivingInversed = false;
        }
        if (gamepad1.dpad_down) {
            drivingInversed = true;
        }

        //Enable and disable encoders for drive motors
        if (gamepad1.dpad_left) {
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (gamepad1.dpad_right) {
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }


        //bumper system
        if (gamepad1.left_bumper) {
            robot.leftBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
        } else {
            robot.leftBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
        }
        if (gamepad1.right_bumper) {
            robot.rightBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
        } else {
            robot.rightBumper.setPosition(Constants.Teleop.BUMPER_DOWN_POSITION);
        }

/*
        //linear slide system system
        if (gamepad2.dpad_up) {//go to top
            robot.LINEAR_SLIDE.setTargetPosition(Constants.Teleop.LINEAR_SLIDE_UP);
            robot.LINEAR_SLIDE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.LINEAR_SLIDE.setPower(0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_right) {//increment up
            robot.LINEAR_SLIDE.setTargetPosition(robot.LINEAR_SLIDE.getTargetPosition() + Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.LINEAR_SLIDE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.LINEAR_SLIDE.setPower(0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_down) {//go down to bottom
            robot.LINEAR_SLIDE.setTargetPosition(0);
            robot.LINEAR_SLIDE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.LINEAR_SLIDE.setPower(-0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_left) {//increment down
            robot.LINEAR_SLIDE.setTargetPosition(robot.LINEAR_SLIDE.getTargetPosition() - Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.LINEAR_SLIDE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.LINEAR_SLIDE.setPower(-0.75);
            linearSlideMoving = true;
        }
        if (linearSlideMoving && !robot.LINEAR_SLIDE.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.LINEAR_SLIDE.setPower(0);
            linearSlideMoving = false;
        }
        if (Math.abs(gamepad2.left_stick_y) > 0.2) {//
            robot.LINEAR_SLIDE.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.LINEAR_SLIDE.setPower(gamepad2.left_stick_y);
            linearSlideMoving = false;
        }


        //FORKLIFT controls
        if (gamepad1.y && gamepad2.y) {
            robot.FORKLIFT.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
            // if 1:50 has passed in teleop then we know we can release the FORKLIFT by one person
        } else if ((gamepad1.y || gamepad2.y) && robot.time.milliseconds() > Constants.Teleop.FORKLIFT_LOCK_UNTIL_TIME) {
            robot.FORKLIFT.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
        } else {
            robot.FORKLIFT.setPosition(Constants.Teleop.FORKLIFT_HOLD_POSITION);
        }
    */

        //tube controls
        if (gamepad2.left_trigger > 0.50) {
            robot.tube.setPower(Constants.DEFAULT_SPEED);
        } else {
            robot.tube.setPower(0);
        }

        //ball collector controls
        if (gamepad2.right_trigger > 0.50) {
            robot.collector.setPower(Constants.DEFAULT_SPEED);
        } else {
            robot.collector.setPower(0);
        }


        //FLIPPER controls
        if (gamepad2.b) {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_OUT);
            //this allows the FLIPPER to be extended fully on a single button press and then retraced
        } else {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_IN);
        }


        //LAUNCHER
        if (gamepad2.a && !launcherMoving) {
            robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //the LAUNCHER has a gear ratio of 2 to 1
            robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
            robot.launcher.setPower(0.8);
            launcherMoving = true;
        }

        if (launcherMoving && !robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.launcher.setPower(0);
            launcherMoving = false;
        }

        if (Math.abs(gamepad2.right_stick_y) > 0.2) {//control LAUNCHER manually
            robot.launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.launcher.setPower(gamepad2.right_stick_y);
            launcherMoving = false;
        }
    }
}
