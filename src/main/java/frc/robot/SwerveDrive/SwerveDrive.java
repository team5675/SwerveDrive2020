package frc.robot.SwerveDrive;



public class SwerveDrive {

	public double L = 30;//length of wheel axle distances
	
	public double W = 28;//width of wheel axle distances
	
	
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
		
			
		backRightSpeed = Math.hypot(a, d);//because going forward is important
			
		backLeftSpeed = Math.hypot(a, c);;
					
		frontRightSpeed = Math.hypot(b, d);
					
		frontLeftSpeed = Math.hypot(b, c);
		
		
		double backRightAngle = Math.atan2(a, d) * 0.5 / Math.PI; //yeah! Polar bears and stuff
		
		double backLeftAngle = Math.atan2(a, c) * 0.5 / Math.PI;
		
		double frontRightAngle = Math.atan2(b, d) * 0.5/ Math.PI;
		
		double frontLeftAngle = Math.atan2(b, c) * 0.5/ Math.PI;
		
		
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

	
	

