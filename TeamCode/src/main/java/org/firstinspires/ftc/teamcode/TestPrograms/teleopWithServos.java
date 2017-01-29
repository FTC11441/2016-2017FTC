package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Teleop with servos
 */

@TeleOp(name = "Simple Teleop W/ Servos", group = Group.TESTING)
@Disabled
public class teleopWithServos extends OpMode {
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

        if (gamepad1.left_bumper) {
            robot.leftBumper.setPosition(1L);
        } else {
            robot.leftBumper.setPosition(0);
        }
        if (gamepad1.right_bumper) {
            robot.rightBumper.setPosition(1L);
        } else {
            robot.rightBumper.setPosition(0);
        }
    }
}
