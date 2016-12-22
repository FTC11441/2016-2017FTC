package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Utils.Constants;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Teleop for development with all the buttons and motors and servos
 */
@TeleOp(name = "Dev Teleop", group = "Test")
public class DevelopmentTeleop extends OpMode {
    private static final int LINEAR_SLIDE_UP = Constants.ENCODER_TICKS_PER_ROTATION * 10;//Start with 10 rotations
    private static final int LINEAR_SLIDE_INCREMENT = Constants.ENCODER_TICKS_PER_ROTATION;//Increment 1 rotation per press

    private static final double BUMPER_UP_POSITION = 1L;

    private static final double FORKLIFT_RELEASE_POSITION = 1L;
    private static final double FORKLIFT_HOLD_POSITION = 0L;
    private static final double FORKLIFT_HOLD_UNTIL_TIME = 1000 * 110;

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
            robot.leftBumper.setPosition(BUMPER_UP_POSITION);
        } else {
            robot.leftBumper.setPosition(0);
        }
        if (gamepad1.right_bumper) {
            robot.rightBumper.setPosition(BUMPER_UP_POSITION);
        } else {
            robot.rightBumper.setPosition(0);
        }

        //linear slide system system
        if (gamepad2.dpad_up) {//go to top
            robot.linearSlide.setTargetPosition(LINEAR_SLIDE_UP);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(0.75);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_right) {//increment up
            robot.linearSlide.setTargetPosition(robot.linearSlide.getCurrentPosition() + LINEAR_SLIDE_INCREMENT);
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
            robot.linearSlide.setTargetPosition(robot.linearSlide.getCurrentPosition() - LINEAR_SLIDE_INCREMENT);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(-0.75);
            linearSlideMoving = true;
        }
        if (linearSlideMoving && !robot.linearSlide.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.linearSlide.setPower(0);
            linearSlideMoving = false;
        }


        //forklift controls
        if (gamepad1.y && gamepad2.y) {
            robot.forklift.setPosition(FORKLIFT_RELEASE_POSITION);
            /* if 1:50 has passed in teleop then we know we can release the forklift by one person*/
        } else if ((gamepad1.y || gamepad2.y) && robot.time.milliseconds() > FORKLIFT_HOLD_UNTIL_TIME) {
            robot.forklift.setPosition(FORKLIFT_RELEASE_POSITION);
        } else {
            robot.forklift.setPosition(FORKLIFT_HOLD_POSITION);
        }


        robot.flipper.setPosition(0);// TODO: 12/20/2016  add flipper controls
        robot.collector.setPower(0);// TODO: 12/20/2016 add collector controls
        robot.launcher.setPower(0);// TODO: 12/20/2016 add launching controls
        robot.tube.setPower(0);// TODO: 12/20/2016 add tube controls
    }
}
