package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderMove;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Stop;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Simple autonomous program
 */
@Autonomous(name = "Ramp Blue", group = Group.TESTING)
public class RampBlue extends OpMode {
    private AutonomousBase auto;

    private final Module[] steps = new Module[]{
            new Wait(15),
            new EncoderMove(0.5, 0.5, Constants.DEFAULT_SPEED),
            new EncoderMove(2, -2, Constants.TURNING_SPEED),
            new EncoderMove(4, 4, Constants.DEFAULT_SPEED),
            new Stop()
    };

    @Override
    public void init() {
        auto = new AutonomousBase();
        // TODO: 2/21/2017 add different teams and stuff
        auto.init(hardwareMap, Team.UNKNOWN, steps);
    }

    @Override
    public void loop() {
        auto.loop();
        telemetry.addLine(auto.getRobot().debug());
    }
}
