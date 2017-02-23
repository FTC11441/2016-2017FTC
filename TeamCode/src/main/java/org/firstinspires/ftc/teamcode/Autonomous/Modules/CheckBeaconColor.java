package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 2/21/2017.
 * Checks beacon color
 */

public class CheckBeaconColor extends Module {

    public CheckBeaconColor(){
        super(null);
    }
    @Override
    public void checkMovement(RobotAuto robot) {
        robot.nextStep();
    }

    @Override
    public void startMovement(RobotAuto robot) {
        int[] left = robot.colorSensors.getCRGB(Constants.Robot.BEACON_COLOR);
        //if red is greater than blue
        if (left[1] > left[3]) {
            robot.leftBeacon = Team.RED;
        } else {
            robot.leftBeacon = Team.BLUE;
        }
    }
}
