package frc.robot.SwerveDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDController;

import frc.robot.SwerveDrive.Encoder;

public class WheelDrive {
	
private CANSparkMax angleMotor;
private CANSparkMax speedMotor;

private PIDController anglePID;

private Encoder azimuthEncoder;


	public WheelDrive (int angleMotor, int speedMotor, int analogIn) { //Idk if this is even close to correct...
	
		//create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		anglePID = new PIDController(0.5, 0.01, 0.01, this.azimuthEncoder.setUp(analogIn), this.angleMotor);

		anglePID.setOutputRange(-1, 1);
		anglePID.enable();
		anglePID.setContinuous();
	}


	public void drive (double speed, double angle) {
	
		speedMotor.set(speed * 0.25);

		double setpoint = angle * azimuthEncoder.toDegrees();

		anglePID.setSetpoint(setpoint);
	}
}
