PREREQUISITES:
=============

- Java 8
- Apache Maven 3.3.x

TO RUN THE WEB APPLICATION:
==========================

1. Download the repository from Github.
2. In the log4j.properties file, located in weather\src\main\resources, modify the log4j.appender.file.File property to a suitable location on your machine for the application log files to reside.
3. In the repository root folder, weather, execute the command "mvn jetty:run"
4. Once Jetty has started, run the application in your favourite web browser with URL http://localhost:8080/weather/search


TODOS:
=====
- HTML is very basic.
- May need to rethink exception handling strategy. For the moment, for the basic functionality it is doing, it is sufficient.
- As the applicaton scales up, 
	(a) will need to find ways to store and refresh Timezones (currently hardcoded in Spring configuration to London and Hong Kong to meet this specific exercise requirement)
	(b) one JVM will not be sufficient, and will need to distribute across multiple nodes. A natural remoting layer exists at the service layer (e.g. JAS-RS/SOAP).

	
Enjoy!