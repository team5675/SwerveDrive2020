package frc.robot.SwerveDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;

//import frc.robot.SwerveDrive.Encoder;

public class WheelDrive {
	
private CANSparkMax angleMotor;
private CANSparkMax speedMotor;

private PIDController anglePID;
private AnalogInput azimuthEncoder;

//private Encoder azimuthEncoder;


	/**
	 @param angleMotor The CAN ID of the azimuth controller
	 @param speedMotor The CAN ID of the speed controller
	 @param analogIn   The Analog ID of the azimuth encoder 
 	*/
	public WheelDrive (int angleMotor, int speedMotor, int analogIn) {
	
		//create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		
		this.azimuthEncoder = new AnalogInput(analogIn);

		
		anglePID = new PIDController(0.68814, 0.0004, 0.000, azimuthEncoder, this.angleMotor);

		anglePID.setOutputRange(-1, 1);
		anglePID.setInputRange(0, 5);
		anglePID.enable();
		anglePID.setContinuous();
	}


	public void drive (double speed, double angle) {
	
		speedMotor.set(speed * .75);

		//System.out.println(azimuthEncoder.getVoltage());

		double setpoint = angle;

		anglePID.setSetpoint(setpoint);
	}
}
