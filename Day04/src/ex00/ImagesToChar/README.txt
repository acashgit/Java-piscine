#Шаг-1
	из корневой папки задания ввести команду:
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
#Шаг-2
	Из корневой папки ввести команду:
java -cp target edu.school21.printer.app.Main  “ символ для белого” “символ для черного” “путь к файлу картинки”
