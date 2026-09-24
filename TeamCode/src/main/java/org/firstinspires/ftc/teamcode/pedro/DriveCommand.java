package org.firstinspires.ftc.teamcode.pedro;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.CommandBase;
public class DriveCommand extends CommandBase {
    private final FollowerSubsystem followerSubsystem;
    public DriveCommand(FollowerSubsystem followerSubsystem) {
        this.followerSubsystem = followerSubsystem;
    }
    @Override
    public void execute() {
        followerSubsystem.manual();
    }
}
