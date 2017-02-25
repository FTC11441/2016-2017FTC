package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Servo Only", group = Group.FEATURE)
@Disabled
public class ServoTeleop extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
     robot.Init(hardwareMap);

    }

    @Override
    public void loop() {
        robot.forklift.setPosition(gamepad1.right_stick_y );
        telemetry.addLine(robot.forklift.getPosition()+"");
    }
}
