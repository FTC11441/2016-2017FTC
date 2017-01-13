package org.firstinspires.ftc.teamcode.Utils;

import android.util.SparseArray;

/**
 * Created by ethan.hampton on 10/18/2016.
 * type of motor
 */

public enum Motor {
    LEFT(1), RIGHT(2), BOTH(3);

    private final int value;
    private static final SparseArray<Motor> map = new SparseArray<>();

    Motor(int value) {
        this.value = value;
    }

    static {
        for (Motor motor : Motor.values()) {
            map.put(motor.value, motor);
        }
    }

    public int getValue() {
        return value;
    }
}
