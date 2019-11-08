package frc.robot.SwerveDrive;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoder {

    AnalogInput azimuthEncoder;

    /**
     * @param port the analog port on the rio
     * @return an analog encoder
     */
    public AnalogInput setUp(int port) {

        azimuthEncoder = new AnalogInput(port);

        return azimuthEncoder;
    }

    /**
     * @return encoder value from 0 to 360 degrees
     */
    public double toDegrees() {

        // output is 0 to 360;
        double degrees = azimuthEncoder.getVoltage() * 72;

        return degrees;
    }

    public void displayValues() {

        SmartDashboard.putNumber("Encoder Position", azimuthEncoder.getVoltage() * 72);
    }
}