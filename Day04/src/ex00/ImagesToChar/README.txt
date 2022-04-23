#Step-1
	#from root directory use this command:
mkdir target
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
#Step-2
	#from root directory use this command: java -cp target edu.school21.printer.app.Main "white" "black" "image path"
java -cp target edu.school21.printer.app.Main  . 0 C:\Java-pool\other\it.bmp
