/* Official Test Swerve Drive Code for 2019*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.kauailabs.navx.frc.*;

import frc.robot.SwerveDrive.SwerveDrive;
import frc.robot.SwerveDrive.WheelDrive;
import frc.robot.SwerveDrive.Encoder;

public class Robot extends TimedRobot {

	AHRS ahrs;

	public  Encoder 	encoder 	= new Encoder();
	
	public  GenericHID 	Controller  = new XboxController (0);
	
	private WheelDrive 	backRight 	= new WheelDrive(1, 2, 1);//actual  port ID's of angle and speed motors
	private WheelDrive 	backLeft 	= new WheelDrive(3, 4, 0);//(In that order)
	private WheelDrive 	frontRight	= new WheelDrive(5, 6, 2);
	private WheelDrive 	frontLeft 	= new WheelDrive(7, 8, 3);
	
	private SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft);


	
	@Override
	public void robotInit() {

		
		try {
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
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

		Scheduler.getInstance().run();
	}

	
	@Override
	public void teleopInit() {
		
	}
	
	
	@Override
	public void teleopPeriodic() {
		
		
		swerveDrive.drive (Controller.getRawAxis(1), Controller.getRawAxis(0), Controller.getRawAxis(4), ahrs.pidGet()); //get them inputs

		//System.out.println(Math.toRadians(ahrs.getYaw()));
		//encoder.displayValues();
	}

	
	@Override
	public void testPeriodic() {
	}
}
