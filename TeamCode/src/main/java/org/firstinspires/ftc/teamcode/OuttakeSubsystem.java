package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class OuttakeSubsystem extends SubsystemBase {
    private final TunedMotor tunedMotor;
    public OuttakeSubsystem(HardwareMap hardwareMap) {
        this.tunedMotor = new TunedMotor(hardwareMap, "outtake", 0, 0, 0, 0);
    }
    public void start() {
        tunedMotor.update(1000);
    }
    public void stop() {
        tunedMotor.update(0);
    }

}