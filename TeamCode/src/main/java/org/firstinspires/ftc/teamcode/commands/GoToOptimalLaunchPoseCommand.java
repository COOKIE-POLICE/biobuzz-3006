package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.commands.followersubsystem.GoToPoseCommand;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.HiveDetectionSubsystem;

public class GoToOptimalLaunchPoseCommand extends GoToPoseCommand {
    public GoToOptimalLaunchPoseCommand(FollowerSubsystem followerSubsystem, HiveDetectionSubsystem hiveDetectionSubsystem) {
        super(followerSubsystem, hiveDetectionSubsystem.getOptimalLaunchPose());
    }
}
