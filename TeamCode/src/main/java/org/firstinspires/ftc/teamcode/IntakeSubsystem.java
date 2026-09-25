package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx intakeMotor;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
    }
    public void start() {
        intakeMotor.setPower(1.0);
    }
    public void eject() {
        intakeMotor.setPower(-1.0);
    }
    public void stop() {
        intakeMotor.setPower(0.0);
    }


}
