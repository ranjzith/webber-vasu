# webber

A simple web interface to perform various defined database activities.

Run the executable as below,

java -Dserver.port=1999 -jar build\libs\webber-1.0.0.jar --spring.config.additional-location="file:C:\Users\rmekala\MyDrive\Third-Party-Code\webber\config.yml" > webber.log 2>&1 &

Notes:
1. A sample config has been provided in the projects root directory.
2. Copy the driver.sh file from the project root directory to a location on the host machine and provide the absolute path to the file in above config file.
3. Provide processDirPath in config file to keep the logs

Improvements:
1. Spring Boot Rest Controller - JSON Object as response
2. Angular JS - Accessing form fields in controller
