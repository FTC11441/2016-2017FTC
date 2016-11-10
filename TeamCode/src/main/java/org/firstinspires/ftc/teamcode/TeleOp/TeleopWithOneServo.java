package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration.Robot;
import org.firstinspires.ftc.teamcode.Configuration.SimpleRobot;

/**
 * Created by ethan.hampton on 11/9/2016.
 * <p>
 * one servo and both controllers do same thing
 */


@TeleOp(name = "Teleop With Bumper", group = "Servo")
public class TeleopWithOneServo extends OpMode {
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

        if(gamepad1.left_bumper||gamepad2.left_bumper||gamepad1.right_bumper||gamepad2.right_bumper){
            robot.bumper.setPosition(1);
        }else{
            robot.bumper.setPosition(0);
        }

    }
}
