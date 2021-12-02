// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  public static NetworkTable limelightTable;

  //limelight x and y values for tracking
  public static double limelight_x;
  public static double limelight_y;

  //limelight LED on, off, or blink
  public static double FORCE_OFF = 1;
  public static double FORCE_BLINK = 2;
  public static double FORCE_ON = 3;

  //modes for limelight camera
  public static double VISION_PROCESSOR = 0;
  public static double DRIVER_CAMERA = 1;

  public static void init_Limelight() {

    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = limelightTable.getEntry("tx");
    NetworkTableEntry ty = limelightTable.getEntry("ty");

    limelight_x = (Double) tx.getDouble(0.0);
    limelight_y = (Double) ty.getDouble(0.0);

    //
    limelightTable.getEntry("ledMode").setNumber(FORCE_ON);
    limelightTable.getEntry("camMode").setNumber(VISION_PROCESSOR);
  }

  public static void refreshLimelightData() {
    limelight_x = (Double) limelightTable.getEntry("tx").getDouble(0.0);
    limelight_y = (Double) limelightTable.getEntry("ty").getDouble(0.0);
  }
//Call to turn LED on
  public static void turn_LED_ON() {
    limelightTable.getEntry("ledMode").setNumber(FORCE_ON);
  }
//Call to turn LED off
  public static void turn_LED_OFF() {
    limelightTable.getEntry("ledMode").setNumber(FORCE_OFF);
  }
//Call to blink LED
  public static void turn_LED_FLASH_BLINK() {
    limelightTable.getEntry("ledMode").setNumber(FORCE_BLINK);
  }

  public static double getLimelightX() {
    return (Double) limelightTable.getEntry("tx").getDouble(0.0);
  }

  public static double getLimelightY() {
    return (Double) limelightTable.getEntry("ty").getDouble(0.0);
  }

  public static void setDriverCamera() {
    limelightTable.getEntry("camMode").setNumber(DRIVER_CAMERA);
    turn_LED_OFF();
  }

  public static void setVisionProcessor() {
    limelightTable.getEntry("camMode").setNumber(VISION_PROCESSOR);
    turn_LED_ON();
  }
  
  static NetworkTable getTable() {
    return NetworkTableInstance.getDefault().getTable("limelight");
  }
  
  /** Creates a new Limelight. */
  public Limelight() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
