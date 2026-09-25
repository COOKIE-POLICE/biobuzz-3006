package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class TunedMotor {
    public final DcMotorEx motor;
    public final PIDFController controller;


    public TunedMotor(HardwareMap hardwareMap, String deviceName, double p, double i, double d, double f) {
        this.motor = hardwareMap.get(DcMotorEx.class, deviceName);
        this.controller = new PIDFController(p, i, d, f);
    }

    public void update(double targetTicks) {
        double currentTicks = motor.getVelocity();
        double absoluteTarget = Math.abs(targetTicks);
        double absoluteCurrent = Math.abs(currentTicks);
        double direction = Math.signum(targetTicks);
        motor.setPower(controller.calculate(absoluteCurrent, absoluteTarget) * direction);
    }

    public void setPIDF(double p, double i, double d, double f) {
        controller.setPIDF(p, i, d, f);
    }

    public void setTolerance(double positionTolerance) {
        controller.setTolerance(positionTolerance);
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        motor.setDirection(direction);
    }

    public boolean atSetPoint() {
        return controller.atSetPoint();
    }
}