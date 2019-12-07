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

private int accum;

public AnalogInput[] encoderArray = {new AnalogInput(0), new AnalogInput(1), new AnalogInput(2), new AnalogInput(3)};
private double[] PValues = {0.68814, 0, 0, 0};
private double[] IValues = {0.0004, 0, 0, 0};
private double[] DValues = {0, 0, 0, 0};



	/**
	 @param angleMotor The CAN ID of the azimuth controller
	 @param speedMotor The CAN ID of the speed controller
	 @param analogIn   The Analog ID of the azimuth encoder 
 	*/
	public WheelDrive (int angleMotor, int speedMotor, int analogIn) {
	
		accum = analogIn;

		//create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		anglePID = new PIDController(PValues[accum], IValues[accum], DValues[accum], encoderArray[accum], this.angleMotor);

		anglePID.setOutputRange(-1, 1);
		anglePID.setInputRange(0, 5);
		anglePID.enable();
		anglePID.setContinuous();
	}


	public void drive (double speed, double angle) {
	
		speedMotor.set(speed * .75);

		//System.out.println(azimuthEncoder.getVoltage());

		if (angle > 5) {angle = angle - 5;}
		if (angle < 0) {angle = 5 - angle;}

		double setpoint = angle;

		anglePID.setSetpoint(setpoint);
	}
}
