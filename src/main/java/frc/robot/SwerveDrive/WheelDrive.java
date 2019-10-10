package frc.robot.SwerveDrive;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANAnalog;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;


public class WheelDrive {
	
private CANSparkMax angleMotor;

private CANSparkMax speedMotor;

private CANPIDController m_pidController;

private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

private CANAnalog a_Encoder;

private PIDController anglePID;

private AnalogInput encoder;


public WheelDrive (int angleMotor, int speedMotor, int analogIn) { //Idk if this is even close to correct...

	//CANEncoder m_encoder;
	
	//create our "wheels"
	this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
	this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

	encoder = new AnalogInput(analogIn);

	anglePID = new PIDController(0.001, 1, 1, encoder, this.angleMotor);

	/*
	//grab the encoder values off of each MAX
	a_Encoder = this.angleMotor.getAnalog(CANAnalog.AnalogMode.kAbsolute);

	//create the PID object
	m_pidController = this.angleMotor.getPIDController();

	//encoder on the NEO
	//m_encoder = this.angleMotor.getEncoder();
	
	//set our PID loop to take the analog encoder as feedback
	m_pidController.setFeedbackDevice(a_Encoder);

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
	*/
}


public void drive (double speed, double angle) {
	
	speedMotor.set(speed);

	anglePID.enable();
	anglePID.setSetpoint(angle);

	//m_pidController.setReference(angle, ControlType.kPosition);          //and so it starts to chug along (not slowly tho) ((hopefully))
}
}
