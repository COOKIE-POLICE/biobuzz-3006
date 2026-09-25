package org.firstinspires.ftc.teamcode;

public class OuttakeSubsystem {
    private final TunedMotor tunedMotor;
    public OuttakeSubsystem() {
        this.tunedMotor = new TunedMotor("outtake", 0, 0, 0, 0);
    }
    public void start() {
        tunedMotor.update(1000);
    }

}