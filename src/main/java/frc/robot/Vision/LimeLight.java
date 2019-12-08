package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class LimeLight{

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = table.getEntry("tx"); //Horizontal
    NetworkTableEntry ty = table.getEntry("ty"); //Vertical
    NetworkTableEntry ta = table.getEntry("ta"); //Distance
    NetworkTableEntry ts = table.getEntry("ts"); //Skew

    //read values periodically
    double x    = tx.getDouble(0.0);
    double y    = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double skew = ts.getDouble(0.0);


    

}