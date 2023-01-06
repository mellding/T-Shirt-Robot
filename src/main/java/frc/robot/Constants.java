// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //CONSTANTS FOR THE DRIVE TRAIN SUBSYSTEM
    public static final int LEFT_MOTOR_1_CAN_ID        = 11;
    public static final int LEFT_MOTOR_2_CAN_ID        = 12;
    public static final int RIGHT_MOTOR_1_CAN_ID       = 13;
    public static final int RIGHT_MOTOR_2_CAN_ID       = 14;  
    public static final double DRIVE_RAMP_RATE         = 1;
    public static final int DRIVE_MAX_CURRENT          = 30;  
    public static final int DRIVE_WHEEL_DIA            = 6;    //Wheel Diameter in inches
    public static final int DRIVE_BASE_WIDTH           = 27;   //Distance between left and right wheels
    public static final double SLOW_DRIVE_MAX          = .25;

    //CONSTANTS FOR THE REVOLVER/SHOOTER SUBSYSTEM
    //INCLUDES TILT, ROTATE, AND REVOLVE 
    public static final int SHOOTER_RELAY_ID           = 0;
    public static final int SHOOTER_DELAY              = 100; //Time in Milliseconds that the firing Solenoid will remain on
    public static final int TILT_MOTOR_CAN_ID          = 15;
    public static final int ROTATE_MOTOR_CAN_ID        = 16;
    public static final int REVOLVE_MOTOR_CAN_ID       = 17;
    public static final int TILT_MAX_CURRENT           = 10;
    public static final double TILE_RAMP_RATE          = .25;
    public static final double ROTATE_RAMP_RATE        = .25;
    public static final double REVOLVE_MAX_DELAY       = 1500; //Time in milliseconds allowed for the 
                                                                //revolver to rotate before throwing error
    public static final int ROTATE_MAX_CURRENT         = 15;
    public static final int REVOLVER_ENCODER_CPR       = 8192;
    public static final int REVOLVER_MAX_PEAK_CURRENT  = 35;
    public static final int REVOLVER_MAX_CURRENT_TIME  = 500;  //Time in Milliseconds the the Peak Current is allowed
    public static final int REVOLVER_MAX_CONST_CURRENT = 10;
    public static final int REVOLVER_MAX_ERROR         = 22;   //Max number of encoder counts the revolver can be off of setpoint
    public static final double REVOLVER_KP             = 1;
    public static final double REVOLVER_KI             = 0;
    public static final double REVOLVER_KD             = 0;

}
