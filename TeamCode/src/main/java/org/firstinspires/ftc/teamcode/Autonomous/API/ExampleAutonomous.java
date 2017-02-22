package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderMove;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Stop;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * An example program to show what we can do with the autonomous API
 */
@Autonomous(name = "Example Autonomous", group = "Example")
@Deprecated
@Disabled
public class ExampleAutonomous extends OpMode {
    AutonomousBase auto = new AutonomousBase();

    @Override
    public void init() {
        auto.init(hardwareMap, Team.UNKNOWN,new Module[]{
                new EncoderMove(1,1,0.75),
                new Stop()
        });
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
