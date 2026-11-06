package org.firstinspires.ftc.teamcode.commands.followersubsystem;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;

public class FieldCentricCommand extends CommandBase {
    private final FollowerSubsystem followerSubsystem;

    public FieldCentricCommand(FollowerSubsystem followerSubsystem) {
        this.followerSubsystem = followerSubsystem;
        addRequirements(followerSubsystem);
    }

    @Override
    public void execute() {
        followerSubsystem.fieldCentric();
    }
}
