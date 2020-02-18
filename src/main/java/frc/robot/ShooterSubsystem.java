import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

    @Override
    protected void initDefaultCommand() {
        
    }

    private static int RIGHT_MOTOR_ID = 3;
    private static int LEFT_MOTOR_ID = 4;

    private CANSparkMax firstMotor;
    private CANSparkMax secondMotor;

    private Robot robot;

    public ShooterSubsystem(Robot robot) {

        this.robot = robot;

        firstMotor = new CANSparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
        secondMotor = new CANSparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);

        firstMotor.setInverted(false);
        secondMotor.setInverted(true);
    }

    public void refresh() {
        if(Math.abs(robot.getController().Triggers.Left) > 0) {
            firstMotor.set(robot.getController().Triggers.Left);
            secondMotor.set(robot.getController().Triggers.Left);
        }
        
        else {
            firstMotor.set(-robot.getController().Triggers.Right);
            secondMotor.set(-robot.getController().Triggers.Right);
        }
        
    }
}