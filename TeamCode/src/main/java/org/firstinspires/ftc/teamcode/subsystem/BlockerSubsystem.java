package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Preferences;

public class BlockerSubsystem extends SubsystemBase {
    private final Servo blocker;

    public BlockerSubsystem(HardwareMap hardwareMap) {
        this.blocker = hardwareMap.get(Servo.class, Preferences.DeviceNames.BLOCKER);
    }

    public void prevent() {
        this.blocker.setPosition(1.0);
    }

    public void admit() {
        this.blocker.setPosition(0.0);
    }
}
