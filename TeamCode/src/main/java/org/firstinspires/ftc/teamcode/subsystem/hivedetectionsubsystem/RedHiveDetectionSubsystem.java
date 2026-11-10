package org.firstinspires.ftc.teamcode.subsystem.hivedetectionsubsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Preferences;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;

public class RedHiveDetectionSubsystem extends HiveDetectionSubsystem {
    public RedHiveDetectionSubsystem(HardwareMap hardwareMap, FollowerSubsystem followerSubsystem) {
        super(
                hardwareMap,
                followerSubsystem,
                Preferences.LimelightPipelines.redAudience,
                Preferences.LimelightPipelines.redScoring,
                Preferences.AprilTagIdentifiers.redAudience,
                Preferences.AprilTagIdentifiers.redScoring
        );

    }
}
