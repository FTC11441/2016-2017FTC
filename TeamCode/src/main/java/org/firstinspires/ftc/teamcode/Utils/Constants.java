package org.firstinspires.ftc.teamcode.Utils;

/**
 * Created by ethan.hampton on 10/11/2016.
 * constants for use
 */

public class Constants {
    public static final int ENCODER_TICKS_PER_ROTATION_40 = 1120;
    public static final int ENCODER_TICKS_PER_ROTATION_60 = 1560;
    public static final int MAX_MOTOR_RPM = 105;
    public static final int DEFAULT_SPEED = 50;
    public static final int MAX_MOTOR_TICKS_PER_SECOND = MAX_MOTOR_RPM * ENCODER_TICKS_PER_ROTATION_40;

    public static final double TIMEOUT = 10000;//maximum length of an autonomous step

    public class Teleop {
        public static final int LINEAR_SLIDE_INCREMENT = ENCODER_TICKS_PER_ROTATION_60;//Increment 1 rotation per press
        public static final int LINEAR_SLIDE_UP = ENCODER_TICKS_PER_ROTATION_60 * 10;//Start with 10 rotations

        public static final double BUMPER_UP_POSITION = 0.7;

        public static final double FORKLIFT_RELEASE_POSITION = 1L;
        public static final double FORKLIFT_HOLD_POSITION = 0L;
        public static final double FORKLIFT_LOCK_UNTIL_TIME = 1000 * 110;//1:50 into match is 110 seconds

        public static final double FLIPPER_OUT = 1L;
        public static final double FLIPPER_IN = 0L;
    }

}
