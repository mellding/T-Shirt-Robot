// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class GTADriveCommand extends CommandBase {
  DrivetrainSubsystem driveTrain;
  XboxController controller01;

  /** Creates a new GTADriveCommand. */
  public GTADriveCommand(DrivetrainSubsystem driveTrain, XboxController controller01) {
    this.driveTrain = driveTrain;
    this.controller01 = controller01;
    
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.gtaDrive(controller01.getRightTriggerAxis(),
                         controller01.getLeftTriggerAxis(),
                         controller01.getLeftY());
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
