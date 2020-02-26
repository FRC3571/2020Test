package frc.robot;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.ChangeShooterSpeed;
import frc.robot.ChangeShooterSpeedChange;
import frc.robot.ManualShoot;
import frc.robot.Shooter;
import frc.robot.util.XboxController;

public class Robot extends TimedRobot {

    public static final int kController = 1;

    private Shooter shooter;
    private Logger logger;
    private XboxController subsystemController;
    private PowerDistributionPanel pdp;
    private static Robot exposedInstance;

    @Override
    public void robotInit() {
        exposedInstance = this;

        pdp = new PowerDistributionPanel();
        subsystemController = new XboxController(kController);

        shooter = new Shooter();

        logger = Logger.getLogger(getClass().getName());

        initController();
    }

    @Override
    public void robotPeriodic() {
        debug();
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        shooter.refresh();
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
    }

    private void debug() {
        shooter.log();
    }

    private void initController() {
        //Testing Shooter
        subsystemController.lb.whenPressed(new ChangeShooterSpeed(1,false));
        subsystemController.lt.whenPressed(new ChangeShooterSpeed(1,true));

        subsystemController.rb.whenPressed(new ChangeShooterSpeed(2,false));
        subsystemController.rt.whenPressed(new ChangeShooterSpeed(2,true));

        subsystemController.a.whenPressed(new ChangeShooterSpeedChange(10));
        subsystemController.b.whenPressed(new ChangeShooterSpeedChange(1/10));
        subsystemController.x.whenPressed(new ChangeShooterSpeedChange(5));
        subsystemController.y.whenPressed(new ChangeShooterSpeedChange(1/5));

        subsystemController.rightStick.toggleWhenPressed(new ManualShoot());
    }

    public void log(final Level logLevel, final String message) {
        logger.log(logLevel, message);
    }

    public static Robot getInstance() {
        if (exposedInstance == null) {
            throw new IllegalStateException("#robotInit must finish its invocation!");
        }
        return exposedInstance;
    }

    public XboxController getSubsystemController() {
        return subsystemController;
    }
    public Shooter getShooter() {
        return shooter;
    }
}