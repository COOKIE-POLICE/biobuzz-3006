package org.firstinspires.ftc.teamcode;


import com.seattlesolvers.solverslib.command.CommandBase;
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
