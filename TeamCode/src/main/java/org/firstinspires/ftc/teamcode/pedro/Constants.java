package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Preferences;

import kotlin.jvm.internal.PackageReference;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, pinpointConfig),
                new Mecanum(h, mecanumConfig),
                new Foresight(foresightConfig)
        );
    }
    public static MecanumConfig mecanumConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set(Preferences.DeviceNames.LEFT_FRONT_MOTOR);
                c.backLeftName.set(Preferences.DeviceNames.LEFT_REAR_MOTOR);
                c.frontRightName.set(Preferences.DeviceNames.RIGHT_FRONT_MOTOR);
                c.backRightName.set(Preferences.DeviceNames.RIGHT_BACK_MOTOR);
                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            }
    );
    public static PinpointConfig pinpointConfig = new PinpointConfig(c -> {
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(c -> {});
}