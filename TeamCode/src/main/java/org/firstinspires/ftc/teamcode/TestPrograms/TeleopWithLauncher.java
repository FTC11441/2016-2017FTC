package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Teleop W/ Launcher", group = Group.FEATURE)
public class TeleopWithLauncher extends OpMode {
    private SimpleRobot robot = new SimpleRobot();

    private DcMotor lifter;
    private DcMotor collector;

    @Override
    public void init() {
        robot.Init(hardwareMap);
        lifter = hardwareMap.dcMotor.get("lifter");
        lifter.setDirection(DcMotorSimple.Direction.REVERSE);

        collector = hardwareMap.dcMotor.get("collector");
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

        double lpower = gamepad1.right_trigger;
        lifter.setPower(lpower);

        double cpower = gamepad1.left_trigger;
        collector.setPower(cpower);
    }
}
