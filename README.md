
# MongoDB coding exercise


## Requirement

* Install java version 8 or above
* This project uses a gradle build system
* gradle is bundled into this project 
* gradle will be installed while building the project

## Build

* Command to build and run unit tests
  ``` 
  ./gradlew clean build 
  ```
* The build command will build a runnable uber jar
* The uber jar will be placed under `build/libs/` folder
* The name of the uber jar is `mongodb-exercise-all.jar`

## Run
* To run the program pass the file name as an argument to the uber jar
* The run commad looks like this
   ``` 
   java -jar build/libs/mongodb-exercise-all.jar simple.json 
   ```
* This will read the input, print the input and will terminate by printing the flattened json
