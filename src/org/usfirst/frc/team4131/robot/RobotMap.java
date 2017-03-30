package org.usfirst.frc.team4131.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int PCM_ID = 62;
	//Input devices
	public static final int LEFT_JOYSTICK = 0;
	public static final int RIGHT_JOYSTICK = 1;
	public static final int AUXILLIARY_CONTROLLER = 2;
	
	//Motors
	public static final int DRIVE_LEFT[] = {1, 2, 3};
	public static final int DRIVE_RIGHT[] = {4, 5, 6};
	public static final int CLIMBER_MOTOR1 = 13;
	public static final int CLIMBER_MOTOR2 = 14;
	
	//Solenoid slots
	public static final int SHIFTER1 = 0;
	public static final int SHIFTER2 = 1;
	public static final int GEAR_RAMP1 = 2;
	public static final int GEAR_RAMP2 = 3;
	public static final int GEAR_POCKET1 = 4;
	public static final int GEAR_POCKET2 = 5;
	public static final int GEAR_CLAW1 = 6;
	public static final int GEAR_CLAW2 = 7;
	
	//Constants
	public static final int DRIVE_ENCODER_TICKS = 20;
	public static final double HIGH_GEAR_RATIO = 0.224, DRIVE_WHEEL_RADIUS = 2;
	public static final double DRIVE_WHEEL_CIRCUMFERENCE = 2 * Math.PI * DRIVE_WHEEL_RADIUS;
	public static final double MAGIC_DRIVE_CONSTANT = 1d/240 * 59/48 * 51/48;//Arbitrary but it works
	public static final double DRIVE_CONVERSION_FACTOR = HIGH_GEAR_RATIO * DRIVE_WHEEL_CIRCUMFERENCE * MAGIC_DRIVE_CONSTANT;
	public static final boolean DRIVE_LEFT_INVERTED = true, DRIVE_RIGHT_INVERTED = false;
}
