package org.firstinspires.ftc.teamcode.Utils;

/**
 * Created by ethan.hampton on 10/11/2016.
 * constants for use in all programs, so you can change one variable here and it will automatically be put
 * into all the other programs
 */

public class Constants {
    public static final int ENCODER_TICKS_PER_ROTATION_40 = 1120;
    public static final int ENCODER_TICKS_PER_ROTATION_60 = 1680;
    public static final int MAX_MOTOR_RPM = 160;
    public static final double DEFAULT_SPEED = 0.75;
    public static final double TURNING_SPEED = 0.5;
    public static final int MAX_MOTOR_TICKS_PER_SECOND = MAX_MOTOR_RPM * Robot.MOTOR_ENCODERS_USED;

    public static final double TIMEOUT = 10000;//maximum length of an autonomous step

    //how much the light needs to change to consider it to be a change
    public static final int LIGHT_CHANGE = 400;

    public class Teleop {
        public static final int LINEAR_SLIDE_INCREMENT = ENCODER_TICKS_PER_ROTATION_60 / 2;//Increment 1/2 rotation per press
        public static final int LINEAR_SLIDE_UP = ENCODER_TICKS_PER_ROTATION_60 * 5;//Start with 10 rotations
        public static final double LINEAR_SLIDE_SPEED = 0.75;

        public static final int LAUNCHER_ROTATIONS = (int) (Constants.ENCODER_TICKS_PER_ROTATION_60 * 2);
        public static final double LAUNCHER_SPEED = 0.8;

        public static final double BUMPER_OUT_POSITION = 0;
        public static final double BUMPER_IN_POSITION = 1;
        public static final double DISTANCE_BETWEEN_BUTTONS = 0.5;//how many rotations between beacon buttons

        public static final double FORKLIFT_RELEASE_POSITION = 1;
        public static final double FORKLIFT_HOLD_POSITION = 0.5;
        public static final double FORKLIFT_LOCK_UNTIL_TIME = 1000 * 75;//1:15 into match is 75 seconds

        public static final double FLIPPER_OUT = 1;
        public static final double FLIPPER_IN = 0.5;
    }

    public class Robot {
        public static final String RIGHT_MOTOR = "rightMotor";
        public static final String LEFT_MOTOR = "leftMotor";
        public static final String BALL_COLLECTOR = "collector";
        public static final String BALL_TUBE = "tube";
        public static final String BUMPER = "bumper";
        public static final String LINEAR_SLIDE = "linearSlide";
        public static final String LAUNCHER = "launcher";
        public static final String FORKLIFT = "forklift";
        public static final String FLIPPER = "flipper";
        public static final String TEAM_TOUCH = "team";

        public static final int MOTOR_ENCODERS_USED = Constants.ENCODER_TICKS_PER_ROTATION_40;

        public static final int LEFT_COLOR = 0;
        public static final int RIGHT_COLOR = 1;
        public static final int BEACON_COLOR = 2;
    }


}
