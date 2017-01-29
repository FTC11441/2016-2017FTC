package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;

/**
 * Created by ethan.hampton on 10/12/2016.
 * test one rotation of encoder
 */
@TeleOp(name = "Encoder for Drive Motors", group = "Test")
@Disabled
public class Encoder extends OpMode {
    private SimpleRobot simpleRobot = new SimpleRobot();

    @Override
    public void init() {
        simpleRobot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Status", simpleRobot.leftMotor.getCurrentPosition() + "  " + simpleRobot.rightMotor.getCurrentPosition());    //
        telemetry.update();

        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;

        //sets the robots motor power for both motors
        simpleRobot.leftMotor.setPower(left);
        simpleRobot.rightMotor.setPower(right);
    }
}
