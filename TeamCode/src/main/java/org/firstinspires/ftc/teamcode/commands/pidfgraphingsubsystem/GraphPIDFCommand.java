package org.firstinspires.ftc.teamcode.commands.pidfgraphingsubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.PIDFGraphingSubsystem;

public class GraphPIDFCommand extends CommandBase {
    private final PIDFGraphingSubsystem pidfGraphingSubsystem;

    public GraphPIDFCommand(PIDFGraphingSubsystem pidfGraphingSubsystem) {
        this.pidfGraphingSubsystem = pidfGraphingSubsystem;
    }

    @Override
    public void execute() {
        pidfGraphingSubsystem.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
