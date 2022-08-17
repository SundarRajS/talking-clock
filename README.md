# Talking Clock

## Overview
The following repo contains talking clock API

## Guidelines to Run the application.


1. Clone this repository

2. Go to the repo folder and run ```mvn clean install```

3. Once the process is completed, there should be the Jar file instead the target folder

4. run the jar file with the command ```java -jar time-0.0.1-SNAPSHOT.jar```. Application will be running in the port 8001.

## Run the application with docker.

1. Go to the repo folder and build the application with the command ```docker build . -t <tagname>```.

2. start the application with docker using ```docker run -p 8001:8001 <tagname>```


## Source Code

Source code for the example is located in /src/main/java/com/sundar/time. 
tests are located in /src/test/java/com/sundar/time.

## Run the example of talking clock


1. Go to the application url using http://localhost:8001/time-to-words. by default the system will be displaying the current time in the application.

2. Pass the Arguments to application say for eg. http://localhost:8001/time-to-words?time=11:00 to display the passed time in words.


