package org.firstinspires.ftc.teamcode.Utils;

/**
 * Created by ethan.hampton on 11/9/2016.
 * <p>
 * States for autonomous
 */

public enum State {
    INIT, MOVE, UPDATEMOVE, STOP,
    @Deprecated WAIT//Currently not using WAIT, may move to unused in future
}
