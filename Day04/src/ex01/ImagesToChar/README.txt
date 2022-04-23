#write this commands
mkdir target
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
cp -r src/resources target/.
cd target
jar cfev images-to-chars-printer.jar edu/school21/printer/app/Main edu/school21/printer/app/Main.class edu/school21/printer/logic/Logic.class resources/it.bmp
java -jar images-to-chars-printer.jar . 0