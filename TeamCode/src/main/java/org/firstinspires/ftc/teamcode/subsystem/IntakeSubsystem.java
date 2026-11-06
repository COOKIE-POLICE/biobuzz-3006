package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Preferences;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx leftIntakeMotor;
    private final DcMotorEx rightIntakeMotor;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        leftIntakeMotor = hardwareMap.get(DcMotorEx.class, Preferences.DeviceNames.LEFT_INTAKE);
        rightIntakeMotor = hardwareMap.get(DcMotorEx.class, Preferences.DeviceNames.RIGHT_INTAKE);
    }

    public void intake() {
        leftIntakeMotor.setPower(1.0);
        rightIntakeMotor.setPower(1.0);
    }

    public void eject() {
        leftIntakeMotor.setPower(-1.0);
        rightIntakeMotor.setPower(-1.0);
    }

    public void stop() {
        leftIntakeMotor.setPower(0.0);
        rightIntakeMotor.setPower(0.0);
    }


}
