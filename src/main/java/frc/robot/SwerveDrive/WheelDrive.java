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

public double encoderValue;

//private Encoder azimuthEncoder;


	/**
	 @param angleMotor The CAN ID of the azimuth controller
	 @param speedMotor The CAN ID of the speed controller
	 @param analogIn   The Analog ID of the azimuth encoder 
	 @param P		   Proportional error for the PID loop
	 @param I		   Integrated error for the PID loop
	 @param D		   Derivative error for the PID loop
 	*/
	public WheelDrive (int angleMotor, int speedMotor, int analogIn, double P, double I, double D) {
	
		//create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		
		this.azimuthEncoder = new AnalogInput(analogIn);

		
		anglePID = new PIDController(P, I, D, azimuthEncoder, this.angleMotor);

		anglePID.setOutputRange(-1, 1);
		anglePID.setInputRange(0, 5);
		anglePID.enable();
		anglePID.setContinuous();

		encoderValue = azimuthEncoder.getVoltage();
	}


	public void drive (double speed, double angle, boolean deadband) {
	
		if (deadband) {

			speedMotor.set(0);
			angleMotor.set(0);
		}

		else{

			speedMotor.set(speed);
			anglePID.setSetpoint(angle);
		}
		//System.out.println("angle: " + angle + "speed: " + speed);
	}
}