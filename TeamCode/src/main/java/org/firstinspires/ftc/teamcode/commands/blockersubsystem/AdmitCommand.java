package org.firstinspires.ftc.teamcode.commands.blockersubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.BlockerSubsystem;

public class AdmitCommand extends CommandBase {

    private final BlockerSubsystem blockerSubsystem;

    public AdmitCommand(BlockerSubsystem blockerSubsystem) {
        this.blockerSubsystem = blockerSubsystem;
        addRequirements(this.blockerSubsystem);
    }

    @Override
    public void execute() {
        this.blockerSubsystem.admit();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
