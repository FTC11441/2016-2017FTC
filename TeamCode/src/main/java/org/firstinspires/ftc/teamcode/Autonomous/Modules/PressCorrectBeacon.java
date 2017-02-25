package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 2/21/2017.
 * <p>
 * Presses the beacon
 */

public class PressCorrectBeacon extends Module {

    public PressCorrectBeacon() {
        super(null);
    }

    @Override
    public void checkMovement(RobotAuto robot) {
        if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {
            robot.nextStep();
            robot.bumper.setPosition(Constants.Teleop.BUMPER_OUT_POSITION);
        }

    }

    @Override
    public void startMovement(RobotAuto robot) {
        //if the beacon is in the right position then extend the bumper
        if (robot.getTeam() == robot.leftBeacon) {
            robot.bumper.setPosition(Constants.Teleop.BUMPER_OUT_POSITION);
            robot.nextStep();
            //if not then move forward and then extend the bumper
        } else {
            double move = Constants.Teleop.DISTANCE_BETWEEN_BUTTONS;
            //reverse distance for the blue side because it is reversed
            if (robot.getTeam() == Team.BLUE) {
                move = -move;
            }
            robot.setDrive(move, move, Constants.TURNING_SPEED-0.03);
        }
    }
}
