compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*.java
jar:
	jar cfm0 ass5game.jar MANIFEST.MF -C bin .
run:
	java -jar ass5game.jar
bin:
	mkdir bin
