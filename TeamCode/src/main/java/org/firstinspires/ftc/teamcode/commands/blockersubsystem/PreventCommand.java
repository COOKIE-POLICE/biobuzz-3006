package org.firstinspires.ftc.teamcode.commands.blockersubsystem;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.BlockerSubsystem;

public class PreventCommand extends CommandBase {
    private final BlockerSubsystem blockerSubsystem;

    public PreventCommand(BlockerSubsystem blockerSubsystem) {
        this.blockerSubsystem = blockerSubsystem;
        addRequirements(blockerSubsystem);
    }

    @Override
    public void execute() {
        blockerSubsystem.prevent();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
