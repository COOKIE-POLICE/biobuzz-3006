package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Side;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.hivedetectionsubsystem.HiveDetectionSubsystem;

public class UpdateHiveDetectionSubsystemPipeline extends CommandBase {
    private final FollowerSubsystem followerSubsystem;
    private final HiveDetectionSubsystem hiveDetectionSubsystem;

    public UpdateHiveDetectionSubsystemPipeline(HiveDetectionSubsystem hiveDetectionSubsystem, FollowerSubsystem followerSubsystem) {
        this.hiveDetectionSubsystem = hiveDetectionSubsystem;
        this.followerSubsystem = followerSubsystem;
    }

    @Override
    public void execute() {
        if (followerSubsystem.getSide() == Side.Audience) {
            hiveDetectionSubsystem.switchToAudiencePipeline();
        } else if (followerSubsystem.getSide() == Side.Scoring) {
            hiveDetectionSubsystem.switchToScoringPipeline();
        }
    }
}
