#���-1
	#�� �������� ����� ������� ������ �������:
mkdir target
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
#���-2
	#�� �������� ����� ������ ������� � ����������� � ������� java -cp target edu.school21.printer.app.Main "����� �������" "������ �������" "���� � ����� ��������"
java -cp target edu.school21.printer.app.Main  . 0 C:\Java-pool\other\it.bmp
