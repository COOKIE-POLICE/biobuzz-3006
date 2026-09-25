package org.firstinspires.ftc.teamcode;


import com.seattlesolvers.solverslib.command.CommandBase;
public class RobotCentricCommand extends CommandBase {
    private final FollowerSubsystem followerSubsystem;
    public RobotCentricCommand(FollowerSubsystem followerSubsystem) {
        this.followerSubsystem = followerSubsystem;
        addRequirements(followerSubsystem);
    }
    @Override
    public void execute() {
        followerSubsystem.robotCentric();
    }
}
