// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  
  private static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(Constants.LEFT_MOTOR_1_CAN_ID);
  private static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(Constants.LEFT_MOTOR_2_CAN_ID);
  private static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_1_CAN_ID);
  private static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_2_CAN_ID);

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    leftDrive1.configFactoryDefault();  leftDrive2.configFactoryDefault();
    rightDrive1.configFactoryDefault(); rightDrive2.configFactoryDefault();

    leftDrive1.setInverted(false);  leftDrive2.setInverted(false);
    rightDrive1.setInverted(true);  rightDrive2.setInverted(true);

    leftDrive1.configContinuousCurrentLimit(Constants.DRIVE_MAX_CURRENT);
    leftDrive2.configContinuousCurrentLimit(Constants.DRIVE_MAX_CURRENT);
    rightDrive1.configContinuousCurrentLimit(Constants.DRIVE_MAX_CURRENT);
    rightDrive2.configContinuousCurrentLimit(Constants.DRIVE_MAX_CURRENT);

    leftDrive1.enableCurrentLimit(true); leftDrive2.enableCurrentLimit(true);
    rightDrive1.enableCurrentLimit(true); rightDrive2.enableCurrentLimit(true); 

    leftDrive2.follow(leftDrive1);  rightDrive2.follow(rightDrive1);

    leftDrive1.configOpenloopRamp(Constants.DRIVE_RAMP_RATE);
    rightDrive1.configOpenloopRamp(Constants.DRIVE_RAMP_RATE);

    leftDrive1.setNeutralMode(NeutralMode.Coast);  leftDrive1.setNeutralMode(NeutralMode.Coast);
    rightDrive1.setNeutralMode(NeutralMode.Coast); rightDrive2.setNeutralMode(NeutralMode.Coast);
  }

  public void setMotorsPercent(double leftPower, double rightPower){
    leftDrive1.setNeutralMode(NeutralMode.Coast);  leftDrive1.setNeutralMode(NeutralMode.Coast);
    rightDrive1.setNeutralMode(NeutralMode.Coast); rightDrive2.setNeutralMode(NeutralMode.Coast);
    leftDrive1.configOpenloopRamp(Constants.DRIVE_RAMP_RATE);
    rightDrive1.configOpenloopRamp(Constants.DRIVE_RAMP_RATE);

    leftDrive1.set(ControlMode.PercentOutput, leftPower);
    rightDrive1.set(ControlMode.PercentOutput, rightPower);
  }

  public void brake(){
    leftDrive1.setNeutralMode(NeutralMode.Brake);  leftDrive1.setNeutralMode(NeutralMode.Brake);
    rightDrive1.setNeutralMode(NeutralMode.Brake); rightDrive2.setNeutralMode(NeutralMode.Brake);
    leftDrive1.configOpenloopRamp(0);
    rightDrive1.configOpenloopRamp(0);
    leftDrive1.set(ControlMode.PercentOutput, 0);
    rightDrive1.set(ControlMode.PercentOutput, 0);
  }

  public void gtaDrive(double rBumper, double lBumper, double turn){
    setMotorsPercent((rBumper - lBumper) - turn, (rBumper - lBumper) + turn);
  }

  public void gtaDriveSlow(double rBumper, double lBumper, double turn){
    double leftMotor = ((rBumper - lBumper) - turn);
    double rightMotor = ((rBumper - lBumper) + turn);
    leftMotor = mapDouble(leftMotor, -2, 2, -Constants.SLOW_DRIVE_MAX, Constants.SLOW_DRIVE_MAX);
    rightMotor = mapDouble(rightMotor, -2, 2, -Constants.SLOW_DRIVE_MAX, Constants.SLOW_DRIVE_MAX);
    setMotorsPercent(leftMotor, rightMotor);
  }

  public static double mapDouble(double valueIn, double baseMin, double baseMax, double limitMin, double limitMax) {
    return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
