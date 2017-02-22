package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderMove;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Stop;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Simple autonomous program
 */
@Autonomous(name = "Basic Auto", group = Group.TESTING)
public class BasicAutonomous extends OpMode {
    private AutonomousBase auto;

    private final Module[] steps = new Module[]{
            new EncoderMove(1, 1, Constants.DEFAULT_SPEED),
            new EncoderMove(1, -1, Constants.TURNING_SPEED),
            new EncoderMove(1, 5, Constants.TURNING_SPEED),
            new EncoderMove(-1, -1, Constants.DEFAULT_SPEED),
            new Stop()
    };

    @Override
    public void init() {
        auto = new AutonomousBase();
        auto.init(hardwareMap, Team.UNKNOWN, steps);
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_40);
    }

    @Override
    public void loop() {
        auto.loop();
        telemetry.addLine(auto.getRobot().debug());
    }
}
