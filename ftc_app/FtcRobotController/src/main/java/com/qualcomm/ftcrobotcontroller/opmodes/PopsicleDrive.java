package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class PopsicleDrive extends OpMode {

    DcMotor rightDrive;
    DcMotor leftDrive;
    DcMotorController driveController;
    DcMotor arm;
    DcMotor sweeper;
    DcMotor armConveyor;
    DcMotor rope;

    Servo rightPoleGrab;
    Servo leftPoleGrab;

    LightSensor lineSensor;
    ColorSensor allianceSensor;

    int numOpLoops = 1;

    @Override
    public void init() {
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        driveController = hardwareMap.dcMotorController.get("drive");
        arm = hardwareMap.dcMotor.get("arm");
        sweeper = hardwareMap.dcMotor.get("sweeper");
        armConveyor = hardwareMap.dcMotor.get("conveyor");
        rope = hardwareMap.dcMotor.get("rope");

        rightPoleGrab = hardwareMap.servo.get("right_res");
        leftPoleGrab = hardwareMap.servo.get("left_res");

        lineSensor = hardwareMap.lightSensor.get("line_sensor");
        allianceSensor = hardwareMap.colorSensor.get("ally_sensor");

        //reverses the right motor
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        rightDrive.setPower(rightY);
        leftDrive.setPower(leftY);

        float sexyStan = -gamepad2.right_stick_y;

        arm.setPower(sexyStan);

        if(sexyStan > 0){
            rope.setPower(-1);
        } else if (sexyStan < 0){
            rope.setPower(1);
        } else {
            rope.setPower(0);
        }

        if(gamepad2.left_bumper){
            sweeper.setPower(-1);
        } else if(gamepad2.right_bumper){
            sweeper.setPower(1);
        } else {
            sweeper.setPower(0);
        }

        if(gamepad1.dpad_up){
            rope.setPower(1);
        } else if (gamepad1.dpad_down){
            rope.setPower(-1);
        } else {
            rope.setPower(0);
        }


        if(gamepad1.left_bumper){
            armConveyor.setPower(-1);
        } else if(gamepad1.right_bumper){
            armConveyor.setPower(1);
        } else {
            armConveyor.setPower(0);
        }


        //Servo positions need to be re-found. Could use telemetry and slowly adding a position up and down like OG code
        if (gamepad2.a) {
            leftPoleGrab.setPosition(0.75);  //Down
        }

        if (gamepad2.b) {
            leftPoleGrab.setPosition(.14);   //Up
        }

        if (gamepad2.x) {
            rightPoleGrab.setPosition(0.5);  //Down
        }

        if (gamepad2.y) {
            rightPoleGrab.setPosition(1);    //Up
        }
        telemetry.addData("Text", "Running!");

        //Setting the motor controller to be able to be read as much as possible (takes time to switch)
        if(numOpLoops % 17 == 0){
            driveController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        }

        //Reading, and switching back after.
        if(driveController.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY){
            telemetry.addData("Right Motor", rightDrive.getPower());
            telemetry.addData("Left Motor", leftDrive.getPower());
            driveController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            numOpLoops = 0;
        }

        numOpLoops++;
    }
}   //TO-DO: Write stuff for everything else the robot might have on it.
