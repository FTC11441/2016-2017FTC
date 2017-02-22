package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;

/**
 * Created by ethan.hampton on 2/21/2017.
 * launches a particle
 */

public class LaunchParticle extends Module {

    protected LaunchParticle() {
        super(null);
    }

    @Override
    public void checkMovement(RobotAuto robot) {
        if (!robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
            robot.launcher.setPower(0);
            robot.nextStep();
        }
    }

    @Override
    public void startMovement(RobotAuto robot) {
        robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //the LAUNCHER has a gear ratio of 2 to 1
        robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
        robot.launcher.setPower(Constants.Teleop.LAUNCHER_SPEED);
    }
}
