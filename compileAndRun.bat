cd src
javac -cp . projet_bataille_navale\*.java
javac -cp . projet_bataille_navale\graphic\*.java
jar cmf projet.mf projet.jar projet_bataille_navale
java -cp . projet_bataille_navale.Main

