package org.firstinspires.ftc.teamcode.commands.followersubsystem;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;

public class GoToPoseCommand extends FollowPathCommand {

    public GoToPoseCommand(FollowerSubsystem followerSubsystem, Pose pose) {
        super(followerSubsystem.follower, Paths.line(followerSubsystem.follower.pose(), pose));
        addRequirements(followerSubsystem);
    }
}
