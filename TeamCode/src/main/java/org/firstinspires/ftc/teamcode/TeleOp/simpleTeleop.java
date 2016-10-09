package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Utils.Mode;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Simple Teleop", group = "Test")
public class simpleTeleop extends OpMode {
    private Robot robot = new Robot();

    private double left;
    private double right;
    double max;

    @Override
    public void init() {

        robot.init(hardwareMap, Mode.TELEOP);
    }

    @Override
    public void loop() {

        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        //sets the robots motor power for both motors
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);
    }
}
