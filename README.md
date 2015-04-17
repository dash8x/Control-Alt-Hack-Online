# Control-Alt-Hack-Online

Java Runtime Environment Required
------------------------------------------------------------------------------
All the code was written and tested on Java Runtime Environment version 1.7.0_51
It is required that you have Java Runtime Environment installed and the java classpath
correctly setup to compile and run the code on your system

How to Compile the Game from Console
------------------------------------------------------------------------------
1. Navigate to the folder "Control-Alt-Hack-Online" from your console
2. Run the following command
javac -classpath bin -sourcepath src -d bin src\grp\ctrlalthack\view\MainView.java

Alternatively, if you are in a Windows environment, you can run the included compile.bat file

How to Run the Server from Console
------------------------------------------------------------------------------
1. Navigate to the folder "APR Student Records System" from your console
2. Compile the code using the above method
3. Run the following command
java -cp .;json-20140107.jar;bin grp.ctrlalthack.view.MainView

Alternatively, if you are in a Windows environment, you can run the included run.bat file
To open multiple instances, you can open a new console window and run this command multiple times