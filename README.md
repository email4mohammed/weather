PREREQUISITES:
=============

- Java 8
- Apache Maven 3.3.x

ASSUMPTIONS:
===========
- The sunrise and sunset times are required in the local time of the city (as opposed to UTC time). Hence the times have been converted as per the timezone of the city.


TO RUN THE WEB APPLICATION:
==========================

1. Download the repository from Github.
2. In the log4j.properties file, located in weather\src\main\resources, modify the log4j.appender.file.File property to a suitable location on your machine for the application log files to reside.
3. In the repository root folder, weather, execute the command "mvn jetty:run"
4. Once Jetty has started, run the application in your favourite web browser with URL http://localhost:8080/weather/search


TODOS:
=====
- HTML is very basic.
- Checktyles on indentations and tab characters have been ignored due to time constraints, as it uses the default Eclipse Luna formatter and tab spaces. Most other Checkstyles issues have been resolved.
- Basic JUnit testing is present. Would have liked to have tested the page navigation flows if time had permitted.
- May need to rethink exception handling strategy. For the moment, for the basic functionality it is doing, it is sufficient.
- As the applicaton scales up: 
	(a) will need to find ways to store and refresh Timezones (currently hardcoded in Spring configuration to London and Hong Kong to meet this specific exercise requirement)
	(b) one JVM will not be sufficient, and will need to distribute across multiple nodes. A natural remoting layer exists at the service layer (e.g. JAX-RS/SOAP).

	
Enjoy!