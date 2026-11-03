package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class LazySusanSubsystem extends SubsystemBase {


    public final PIDFController controller;
    private final CRServo leftLazySusan;
    private final CRServo rightLazySusan;
    private final MotorEx lazySusanEncoder;

    public LazySusanSubsystem(HardwareMap hardwareMap) {
        leftLazySusan = hardwareMap.get(CRServo.class, Preferences.DeviceNames.LEFT_LAZY_SUSAN);
        rightLazySusan = hardwareMap.get(CRServo.class, Preferences.DeviceNames.RIGHT_LAZY_SUSAN);
        lazySusanEncoder = hardwareMap.get(MotorEx.class, Preferences.DeviceNames.LAZY_SUSAN_ENCODER);
        controller = new PIDFController(Preferences.LazySusanPIDF.p, Preferences.LazySusanPIDF.i, Preferences.LazySusanPIDF.d, Preferences.LazySusanPIDF.f);
    }

    public void updatePIDFController() {
        controller.setP(Preferences.LazySusanPIDF.p);
        controller.setI(Preferences.LazySusanPIDF.i);
        controller.setD(Preferences.LazySusanPIDF.d);
        controller.setF(Preferences.LazySusanPIDF.f);

    }

    public void setTargetRadians(double radians) {
        leftLazySusan.setPower(controller.calculate(getAngleInRadians(), radians));
    }

    public double getAngleInRadians() {
        return lazySusanEncoder.getCurrentPosition() * (2.0 * Math.PI) / (8192.0 * 3.0);
    }
}
