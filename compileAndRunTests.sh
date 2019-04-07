#!/bin/bash

cd src
javac -cp . projet_bataille_navale/*.java
javac -cp . projet_bataille_navale/graphic/*.java
javac -cp .:../libs/junit-platform-console-standalone-1.5.0-M1.jar projet_bataille_navale/unitTests/*.java
java -jar ../libs/junit-platform-console-standalone-1.5.0-M1.jar --class-path . --scan-class-path

