package org.usfirst.frc.team4131.robot.subsystems;

import org.usfirst.frc.team4131.robot.RobotMap;
import org.usfirst.frc.team4131.robot.commands.Move;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * ========== Test Procedure ==========
 * Ran on Robot in a Box
 * We will call {@link #move(double, double)} in teleopPeriodic and expect both motors to move. The same number to both should make them rotate in opposite directions.
 * We will report {@link #getAngle()} in teleopPeriodic and expect it to show the robot's angle of rotation about the axis perpendicular to the floor.
 * We will report {@link #getDistance()} in teleopPeriodic while moving the drive motors and expect the value to change proportional to the motor value.
 * We will call {@link #shiftUp()} and {@link #shiftDown()} and expect them to set the solenoids to forward or reverse.
 * Tests passed
 * ====================================
 * @author Calvin, Ian
 * @since 2/17/2017
 */
public class DriveBase extends Subsystem {
	private CANTalon leftMotor, rightMotor;
	private DoubleSolenoid leftShifter, rightShifter;
	private Encoder leftEncoder, rightEncoder;
	private AHRS imu;
	public DriveBase(){
		//Create left motor
		leftMotor = new CANTalon(RobotMap.DRIVE_LEFT[0]);
		leftMotor.setInverted(RobotMap.DRIVE_LEFT_INVERTED);
		leftMotor.reverseOutput(RobotMap.DRIVE_LEFT_INVERTED);
		leftMotor.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		
		//Create right motor
		rightMotor = new CANTalon(RobotMap.DRIVE_RIGHT[0]);
		rightMotor.setInverted(RobotMap.DRIVE_RIGHT_INVERTED);
		rightMotor.reverseOutput(RobotMap.DRIVE_RIGHT_INVERTED);
		rightMotor.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		
		//Create other motors (as followers)
		for(int i=1; i<3; ++i){
			CANTalon left = new CANTalon(RobotMap.DRIVE_LEFT[i]);
			left.changeControlMode(TalonControlMode.Follower);
			left.set(RobotMap.DRIVE_LEFT[0]);
			
			CANTalon right = new CANTalon(RobotMap.DRIVE_RIGHT[i]);
			right.changeControlMode(TalonControlMode.Follower);
			right.set(RobotMap.DRIVE_RIGHT[0]);
		}
		//Create shifters
		leftShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.LEFT_SHIFTER1, RobotMap.LEFT_SHIFTER2);
		rightShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.RIGHT_SHIFTER1, RobotMap.RIGHT_SHIFTER2);
		
		//Create left encoder
		leftEncoder = new Encoder(RobotMap.ENCODER_LEFT1, RobotMap.ENCODER_LEFT2);
		leftEncoder.setDistancePerPulse(RobotMap.DRIVE_INCHES_PER_PULSE);
		leftEncoder.setReverseDirection(RobotMap.DRIVE_LEFT_INVERTED);
		
		//Create right encoder
		rightEncoder = new Encoder(RobotMap.ENCODER_RIGHT1, RobotMap.ENCODER_RIGHT2);
		rightEncoder.setDistancePerPulse(RobotMap.DRIVE_INCHES_PER_PULSE);
		
		imu = new AHRS(SPI.Port.kMXP);
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new Move());
	}
	public void move(double left, double right) {
		leftMotor.set(left);
		rightMotor.set(right);
	}
	public void shiftUp(){
		leftShifter.set(DoubleSolenoid.Value.kForward);
		rightShifter.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftDown(){
		leftShifter.set(DoubleSolenoid.Value.kReverse);
		rightShifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void resetDistance(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public double getDistance(){
		return leftEncoder.getDistance();
	}
	public PIDSource getDistanceSource(){
		return leftEncoder;
	}
	public void resetGyro(){
		imu.reset();
	}
	public double getAngle(){
		return imu.getYaw();
	}
	public PIDSource getAngleSource(){
		return imu;
	}
}
