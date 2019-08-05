package frc.robot.SwerveDrive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class WheelDrive {
	
private CANSparkMax angleMotor;

private CANSparkMax speedMotor;

private CANPIDController m_pidController;

private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;


public WheelDrive (int angleMotor, int speedMotor) { //Idk if this is even close to correct...

    CANEncoder m_encoder;
	
	this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
	
	this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

	//create the PID object
	m_pidController = this.angleMotor.getPIDController();

	//encoder on the NEO 
	//TODO: switch out for different encoder, maybe a mag encoder
    m_encoder = this.angleMotor.getEncoder();

	//PID's
	kP = 0.45; 
    kI = 1e-3;
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
}


public void drive (double speed, double angle) {
	
	speedMotor.set(speed);

	m_pidController.setReference(angle, ControlType.kPosition);          //and so it starts to chug along (not slowly tho) ((hopefully))
}
}
