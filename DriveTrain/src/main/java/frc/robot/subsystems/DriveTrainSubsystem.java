// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends SubsystemBase {
  private static final WPI_TalonFX leftMotorBack = RobotMap.leftBackDriveMotor;
  private static final WPI_TalonFX rightMotorBack = RobotMap.rightBackDriveMotor;
  private static final WPI_TalonFX leftMotorFront = RobotMap.leftFrontDriveMotor;
  private static final WPI_TalonFX rightMotorFront = RobotMap.rightFrontDriveMotor;

    final double IN_TO_M = .0254;

    final int MOTOR_ENCODER_CODES_PER_REV = 2048;
    final double DIAMETER_INCHES = 5.0;

    final double WHEEL_DIAMETER = DIAMETER_INCHES * IN_TO_M;
    final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

    final double GEAR_RATIO = 12.75;

    final double TICKS_PER_METER = (MOTOR_ENCODER_CODES_PER_REV * GEAR_RATIO) / WHEEL_CIRCUMFERENCE;
    final double METERS_PER_TICK = 1 / TICKS_PER_METER;

    private static final int TIMEOUT_MS = 10;
    
  

    
  /** Creates a new DriveSubsystem. */
  public DriveTrainSubsystem() {
  
    leftMotorFront.set(ControlMode.Follower, leftMotorBack.getDeviceID());
    rightMotorFront.set(ControlMode.Follower, rightMotorBack.getDeviceID());

    leftMotorBack.setNeutralMode(NeutralMode.Coast);
    rightMotorBack.setNeutralMode(NeutralMode.Coast);
    leftMotorFront.setNeutralMode(NeutralMode.Coast);
    rightMotorFront.setNeutralMode(NeutralMode.Coast);

    leftMotorBack.setInverted(false); //enable if motors are uneven
    rightMotorBack.setInverted(true);
    leftMotorFront.setInverted(false); //enable if motors are uneven
    rightMotorFront.setInverted(true);

    leftMotorFront.configNominalOutputForward(0,TIMEOUT_MS);
    leftMotorFront.configNominalOutputReverse(0,TIMEOUT_MS);
    leftMotorFront.configPeakOutputForward(1,TIMEOUT_MS);
    leftMotorFront.configPeakOutputReverse(-1,TIMEOUT_MS);

    rightMotorFront.configNominalOutputForward(0,TIMEOUT_MS);
    rightMotorFront.configNominalOutputReverse(0,TIMEOUT_MS);
    rightMotorFront.configPeakOutputForward(1,TIMEOUT_MS);
    rightMotorFront.configPeakOutputReverse(-1,TIMEOUT_MS);

    leftMotorFront.configNeutralDeadband(0.001, TIMEOUT_MS);
    leftMotorBack.configNeutralDeadband(0.001, TIMEOUT_MS);
    rightMotorFront.configNeutralDeadband(0.001, TIMEOUT_MS);
    rightMotorBack.configNeutralDeadband(0.001, TIMEOUT_MS);

    leftMotorBack.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    leftMotorBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftMotorBack.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    leftMotorBack.configVelocityMeasurementWindow(16);
    leftMotorBack.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    rightMotorBack.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    rightMotorBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightMotorBack.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    rightMotorBack.configVelocityMeasurementWindow(16);
    rightMotorBack.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);
    }
  
    public void setModePercentVoltage() {
      leftMotorBack.set(ControlMode.PercentOutput, 0);
      rightMotorBack.set(ControlMode.PercentOutput, 0);
  
      leftMotorBack.set(ControlMode.PercentOutput, 0);
      rightMotorBack.set(ControlMode.PercentOutput, 0);
    }
  
    public static void drive(double throttle , double rotate) {
      leftMotorBack.set(throttle + rotate);
      rightMotorBack.set(throttle - rotate);
  
      leftMotorBack.set(throttle + rotate);
      rightMotorBack.set(throttle - rotate);
    }
    
    public void stop() {
      drive(0,0);
    }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
