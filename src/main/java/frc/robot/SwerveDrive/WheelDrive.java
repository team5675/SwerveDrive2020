package frc.robot.SwerveDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class WheelDrive {
	
private CANSparkMax angleMotor;

private CANSparkMax speedMotor;

private CANPIDController m_pidController;

private CANEncoder m_encoder;

private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;


public WheelDrive (int angleMotor, int speedMotor, int encoder) { //Idk if this is even close to correct...
	
	this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
	
	this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

	//create the PID object
	m_pidController = this.angleMotor.getPIDController();

	//encoder on the NEO 
	//TODO: switch out for different encoder, maybe a mag encoder
	m_encoder = this.angleMotor.getEncoder();

	//PID's
	kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
	kMinOutput = -1;
	
	//set PID's
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
	m_pidController.setOutputRange(kMinOutput, kMaxOutput);
	
	//maybe to help with tunning?
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
}


public void drive (double speed, double angle) {

	// read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
	if((p != kP)) { m_pidController.setP(p); kP = p; }
	
	if((i != kI)) { m_pidController.setI(i); kI = i; }
	
	if((d != kD)) { m_pidController.setD(d); kD = d; }
	
	if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
	
	if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
	
    if((max != kMaxOutput) || (min != kMinOutput)) { 

	  m_pidController.setOutputRange(min, max); 
	  
      kMinOutput = min; kMaxOutput = max; 
    }
	
	speedMotor.set(speed);

	m_pidController.setReference(angle, ControlType.kPosition);          //and so it starts to chug along (not slowly tho) ((hopefully))

	SmartDashboard.putNumber("SetPoint", angle);
	SmartDashboard.putNumber("EncoderPosition", m_encoder.getPosition());
}
}
