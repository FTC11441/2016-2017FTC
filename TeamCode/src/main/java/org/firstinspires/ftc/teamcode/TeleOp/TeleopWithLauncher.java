package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.Robot;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Launcher Teleop", group = "Launcher")
@Deprecated
@Disabled
public class TeleopWithLauncher extends OpMode {
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

        double power = gamepad1.right_trigger;
        //robot.rightLauncher.setPower(power);
        robot.linearSlide.setPower(power);
    }
}
