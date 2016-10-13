package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration.Robot;

/**
 * Created by ethan.hampton on 10/12/2016.
 * test one rotation of encoder
 */
@TeleOp(name = "Encoder", group = "Test")
public class Encoder extends OpMode {
    private Robot robot = new Robot();

    private double power;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        power = gamepad1.left_stick_y;

        telemetry.addData("Status", robot.rightMotor.getCurrentPosition() + "  " + robot.leftMotor.getCurrentPosition());    //
        telemetry.update();

        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);
    }
}
