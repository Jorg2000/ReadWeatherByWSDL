
# Check Weather In Your Airport
### About
This small&simple web application shows you weather in different airports around the world. It based on SOAP [GlobalWeatherAPI](http://www.webservicex.net/ws/WSDetails.aspx?CATID=12&WSID=56) from [WebserviceX.NET](http://WebserviceX.NET). It also uses [Google Places API](https://developers.google.com/places/?hl=en) to predict country names in the input fields. 
### Architecture
This app consists of two parts:
- Frontend based on HTML+JavaScript+CSS. It gives you ability to enter name of the country and choose one of available airports. All interconnections between frontend page and backend service based on Ajax.    
- Backend based on Java Servlets. This part negotiates with apis and sends prepared weather data to frontend in JSON. 

### Technology stack  
Java, jUnit, Mockito, jDom2, Gson, Maven, Tomcat, JavaScript, jQuery, AJAX, SOAP, GooglePlacesAPI, GoogleMapsAPI etc.
* [Template for start page](http://www.bootstrapzero.com/bootstrap-template/squad) 
* [Template for 404 page](http://www.os-templates.com/page-templates/404-templates/404-17)

### With very big thanks to
http://stackoverflow.com/

### HOWTO
If you want to run this project in your environment you have to:

1. Download and unpack Tomcat 7 servlet container. 

- Import and build project in your IDE. (For Example am Using IntelliJ IDEA & Maven).

2. Chose: File -> New-> Project from version control -> GitHub. 
3. Enter link to repository in "GIT repository URL" field, push Clone button and wait for completion of download.
4. Idea has to recognize Maven config in the sources and asks you to use it with the help of the bubble, but IF NOT: a) Press View->Tools Window -> Maven Projects; b) Press "Green plus button" located on the top panel; c) choose file "pom.xml" in directory named ReadWeatherByWSDL and press OK. Wait until Maven finishes downloading all dependencies. 
5. Create WAR file for Tomcat: a) open Maven Projects Window again; b) Choose Simple WeatherInTheAirport Client Based on SOAP API -> Lifcycle -> Package.
6. Make backup copy of tomcat7/webapps/ROOT folder. It contains default start page for Apache tomcat. tomcat7 - directory where you unpacked Apache Tomcat.
7. Delete tomcat7/webapps/ROOT folder.
8. Find ROOT.WAR file in ReadWeatherByWSDL/target folder and copy it to the tomcat7/webapps/.
9. RUN tomcat7/bin/startup.sh (or startup.bat if you are using windows) to start tomcat server. 
10. Open your browser, put http://localhost:8080 and press enter.
11. If everything good you will see the start page.

# You can every time test this app here: [weather.tomcat.ninja](http://weather.tomcat.ninja)
