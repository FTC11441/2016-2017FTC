package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Simple Teleop", group = "Test")
public class simpleTeleop extends OpMode {
    private SimpleRobot simpleRobot = new SimpleRobot();

    @Override
    public void init() {
        simpleRobot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;

        //sets the robots motor power for both motors
        simpleRobot.leftMotor.setPower(left);
        simpleRobot.rightMotor.setPower(right);
    }
}
