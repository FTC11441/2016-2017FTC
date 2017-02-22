package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.State;

/**
 * Created by ethan.hampton on 2/21/2017.
 * <p>
 * Stops robot
 */

public class Stop extends Module {
    public Stop() {
        super(null);
    }

    @Override
    public void checkMovement(RobotAuto robot) {

    }

    @Override
    public void startMovement(RobotAuto robot) {
        robot.currentState = State.STOP;
    }
}
