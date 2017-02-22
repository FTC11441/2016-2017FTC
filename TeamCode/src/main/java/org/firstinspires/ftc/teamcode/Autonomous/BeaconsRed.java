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
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Beacons Red", group = Group.DEV)
public class BeaconsRed extends OpMode {
    private AutonomousBase auto;

    private final Module[] steps;

    public BeaconsRed() {
        steps = new Module[]{
                new EncoderMove(1,1,Constants.DEFAULT_SPEED),
                new Stop()
        };
    }


    @Override
    public void init() {
        auto = new AutonomousBase();
        auto.init(hardwareMap, Team.RED,steps);

        auto.getRobot().setEncoderTicksPerRotation(Constants.Robot.MOTOR_ENCODERS_USED);

        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    private boolean floor = false;

    @Override
    public void loop() {
        if (!floor) {
            auto.getRobot().colorSensors.startPolling();
            //get average of floor color so that we can accurately detect the floor
            auto.getRobot().floorReflection = (auto.getRobot().colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] + auto.getRobot().colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0]) / 2;
            floor = true;

            auto.getRobot().bumper.setPosition(Constants.Teleop.BUMPER_IN_POSITION);
            auto.getRobot().flipper.setPosition(Constants.Teleop.FLIPPER_IN);
            auto.getRobot().forklift.setPosition(Constants.Teleop.FORKLIFT_HOLD_POSITION);
        }
        auto.loop();
        telemetry.addLine(auto.getRobot().debug());
        telemetry.addLine("Left Beacon Color: " + auto.getRobot().leftBeacon.toString());
    }
}
