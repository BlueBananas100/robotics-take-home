// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkMax;

/**
 * This is an EDITED demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final SparkMax m_FLMotor = new SparkMax(0, MotorType.kBrushless);
  private final SparkMax m_FRMotor = new SparkMax(1, MotorType.kBrushless);
  private final SparkMax m_BLMotor = new SparkMax(2, MotorType.kBrushless);
  private final SparkMax m_BRMotor = new SparkMax(3, MotorType.kBrushless);
  private final SparkMax rightArmMotor = new SparkMax(4, MotorType.kBrushless);
  private final SparkMax leftArmMotor = new SparkMax(5, MotorType.kBrushless);
  
  
  private final XboxController controller = new XboxController(0);
  
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_FLMotor::set, m_FRMotor::set);
  // TODO: Figure out how to set back motors to follow
  private final Joystick m_stick = new Joystick(0);

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_FLMotor);
    SendableRegistry.addChild(m_robotDrive, m_FRMotor);
    SendableRegistry.addChild(m_robotDrive, m_BLMotor);
    SendableRegistry.addChild(m_robotDrive, m_BRMotor);

    m_FRMotor.setInverted(true);
    m_BRMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());

    if (controller.getYButtonPressed()) {
      rightArmMotor.set(1);
      leftArmMotor.set(1);
    } else if (controller.getAButtonPressed()) {
      rightArmMotor.set(-1);
      leftArmMotor.set(-1);
    } else {
      rightArmMotor.set(0);
      leftArmMotor.set(0);
    }
  }

  public void autonomous() {
    m_robotDrive.arcadeDrive(1.0, 0);
    Timer.delay(3);
    m_robotDrive.arcadeDrive(1.0, 90);
  }
}
