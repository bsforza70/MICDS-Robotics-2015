package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class RedAuto extends LinearOpMode {

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

    //Write this when making an autonomous program
    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.dcMotor.get("right_drive");
        rightDrive = hardwareMap.dcMotor.get("left_drive");
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

        //turns on the LED on the color sensor
        allianceSensor.enableLed(true);

        waitForStart();

        //Time it takes to move 1 floor block (or 60 cm) below, calculations based off of that.
        long blockTime = 1270;

        //Time it takes to turn a quarter-turn (90 degrees) below, calculations based off of that.
        //Reminder that these are in ms
        long turnTime = 1398;

        //half of this (45 degrees) is...
        long halfTurnTime = 699;

        //Starting to actually move, better scream it to the world so everyone knows
        telemetry.addData("Done?", "Nope!");

        //We don't need this on all the time.
        allianceSensor.enableLed(false);

        //Move from start to beacon
        forward(blockTime);
        stoperino(200);
        turnRight(halfTurnTime);
        stoperino(200);
        /*
        while (lineSensor.getLightDetected() < 0.5){
            forward(0); //Go until you get to the guiding line (if bad, this is about 2.828 blocks)
        }
        */
        forward(3592); //It was bad lol

        turnRight(halfTurnTime); //Robot should be facing the beacon head on now :)

        //Light Sensor Stuff for Beacon
        if(checkColor()){
            //matching alliance is detected (red)
            turnRight(halfTurnTime);
            stoperino(200);
            rightDrive.setPower(1.0);
            leftDrive.setPower(0.5);
            sleep(1000);
            stoperino(200);
            //now return to your original position ;)
            rightDrive.setPower(-1.0);
            leftDrive.setPower(-0.5);
            sleep(1000);
            stoperino(200);
        } else {
            //other alliance is detected (blue
            turnLeft(halfTurnTime);
            rightDrive.setPower(0.5);
            leftDrive.setPower(1.0);
            sleep(1000);
            stoperino(200);
            //now return to your original position ;)
            rightDrive.setPower(-0.5);
            leftDrive.setPower(-1.0);
            sleep(1000);
            stoperino(200);
        }
        
        //Move back a little bit so you turn cleanly
        rightDrive.setPower(-1.0);
        leftDrive.setPower(-1.0);
        sleep(500);
        stoperino(200);

stoperino(7000);
        //Move from Beacon to mountain on other side
        turnRight(turnTime * 2);
        stoperino(200);
        forward(blockTime * 2);
        stoperino(200);
        turnLeft(halfTurnTime);
        stoperino(200);
        forward(blockTime * 2);

        //Stop moving after
        rightDrive.setPower(0);
        leftDrive.setPower(0);

        //tell the world you're done and jus' chillin' yo
        telemetry.clearData();
        telemetry.addData("Done?", "Completed! :D");
    }

    public void forward(long blockTime) throws InterruptedException{
        rightDrive.setPower(1.0);
        leftDrive.setPower(1.0);
        sleep(blockTime);
        telemetry.addData("Status", "Going Forward!");
    } //both motors on

    public void turnRight(long turnTime) throws InterruptedException{
        rightDrive.setPower(1.0);
        leftDrive.setPower(-1.0);
        sleep(turnTime);
        telemetry.addData("Status", "Turning Right!");
    } // left motor reversed, right on

    public void turnLeft(long turnTime) throws InterruptedException{
        rightDrive.setPower(-1.0);
        leftDrive.setPower(1.0);
        sleep(turnTime);
        telemetry.addData("Status", "Turning Left!");
    } // right motor reversed, left on

    public void stoperino(long stopTime) throws InterruptedException{
        rightDrive.setPower(0);
        leftDrive.setPower(0);
        sleep(stopTime);
        //SWAG OUT
    }

    public boolean checkColor() throws InterruptedException{
        telemetry.addData("Clear", allianceSensor.alpha());
        telemetry.addData("Red  ", allianceSensor.red());
        telemetry.addData("Green", allianceSensor.green());
        telemetry.addData("Blue ", allianceSensor.blue());
        return (allianceSensor.red() > allianceSensor.blue());
    } //true if red, false if blue

}   //TO-DO: Do the two measurements and test!
    // Also flip it around for the sensor to read since it's on the back
