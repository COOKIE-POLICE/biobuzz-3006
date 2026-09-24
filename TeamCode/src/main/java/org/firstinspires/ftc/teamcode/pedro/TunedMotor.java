package org.firstinspires.ftc.teamcode.pedro;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class TunedMotor {
    public final DcMotorEx motor;
    public final PIDFController controller;
    public double targetVelocity;

    public TunedMotor(DcMotorEx motor, double p, double i, double d, double f) {
        this.motor = motor;
        this.controller = new PIDFController(p, i, d, f);
        this.targetVelocity = 0.0;
    }

    public void setTargetTicks(double ticks) {
        this.targetVelocity = ticks;
    }
    public double getTicks() {
        return this.motor.getVelocity();
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

    public void update(double currentVelocity) {
        double absoluteTarget = Math.abs(targetVelocity);
        double absoluteCurrent = Math.abs(currentVelocity);
        double direction = Math.signum(targetVelocity);
        motor.setPower(controller.calculate(absoluteCurrent, absoluteTarget) * direction);
    }

    public boolean atSetPoint() {
        return controller.atSetPoint();
    }
}