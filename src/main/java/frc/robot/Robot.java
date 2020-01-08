/* Official Test Swerve Drive Code for 2019*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.networktables.*;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.SwerveDrive.SwerveDrive;
import frc.robot.SwerveDrive.WheelDrive;

public class Robot extends TimedRobot {

	Timer timer = new Timer();

	AHRS navX;

	public XboxController Controller = new XboxController(0);
	
	private WheelDrive backRight = new WheelDrive(3, 4, 0, 0.78715, 0.0004, 0.0);// actual port ID's of angle and speed motors																			// motors
	private WheelDrive backLeft = new WheelDrive(1, 2, 1, 0.79715, 0.0006, 0.0);// (In that order)
	private WheelDrive frontRight = new WheelDrive(5, 6, 2, 0.68715, 0.0005, 0.0);
	private WheelDrive frontLeft = new WheelDrive(7, 8, 3, 0.88715, 0.0003, 0.0);

	private SwerveDrive swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);

	NetworkTableEntry encoderValue;

	@Override
	public void robotInit() {

		try {
			navX = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}

		timer.start();

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

		while (timer.get() < 3) {

			swerveDrive.drive(-1, 0, 0, navX.getAngle() - 90, false);
		}
		
		swerveDrive.drive(0, 0, 0, navX.getAngle() - 90, false);
	}

	
	@Override
	public void teleopInit() {

		
	}
	
	
	@Override
	public void teleopPeriodic() {
		 
		if (Controller.getBButton()) {

			swerveDrive.zeroEncoders();			
		}
		
		swerveDrive.drive(Controller.getRawAxis(1), Controller.getRawAxis(0), Controller.getRawAxis(4), navX.getAngle() - 90, Controller.getStickButton(Hand.kLeft));// navX.getAngle() - 90); //get them inputs

		if (Controller.getAButton()) {

			navX.zeroYaw();
			System.out.println("Zeroed.");
		}
	}

	
	@Override
	public void testPeriodic() {

	}
}