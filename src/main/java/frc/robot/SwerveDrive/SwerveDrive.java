package frc.robot.SwerveDrive;



public class SwerveDrive {

	public double L = 30;//length of wheel axle distances
	public double W = 28;//width of wheel axle distances

	public final double ANGLE_OFFSET1 = -92; //in degrees
	public final double ANGLE_OFFSET2 = 0; //in degrees
	public final double ANGLE_OFFSET3 = 0; //in degrees
	public final double ANGLE_OFFSET4 = 0; //in degrees
	
	/**
	 * 
	 * @param x1 Forward input
	 * @param y1 Strafe input
	 * @param rotation Azimuth input
	 * @param theta Gyro Yaw input
	 */
	public void drive (double x1, double y1, double rotation, double theta) {

		double r = Math.hypot(L, W);
	
		
		double temp = y1 * Math.cos(Math.toRadians(theta)) + x1 * Math.sin(Math.toRadians(theta)); //allows for field centric control
		
		double strafe = -y1 * Math.sin(Math.toRadians(theta)) + x1 * Math.cos(Math.toRadians(theta));

		double forward = temp;
		
		
		double a = strafe - rotation * (L / r); //placeholder vector values
		
		double b = strafe + rotation * (L / r);
		
		double c = forward - rotation * (W / r);
		
		double d = forward + rotation * (W / r);
		
		
		double backRightSpeed = 0; //calculating speed
				
		double backLeftSpeed = 0;
				
		double frontRightSpeed = 0;
				
		double frontLeftSpeed = 0;
		
		
		//Output is 0 to 1
		backRightSpeed = Math.hypot(a, d);
			
		backLeftSpeed = Math.hypot(a, c);;
					
		frontRightSpeed = Math.hypot(b, d);
					
		frontLeftSpeed = Math.hypot(b, c);
		

		//Output is 0 to 360 degrees
		double backRightAngle 	= ((Math.atan2(a, d) + Math.PI) * (180/Math.PI)) + ANGLE_OFFSET1; 
		
		double backLeftAngle 	= ((Math.atan2(a, c) + Math.PI) * (180/Math.PI)) + ANGLE_OFFSET2;
		
		double frontRightAngle	= ((Math.atan2(b, d) + Math.PI) * (180/Math.PI)) + ANGLE_OFFSET3;
		
		double frontLeftAngle	= ((Math.atan2(b, c) + Math.PI) * (180/Math.PI)) + ANGLE_OFFSET4;

		//normalize wheel speeds
        double max = backRightSpeed;

        if (backLeftSpeed > max)   { max = backLeftSpeed;}
        if (frontLeftSpeed > max)  { max = frontLeftSpeed;}
        if (frontRightSpeed > max) { max = frontRightSpeed;}

        if (max > 1) {

            backRightSpeed  /= max;
            backLeftSpeed   /= max;
            frontLeftSpeed  /= max;
            frontRightSpeed /= max;
        }
		
		
		backRight.drive(backRightSpeed, backRightAngle); //just using a class to organize modules together
		
		backLeft.drive(backLeftSpeed, backLeftAngle);
		
		frontRight.drive(frontRightSpeed, frontRightAngle);
		
		frontLeft.drive(frontLeftSpeed, frontLeftAngle);
	
	}
		
		private WheelDrive backRight;
		
		private WheelDrive backLeft;
		
		private WheelDrive frontRight;
		
		private WheelDrive frontLeft;
		
		
		public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft ) {
			
			//passing into the full full swerve class
			this.backRight = backRight;
			
			this.backLeft = backLeft;
			
			this.frontRight = frontRight;
			
			this.frontLeft = frontLeft;
			
		}
}