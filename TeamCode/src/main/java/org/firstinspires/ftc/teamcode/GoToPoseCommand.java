package org.firstinspires.ftc.teamcode;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

public class GoToPoseCommand extends FollowPathCommand {

    public GoToPoseCommand(FollowerSubsystem followerSubsystem, Pose pose) {
        super(followerSubsystem.follower, Paths.line(followerSubsystem.follower.pose(), pose));
        addRequirements(followerSubsystem);
    }
}
