package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ManualShoot;
import frc.robot.util.Loggable;
import frc.robot.util.Refreshable;

public class Shooter extends Subsystem implements Loggable, Refreshable {
    private static final int kTopMotorID = 11;
    private static final int kBottomMotorID = 21;

    private CANSparkMax topMotor;
    private CANSparkMax bottomMotor;

    private CANEncoder topEncoder;
    private CANEncoder bottomEncoder;

    private double topSpeed, bottomSpeed;

    private double speedChange;

    public Shooter() {
        topMotor = new CANSparkMax(kTopMotorID, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(kBottomMotorID, MotorType.kBrushless);

        topMotor.restoreFactoryDefaults();
        bottomMotor.restoreFactoryDefaults();

        topMotor.setInverted(false);
        bottomMotor.setInverted(true);

        speedChange = 0.01;

        initEncoders();
    }

	@Override
    public void refresh() {
        
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Shooter/TopMotor/Speed", topSpeed);
        SmartDashboard.putNumber("Shooter/BottomMotor/Speed", bottomSpeed);
        SmartDashboard.putNumber("Shooter/BottomMotor/SpeedChange", speedChange);
    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new ManualShoot());
    }

    public void setMotors(double topSpeed, double bottomSpeed) {
        topMotor.set(topSpeed);
        bottomMotor.set(bottomSpeed);
    }

    private void initEncoders() {
        topEncoder = topMotor.getEncoder();
        bottomEncoder = bottomMotor.getEncoder();
    }

    public double getBottomSpeed() {
		return bottomSpeed;
	}

	public void setBottomSpeed(double bottomSpeed) {
		this.bottomSpeed = bottomSpeed;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
    }
    
    public double getSpeedChange(){
        return speedChange;
    }

    public void setSpeedChange(double speedChange){
        this.speedChange = speedChange;
    }
}
