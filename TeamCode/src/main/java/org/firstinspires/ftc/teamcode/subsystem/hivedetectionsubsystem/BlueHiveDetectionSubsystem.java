package org.firstinspires.ftc.teamcode.subsystem.hivedetectionsubsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Preferences;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;

public class BlueHiveDetectionSubsystem extends HiveDetectionSubsystem {
    public BlueHiveDetectionSubsystem(HardwareMap hardwareMap, FollowerSubsystem followerSubsystem) {
        super(
                hardwareMap,
                followerSubsystem,
                Preferences.LimelightPipelines.blueAudience,
                Preferences.LimelightPipelines.blueScoring,
                Preferences.AprilTagIdentifiers.blueAudience,
                Preferences.AprilTagIdentifiers.blueScoring
        );
    }
}
