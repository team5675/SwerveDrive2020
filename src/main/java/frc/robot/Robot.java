/* Official Test Swerve Drive Code for 2019*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.networktables.*;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.SwerveDrive.SwerveDrive;
import frc.robot.SwerveDrive.WheelDrive;

public class Robot extends TimedRobot {

	Timer timer = new Timer();

	AHRS navX;
	/*
	public  AnalogInput backRightEncoder 	= new AnalogInput(0);
	public  AnalogInput backLeftEncoder 	= new AnalogInput(1);
	public  AnalogInput frontRightEncoder 	= new AnalogInput(2);
	public  AnalogInput frontLeftEncoder 	= new AnalogInput(3);
	*/
	public  XboxController Controller  = new XboxController (0);

	//frontleft --> frontright
	//frontright --> backright
	//backright --> backleft
	//backleft --> frontleft
	
	private WheelDrive 	backRight 	= new WheelDrive(3, 4, 0);//actual  port ID's of angle and speed motors
	private WheelDrive 	backLeft 	= new WheelDrive(1, 2, 1);//(In that order)
	private WheelDrive 	frontRight	= new WheelDrive(5, 6, 2);
	private WheelDrive 	frontLeft 	= new WheelDrive(7, 8, 3);
	
	private SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft);
	
	NetworkTableEntry encoderValue;
	
	@Override
	public void robotInit() {

		try {
			navX = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}

		
	}


	@Override
	public void disabledInit() {
		
	}

	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {

		
	}

	
	@Override
	public void autonomousPeriodic() {

	}

	
	@Override
	public void teleopInit() {
		
	}
	
	
	@Override
	public void teleopPeriodic() {
		 
		if (Controller.getBButton()) {

			swerveDrive.zeroEncoders();
		}
		
		swerveDrive.drive (Controller.getRawAxis(1), Controller.getRawAxis(0), Controller.getRawAxis(4), navX.getAngle() - 90); //get them inputs

		if (Controller.getAButton()) {

			navX.zeroYaw();
			System.out.println("Zeroed.");
		}

		//System.out.println("Controller in: " + Controller.getRawAxis(1));
		/*
		System.out.print("Back Right Encoder: " + backRightEncoder.getVoltage());
		System.out.print("Back Left Encoder: " + backLeftEncoder.getVoltage());
		System.out.print("Front Right Encoder: " + frontRightEncoder.getVoltage());
		System.out.println("Front Left Encoder: " + frontLeftEncoder.getVoltage());
		*/
	}

	
	@Override
	public void testPeriodic() {

	}
}