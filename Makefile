#Makefile

build:
	@javac src/*.java
	@cd src && jar cfm ../JarMapTeste.jar ../manifest.mf *.class
	
clean:
	@rm -Rf ./src/*.class
	@rm -Rf ./JarMapTeste.jar

run:
	@java -jar JarMapTeste.jar
