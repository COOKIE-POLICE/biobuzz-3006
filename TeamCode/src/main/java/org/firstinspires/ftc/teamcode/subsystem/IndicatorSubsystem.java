package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Preferences;

public class IndicatorSubsystem extends SubsystemBase {
    private final Servo leftIndicator;
    private final Servo rightIndicator;

    public IndicatorSubsystem(HardwareMap hardwareMap) {
        leftIndicator = hardwareMap.get(Servo.class, Preferences.DeviceNames.LEFT_INDICATOR);
        rightIndicator = hardwareMap.get(Servo.class, Preferences.DeviceNames.RIGHT_INDICATOR);
    }

    public void turnGreen() {

        leftIndicator.setPosition(0.5);
        rightIndicator.setPosition(0.5);
    }

    public void turnRed() {
        leftIndicator.setPosition(0.0);
        rightIndicator.setPosition(0.0);
    }

}
