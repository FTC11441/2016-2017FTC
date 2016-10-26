package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration.Robot;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Teleop with servos
 */

@TeleOp(name = "Teleop W/ Servos", group = "Servo")
public class teleopWithServos extends OpMode {
    private Robot simpleRobot = new Robot();

    private double left;
    private double right;

    @Override
    public void init() {
        simpleRobot.simpleInit(hardwareMap);
    }

    @Override
    public void loop() {
        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        //sets the robots motor power for both motors
        simpleRobot.leftMotor.setPower(left);
        simpleRobot.rightMotor.setPower(right);
    }
}
