#Шаг-1
	#из корневой папки задания ввести команду:
mkdir target
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
#Шаг-2
	#Из корневой папки ввести команду с аргумантами в формате java -cp target edu.school21.printer.app.Main "белые пиксели" "черные пиксели" "путь к файлу картинки"
java -cp target edu.school21.printer.app.Main  . 0 C:\Java-pool\other\it.bmp
