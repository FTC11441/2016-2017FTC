package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by ethan.hampton on 10/4/2016.
 *
 * Very simple Teleop
 */

@TeleOp(name = "Simple Teleop",group = "Test")
public class simpleTeleop extends OpMode {
    private Robot robot = new Robot();

    double left;
    double right;
    double max;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        left  = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        //sets the robots motor power for both motors
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);
    }
}
