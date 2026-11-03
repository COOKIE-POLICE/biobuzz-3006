package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class LazySusanSubsystem extends SubsystemBase {

    private static final double ZERO_OFFSET = Math.toRadians(90.0);

    private static final double TICKS_PER_REV = 8192.0;
    private static final double SERVO_REVS_PER_OUTPUT_REV = 3.0;

    private static final double RADIANS_PER_TICK =
            (2.0 * Math.PI) /
                    (TICKS_PER_REV * SERVO_REVS_PER_OUTPUT_REV);

    private static final double ANGLE_TOLERANCE =
            Math.toRadians(1.0);

    private static final double MAX_POWER = 0.7;
    private static final double ENCODER_DIRECTION = 1.0;
    private static final double OUTPUT_DIRECTION = 1.0;

    public final PIDFController controller;

    private final CRServo leftLazySusan;
    private final CRServo rightLazySusan;

    private final MotorEx lazySusanEncoder;
    private final FollowerSubsystem followerSubsystem;

    public LazySusanSubsystem(
            HardwareMap hardwareMap,
            FollowerSubsystem followerSubsystem
    ) {
        leftLazySusan = hardwareMap.get(
                CRServo.class,
                Preferences.DeviceNames.LEFT_LAZY_SUSAN
        );

        rightLazySusan = hardwareMap.get(
                CRServo.class,
                Preferences.DeviceNames.RIGHT_LAZY_SUSAN
        );

        lazySusanEncoder = new MotorEx(
                hardwareMap,
                Preferences.DeviceNames.LAZY_SUSAN_ENCODER
        );

        controller = new PIDFController(
                Preferences.LazySusanPIDF.p,
                Preferences.LazySusanPIDF.i,
                Preferences.LazySusanPIDF.d,
                0.0
        );

        this.followerSubsystem = followerSubsystem;
        // rightLazySusan.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private static double wrapRadians(double angle) {
        return Math.atan2(
                Math.sin(angle),
                Math.cos(angle)
        );
    }

    public double getCurrentAngle() {
        double encoderAngle =
                lazySusanEncoder.getCurrentPosition()
                        * ENCODER_DIRECTION
                        * RADIANS_PER_TICK;

        return wrapRadians(encoderAngle + ZERO_OFFSET);
    }

    public void update() {
        double currentAngle = getCurrentAngle();
        double targetAngle = followerSubsystem.angleToHive();

        double error = wrapRadians(targetAngle - currentAngle);

        if (Math.abs(error) <= ANGLE_TOLERANCE) {
            controller.reset();
            setPower(0.0);
            return;
        }

        double output = controller.calculate(-error, 0.0);

        output *= OUTPUT_DIRECTION;
        output = Range.clip(output, -MAX_POWER, MAX_POWER);

        setPower(output);
    }

    private void setPower(double power) {
        leftLazySusan.setPower(power);
        // rightLazySusan.setPower(power);
    }


    public void updatePIDFController() {
        controller.setP(Preferences.LazySusanPIDF.p);
        controller.setI(Preferences.LazySusanPIDF.i);
        controller.setD(Preferences.LazySusanPIDF.d);
        controller.setF(Preferences.LazySusanPIDF.f);
    }
}
