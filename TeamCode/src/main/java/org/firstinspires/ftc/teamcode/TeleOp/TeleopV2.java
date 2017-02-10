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
public class TeleopV2 extends OpMode {
    private static boolean linearSlideMoving = false;
    private static boolean launcherMoving = false;
    private static boolean forkliftLockedUp = false;

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

        //sets the robots motor power for both motors
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);


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
        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            robot.bumper.setPosition(Constants.Teleop.BUMPER_OUT_POSITION);
        } else {
            robot.bumper.setPosition(Constants.Teleop.BUMPER_IN_POSITION);
        }


        //linear slide system system
        if (gamepad2.dpad_up) {//go to top
            robot.linearSlide.setTargetPosition(Constants.Teleop.LINEAR_SLIDE_UP);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(Constants.Teleop.LINEAR_SLIDE_SPEED);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_right) {//increment up
            robot.linearSlide.setTargetPosition(robot.linearSlide.getTargetPosition() + Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(Constants.Teleop.LINEAR_SLIDE_SPEED);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_down) {//go down to bottom
            robot.linearSlide.setTargetPosition(0);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(-Constants.Teleop.LINEAR_SLIDE_SPEED);
            linearSlideMoving = true;
        }
        if (gamepad2.dpad_left) {//increment down
            robot.linearSlide.setTargetPosition(robot.linearSlide.getTargetPosition() - Constants.Teleop.LINEAR_SLIDE_INCREMENT);
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.linearSlide.setPower(-Constants.Teleop.LINEAR_SLIDE_SPEED);
            linearSlideMoving = true;
        }
        if (linearSlideMoving && !robot.linearSlide.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.linearSlide.setPower(0);
            linearSlideMoving = false;
        }
        if (!linearSlideMoving) {//control linear slide
            robot.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.linearSlide.setPower(gamepad2.left_stick_y);
            linearSlideMoving = false;
        }
        telemetry.addLine("Linear slide target: " + robot.linearSlide.getTargetPosition());

        //FORKLIFT controls
        if (gamepad1.y && gamepad2.y) {
            robot.forklift.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
            forkliftLockedUp = true;

            // if 1:50 has passed in teleop then we know we can release the FORKLIFT by one person
        } else if ((gamepad1.y || gamepad2.y) && robot.time.milliseconds() > Constants.Teleop.FORKLIFT_LOCK_UNTIL_TIME) {
            robot.forklift.setPosition(Constants.Teleop.FORKLIFT_RELEASE_POSITION);
            forkliftLockedUp = true;
        } else if (!forkliftLockedUp) {
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


        //Flipper controls
        if (gamepad2.b) {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_OUT);
            //this allows the flipper to be extended fully on a single button press and then retraced
        } else {
            robot.flipper.setPosition(Constants.Teleop.FLIPPER_IN);
        }


        //Launcher
        if (gamepad2.a && !launcherMoving) {
            robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //The launcher has a gear ratio of 2 to 1
            robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
            robot.launcher.setPower(Constants.Teleop.LAUNCHER_SPEED);
            launcherMoving = true;
        }

        if (launcherMoving && !robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.launcher.setPower(0);
            launcherMoving = false;
        }

        if (!launcherMoving) {//control launcher manually
            robot.launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.launcher.setPower(gamepad2.right_stick_y);
            launcherMoving = false;
        }
    }
}
