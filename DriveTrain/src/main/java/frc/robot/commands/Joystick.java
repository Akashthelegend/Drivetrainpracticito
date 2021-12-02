// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Joystick extends CommandBase {
  private final DriveTrainSubsystem driveSubsystem;
  private final static XboxController driverController = RobotContainer.driverController;
  /** Creates a new Joystick. */
  public Joystick(DriveTrainSubsystem drivetrain) {
   // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = drivetrain;
    addRequirements(driveSubsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double throttle = driverController.getY(Hand.kLeft);
    double rotate = driverController.getX(Hand.kRight);
    if((throttle > 0 && throttle< 0.25) || (throttle < 0 && throttle > -0.25)) {
      throttle = 0;
    }
    else {
      throttle = throttle;
    }
    if((rotate > 0 && rotate< 0.25) || (rotate < 0 && rotate > -0.25)) {
      rotate = 0;
    }
    // Slow Mode
    if (driverController.getTriggerAxis(Hand.kRight) > 0.25) {
      throttle = Math.signum(throttle) * 0.75;
  }
    // Fast Mode
    else if (driverController.getAButton()) {
      throttle = (throttle*1.1);
  }
    // Normal Driving Speed
    else {
      throttle = (throttle*0.8);
  }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
