// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Date;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private static final Relay shooterRelay = new Relay(Constants.SHOOTER_RELAY_ID);
  private static final WPI_TalonSRX tiltMotor   = new WPI_TalonSRX(Constants.TILT_MOTOR_CAN_ID);
  private static final WPI_TalonSRX rotateMotor = new WPI_TalonSRX(Constants.ROTATE_MOTOR_CAN_ID);
  private static final WPI_TalonSRX revolveMotor = new WPI_TalonSRX(Constants.REVOLVE_MOTOR_CAN_ID);

  private static Date shooterTimer = new Date();
  private static int shotCounter = 0;
  private static int currentShooterPos = 0;
  private static int nextShooterPos = 0;
  private static boolean isEnabled = true;

  public ShooterSubsystem() {
    tiltMotor.configFactoryDefault();  
    tiltMotor.setNeutralMode(NeutralMode.Brake);
    tiltMotor.configContinuousCurrentLimit(Constants.TILT_MAX_CURRENT);
    tiltMotor.enableCurrentLimit(true);

    rotateMotor.configFactoryDefault();
    rotateMotor.setNeutralMode(NeutralMode.Brake);
    rotateMotor.configContinuousCurrentLimit(Constants.ROTATE_MAX_CURRENT);
    rotateMotor.enableCurrentLimit(true);
    
    revolveMotor.configFactoryDefault();
    revolveMotor.configPeakCurrentLimit(Constants.REVOLVER_MAX_PEAK_CURRENT);
    revolveMotor.configPeakCurrentDuration(Constants.REVOLVER_MAX_CURRENT_TIME);
    revolveMotor.configContinuousCurrentLimit(Constants.REVOLVER_MAX_CONST_CURRENT);
    revolveMotor.enableCurrentLimit(true);
    revolveMotor.setNeutralMode(NeutralMode.Coast);
    revolveMotor.configAllowableClosedloopError(0, Constants.REVOLVER_MAX_ERROR);
    revolveMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    revolveMotor.config_kP(0, Constants.REVOLVER_KP);
    revolveMotor.config_kI(0, Constants.REVOLVER_KI);
    revolveMotor.config_kD(0, Constants.REVOLVER_KD);
    revolveMotor.setSelectedSensorPosition(0);

    shooterRelay.setDirection(Relay.Direction.kReverse);
  }

  public void tilt(double tiltPower){
    tiltMotor.set(ControlMode.PercentOutput, tiltPower);
  }

  public void rotate(double rotatePower){
    rotateMotor.set(ControlMode.PercentOutput, rotatePower);
  }

  public void revolveToNext(){
    if(!isEnabled) return;
    if(shotCounter % 2 == 0) nextShooterPos = currentShooterPos + Math.round(Constants.REVOLVER_ENCODER_CPR / 6);
    else nextShooterPos = currentShooterPos + Math.round(Constants.REVOLVER_ENCODER_CPR / 6) - 1;
    revolveMotor.set(ControlMode.Position, nextShooterPos);
    double startTime = shooterTimer.getTime();
    while(true){
      if((Math.abs(shooterTimer.getTime() - startTime) <= Constants.REVOLVE_MAX_DELAY)
          && (Math.abs(revolveMotor.getSelectedSensorPosition() - nextShooterPos) <= Constants.REVOLVER_MAX_ERROR)) {
            break;
          }else if((Math.abs(shooterTimer.getTime() - startTime) >= Constants.REVOLVE_MAX_DELAY)
                    && (Math.abs(revolveMotor.getSelectedSensorPosition() - nextShooterPos) >= Constants.REVOLVER_MAX_ERROR)){
            disableRevolver();
            return;
          }
    } 
    currentShooterPos = nextShooterPos;
    shotCounter++;
  }

  public void revolveToPrev(){
    if(!isEnabled) return;
    if(shotCounter % 2 == 0) nextShooterPos = currentShooterPos - Math.round(Constants.REVOLVER_ENCODER_CPR / 6);
    else nextShooterPos = currentShooterPos - Math.round(Constants.REVOLVER_ENCODER_CPR / 6) - 1;
    revolveMotor.set(ControlMode.Position, nextShooterPos);
    double startTime = shooterTimer.getTime();
    while(true){
      if((Math.abs(shooterTimer.getTime() - startTime) <= Constants.REVOLVE_MAX_DELAY)
          && (Math.abs(revolveMotor.getSelectedSensorPosition() - nextShooterPos) <= Constants.REVOLVER_MAX_ERROR)) {
            break;
          }else if((Math.abs(shooterTimer.getTime() - startTime) >= Constants.REVOLVE_MAX_DELAY)
                    && (Math.abs(revolveMotor.getSelectedSensorPosition() - nextShooterPos) >= Constants.REVOLVER_MAX_ERROR)){
            disableRevolver();
            return;
          }   
    } 
    currentShooterPos = nextShooterPos;
    shotCounter--;
  }

  public void disableRevolver(){
    isEnabled = false;
    revolveMotor.set(ControlMode.PercentOutput, 0);
    //revolveMotor.disable();
  }

  public void enableRevolver(){
    isEnabled = true;
  }

  public void shoot(){
    if(!isEnabled) return;
    shooterRelay.setDirection(Relay.Direction.kForward);
    double startTime = shooterTimer.getTime();
    while(Math.abs(shooterTimer.getTime() - startTime) <= Constants.SHOOTER_DELAY){
      //Do nothing until the delay time has passed.
    }
    shooterRelay.setDirection(Relay.Direction.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
