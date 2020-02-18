
package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {

    //TODO Continue implementation of this command

    private double speed;
    private boolean isLeftSide;


    public ShootCommand(boolean isLeftSide) {
        this.isLeftSide = isLeftSide;
    }

    @Override
    protected void initialize() {

        super.initialize();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}