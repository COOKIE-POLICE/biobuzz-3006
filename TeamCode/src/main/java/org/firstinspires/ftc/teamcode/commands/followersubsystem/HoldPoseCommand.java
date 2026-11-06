package org.firstinspires.ftc.teamcode.commands.followersubsystem;

import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.pedroCommand.HoldPointCommand;

import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;

// This might seem redundant to wrap this, but the reason I did this is cuz then teleOp can interrupt auto pathing if I do this.
// Ima also wrap the other solvers lib pedro commands.
public class HoldPoseCommand extends HoldPointCommand {
    public HoldPoseCommand(FollowerSubsystem followerSubsystem, Pose pose) {
        super(followerSubsystem.follower, pose, false);
        addRequirements(followerSubsystem);
    }
}
