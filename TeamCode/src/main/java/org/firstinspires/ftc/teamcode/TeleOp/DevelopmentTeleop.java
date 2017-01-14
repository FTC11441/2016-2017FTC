package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Teleop for development with all the buttons and motors and servos
 */
@TeleOp(name = "Dev Teleop", group = Group.DEV)
public class DevelopmentTeleop extends OpMode {
    private static boolean linearSlideMoving = false;
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
            left = -left;
            right = -right;
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

        //bumper system
        if (gamepad1.left_bumper) {
            robot.leftBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
        } else {
            robot.leftBumper.setPosition(0);
        }
        if (gamepad1.right_bumper) {
            robot.rightBumper.setPosition(Constants.Teleop.BUMPER_UP_POSITION);
        } else {
            robot.rightBumper.setPosition(0);
        }

        //linear slide system system
        if (gamepad2.dpad_up) {//go to top
            robot.linearSlide.setTargetPosition(Constants.Teleop.LINEAR_SLIDE_UP);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_right) {//increment up
            robot.linearSlide.setTargetPosition(robot.linearSlide.getTargetPosition() + Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_down) {//go down to bottom
            robot.linearSlide.setTargetPosition(0);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(-0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_left) {//increment down
            robot.linearSlide.setTargetPosition(robot.linearSlide.getTargetPosition() - Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(-0.75);
            linearSlideMoving = true;
        }
        if (linearSlideMoving && !robot.linearSlide.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.linearSlide.setPower(0);
            linearSlideMoving = false;
        }
        if (Math.abs(gamepad2.left_stick_y) > 0.2) {//
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.linearSlide.setPower(gamepad2.left_stick_y);
            linearSlideMoving = false;
        }


        //forklift controls
        if (gamepad1.y && gamepad2.y) {
            robot.forklift.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
            /* if 1:50 has passed in teleop then we know we can release the forklift by one person*/
        } else if ((gamepad1.y || gamepad2.y) && robot.time.milliseconds() > Constants.Teleop.FORKLIFT_LOCK_UNTIL_TIME) {
            robot.forklift.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
        } else {
            robot.forklift.setPosition(Constants.Teleop.FORKLIFT_HOLD_POSITION);
        }

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

        //flipper controls
        if (gamepad2.b) {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_OUT);
            /* this allows the flipper to be extended fully on a single button press and then retraced */
        } else if (robot.flipper.getPosition() > (Constants.Teleop.FLIPPER_OUT - 0.05)) {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_IN);
        }


        robot.launcher.setPower(0);// TODO: 12/20/2016 add launching controls
        // TODO: 12/22/2016 add launcher manual override
    }
}
