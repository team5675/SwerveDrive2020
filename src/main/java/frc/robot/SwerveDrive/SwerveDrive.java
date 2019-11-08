package frc.robot.SwerveDrive;



public class SwerveDrive {

	public double L = 30;//length of wheel axle distances
	public double W = 28;//width of wheel axle distances

	public final double ANGLE_OFFSET1 = 0; //in degrees
	public final double ANGLE_OFFSET2 = 0; //in degrees
	public final double ANGLE_OFFSET3 = 0; //in degrees
	public final double ANGLE_OFFSET4 = 0; //in degrees
	
	
	public void drive (double x1, double y1, double rotation, double theta) {
		double r = Math.sqrt((L * L) + (W * W));
		y1 *= -1; //if axis is funky
		
		
		double foward = y1 * Math.cos(theta) + x1 * Math.sin(theta); // field-centricishy??
		
		double strafe = -y1 * Math.sin(theta) + x1 * Math.cos(theta);
		
		
		double a = strafe - rotation * (L / r); //quick mafs for figuring speed and angles later
		
		double b = strafe + rotation * (L / r);
		
		double c = foward - rotation * (W / r);
		
		double d = foward + rotation * (W / r);
		
		
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
		double backRightAngle 	= (Math.atan2(a, d) * 180 / Math.PI + ANGLE_OFFSET1) + (180); 
		
		double backLeftAngle 	= (Math.atan2(a, c) * 180 / Math.PI + ANGLE_OFFSET2) + (180);
		
		double frontRightAngle	= (Math.atan2(b, d) * 180 / Math.PI + ANGLE_OFFSET3) + (180);
		
		double frontLeftAngle	= (Math.atan2(b, c) * 180 / Math.PI + ANGLE_OFFSET4) + (180);

		//normalize wheel speeds
        double max = backRightSpeed;

        if (backLeftSpeed > max) { max = backLeftSpeed;}
        if (frontLeftSpeed > max) { max = frontLeftSpeed;}
        if (frontRightSpeed > max) { max = frontRightSpeed;}

        if (max > 1) {

            backRightSpeed /= max;
            backLeftSpeed /= max;
            frontLeftSpeed /= max;
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