The code here is material I am creating for a Core Java Workshop I am planning to conduct. 

PreRequisites : 

1) Install Java on your computer (the examples here use Java 17). Details will be provided before/during the workshop
2) The code can be compiled using any IDE (IntelliJ or Eclipse are preferred).
3) A maven pom.xml file is provided to enable the code to be compiled via Maven. (mvn clean compile)
4) Code can be run via the IDE. To run it from the commandline/terminal, go to the target directory and run java
   For example, to run the BasicProgramStructure program
   java -cp target/classes org.spjain.bds.fundamentals.BasicProgramStructure

For folks in the workshop, please dont get overwhelmed. All these steps will be explained during the workshop

To run a class from the command line (see example below) 

mvn compile
mvn exec:java -Dexec.mainClass=org.spjain.bds.fundamentals.BasicFunctionCalls
