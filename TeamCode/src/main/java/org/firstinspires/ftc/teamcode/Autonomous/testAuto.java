package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 10/17/2016.
 * test auto
 */
@Autonomous(name = "Test Auto", group = "Test")
@Disabled
public class testAuto extends OpMode {
    private RobotAuto robot = new RobotAuto();

    private boolean run = false;

    @Override
    public void init() {
        robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        if (!run) {
            //robot.straight(10);
            run = true;
        }
        if (!robot.rightMotor.isBusy()) {
            robot.rightMotor.setPower(0);
        }
        if (!robot.leftMotor.isBusy()) {
            robot.leftMotor.setPower(0);
        }
        telemetry.addData("Status", "left = " + robot.leftMotor.getCurrentPosition() + " right = " + robot.rightMotor.getCurrentPosition());    //
        telemetry.addData("Busy", "left = " + robot.leftMotor.isBusy() + " right = " + robot.rightMotor.isBusy());
        telemetry.update();
    }
}
