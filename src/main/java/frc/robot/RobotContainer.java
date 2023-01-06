// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BrakeCommand;
import frc.robot.commands.EnableShooter;
import frc.robot.commands.GTADriveCommand;
import frc.robot.commands.ShootOneCommand;
import frc.robot.commands.ShooterAimCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private static final DrivetrainSubsystem driveTrain = new DrivetrainSubsystem();
  private static final ShooterSubsystem shooter = new ShooterSubsystem();
  private static final XboxController controller01    = new XboxController(0);

  private static JoystickButton brakeButton = new JoystickButton(controller01, XboxController.Button.kB.value);
  private static JoystickButton shootButton = new JoystickButton(controller01, XboxController.Button.kBack.value);
  private static JoystickButton enableShooterButton = new JoystickButton(controller01, XboxController.Button.kStart.value);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    driveTrain.setDefaultCommand(new GTADriveCommand(driveTrain, controller01));
    shooter.setDefaultCommand(new ShooterAimCommand(shooter, controller01));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    brakeButton.whenPressed(new BrakeCommand(driveTrain));
    shootButton.whenPressed(new ShootOneCommand(shooter));
    enableShooterButton.whenPressed(new EnableShooter(shooter));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
