package frc.robot.SwerveDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.NetworkTables.Network;

public class WheelDrive {

<<<<<<< Updated upstream
private PIDController anglePID;

private int accum;

public AnalogInput[] encoderArray = {new AnalogInput(0), new AnalogInput(1), new AnalogInput(2), new AnalogInput(3)};
private double[] PValues = {0.68814, 0, 0, 0};
private double[] IValues = {0.0004, 0, 0, 0};
private double[] DValues = {0, 0, 0, 0};

=======
	private CANSparkMax angleMotor;
	private CANSparkMax speedMotor;

	private PIDController anglePID;
	private AnalogInput azimuthEncoder;

	private Network networkTable;

	private double encoderValue;
>>>>>>> Stashed changes

	// private Encoder azimuthEncoder;

	/**
<<<<<<< Updated upstream
	 @param angleMotor 	The CAN ID of the azimuth controller
	 @param speedMotor 	The CAN ID of the speed controller
	 @param wheelNumber	The order of the modules (backRight(0) - frontLeft(3))  
 	*/
	public WheelDrive (int angleMotor, int speedMotor, int wheelNumber) {
	
		accum = wheelNumber;

		//create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		anglePID = new PIDController(PValues[accum], IValues[accum], DValues[accum], encoderArray[accum], this.angleMotor);
=======
	 * @param angleMotor The CAN ID of the azimuth controller
	 * @param speedMotor The CAN ID of the speed controller
	 * @param analogIn   The Analog ID of the azimuth encoder
	 */
	public WheelDrive(int angleMotor, int speedMotor, int analogIn) {

		// create our "wheels"
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

		this.azimuthEncoder = new AnalogInput(analogIn);

	
		anglePID = new PIDController(0.68814, 0.0004, 0.000, azimuthEncoder, this.angleMotor);
>>>>>>> Stashed changes

		anglePID.setOutputRange(-1, 1);
		anglePID.setInputRange(0, 5);
		anglePID.enable();
		anglePID.setContinuous();

		
	}


	public void drive (double speed, double angle, int ID) {
	
		speedMotor.set(speed * .75);

<<<<<<< Updated upstream
		//System.out.println(azimuthEncoder.getVoltage());

		if (angle > 5) {angle = angle - 5;}
		if (angle < 0) {angle = 5 - angle;}

=======
>>>>>>> Stashed changes
		double setpoint = angle;

		anglePID.setSetpoint(setpoint);

		encoderValue = azimuthEncoder.getVoltage();
		networkTable.putDouble(encoderValue, ID);
	}
}
