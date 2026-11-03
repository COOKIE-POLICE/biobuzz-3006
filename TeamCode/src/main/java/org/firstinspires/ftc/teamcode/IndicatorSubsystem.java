package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IndicatorSubsystem extends SubsystemBase {
    private final Servo indicator;

    public IndicatorSubsystem(HardwareMap hardwareMap) {
        indicator = hardwareMap.get(Servo.class, Preferences.DeviceNames.INDICATOR);
    }

    public void turnGreen() {
        indicator.setPosition(0.5);
    }
    public void turnRed() {
        indicator.setPosition(0.0);
    }

}
