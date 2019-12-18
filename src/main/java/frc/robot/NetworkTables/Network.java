package frc.robot.NetworkTables;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Network {

    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("robot");

    NetworkTableEntry backRight  = table.getEntry("backRight");
    NetworkTableEntry backLeft   = table.getEntry("backLeft");
    NetworkTableEntry frontRight = table.getEntry("frontRight");
    NetworkTableEntry frontLeft  = table.getEntry("frontLeft");
    
    double backRightE  = backRight.getDouble(0.0);
    double backLeftE   = backLeft.getDouble(0.0);
    double frontRightE = frontRight.getDouble(0.0);
    double frontLeftE  = frontLeft.getDouble(0.0);

    public void putDouble(double value, int ID) {

        switch (ID){

            case 0:
                backRight.setDouble(value);

            case 1:
                backLeft.setDouble(value);

            case 2:
                frontRight.setDouble(value);

            case 3:
                frontLeft.setDouble(value);
                break;
        }
    }
       
}