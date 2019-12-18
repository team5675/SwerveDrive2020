package frc.robot.SwerveDrive;



public class SwerveDrive {

	public double L = 25;//length of wheel axle distances
	public double W = 25;//width of wheel axle distances

	//backright backleft frontright frontleft
	public final double ANGLE_OFFSET1 = 1.199; //from 0 to 5
	public final double ANGLE_OFFSET2 = 0.635;
	public final double ANGLE_OFFSET3 = 4.387;
	public final double ANGLE_OFFSET4 = 3.868;
	
	private double x1N;
	private double y1N;
	private double r1N;
	/**
	 * 
	 * @param x1 Forward input
	 * @param y1 Strafe input
	 * @param rotation Azimuth input
	 * @param theta Gyro Yaw input
	 */
	public void drive (double x1, double y1, double rotation, double theta) {

		double r = Math.hypot(L, W);

		if (x1 < 0.1) {x1 = x1N;}
		else 		  {x1N = x1;}

		if (y1 < 0.1) {y1 = y1N;}
		else 		  {y1N = y1;}

		if (rotation < 0.1) {rotation = r1N;}
		else 		  	    {r1N = rotation;}
	
		
		double temp = y1 * Math.cos(Math.toRadians(theta)) + x1 * Math.sin(Math.toRadians(theta)); //allows for field centric control
		
		double strafe = -y1 * Math.sin(Math.toRadians(theta)) + x1 * Math.cos(Math.toRadians(theta));

		double forward = temp;
		
		
		double a = strafe - rotation  * (L / r); //placeholder vector values
		
		double b = strafe + rotation  * (L / r);
		
		double c = forward - rotation * (W / r);
		
		double d = forward + rotation * (W / r);
		
		
		double backRightSpeed = 0; //calculating speed
				
		double backLeftSpeed = 0;
				
		double frontRightSpeed = 0;
				
		double frontLeftSpeed = 0;
		
		
		//Output is 0 to 1
		backRightSpeed = Math.hypot(a, d);
			
		backLeftSpeed = Math.hypot(a, c);
					
		frontRightSpeed = Math.hypot(b, d);
					
		frontLeftSpeed = Math.hypot(b, c);
		

		//									-1 to 1		   -2.5 to 2.5   0 to 5 
		double backRightAngle 	= (((Math.atan2(a, c) / Math.PI) * 2.5) + 2.5) + ANGLE_OFFSET1; 
		
		double backLeftAngle 	= (((Math.atan2(a, d) / Math.PI) * 2.5) + 2.5) + ANGLE_OFFSET2;

		double frontRightAngle	= (((Math.atan2(b, c) / Math.PI) * 2.5) + 2.5) + ANGLE_OFFSET3;

		double frontLeftAngle	= (((Math.atan2(b, d) / Math.PI) * 2.5) + 2.5) + ANGLE_OFFSET4;

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
		
		
<<<<<<< Updated upstream
		backRight.drive(backRightSpeed, backRightAngle);
=======
		backRight.drive(backRightSpeed, backRightAngle, 0); //just using a class to organize modules together
>>>>>>> Stashed changes
		
		backLeft.drive(backLeftSpeed, backLeftAngle, 1);
		
		frontRight.drive(frontRightSpeed, frontRightAngle, 2);
		
		frontLeft.drive(frontLeftSpeed, frontLeftAngle, 3);
	
	}
	
	
	public void defense () {

		backRight.drive(0, 1.7875);
		
		backLeft.drive(0, 0.7125);
		
		frontRight.drive(0, -1.7875);
		
		frontLeft.drive(0, -0.7125);
	}

	public void autoDrive(double strafeError, double ForwardError, double azimuthError) {

		//place in logic code for vision to inject angle and speed values to modules
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