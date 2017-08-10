# Geo-coding and Bounding Box

This program determines from a list a addresses which one are inside of a polygon. This program is written in Java using Maven for the build, that showcases a sample tests which are written with JUnit 4. Here I have used Ray-casting algorithm which will determine if an address's coordinate is inside a plotted shape. You can create Google Maps Geocoding API key from https://developers.google.com/maps/documentation/geocoding/get-api-key.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to run the software

JDK 1.7 or higher
Maven 3.5.0
Internet Connection
Windows

### Setting up Java Enviornment

-Check if you have JDK installed on your computer and enviornment variable are set:

1. java -version

	It should return something like this:

	java version "1.8.0_131"
	Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
	Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)

2. javac -version

	It should return something like this:

	javac 1.8.0_121
	
This shows that if similiar output is obtained then Java enviornment is configured and you can skip the next install step.

-To Install the JDK Software and Set JAVA_HOME on a Windows System

1. Install the JDK software.

	a. Go to http://java.sun.com/javase/downloads/index.jsp.
	b. Select the appropriate JDK software and click Download.
	
The JDK software is installed on your computer, for example, at C:\Program Files\Java\jdk1.6.0_02. You can move the JDK software to another location if desired.

2. Set JAVA_HOME:

	a. Right click My Computer and select Properties. Then select the Advanced tab, and the Environment Variables button
	b. In System Variables, set JAVA_HOME to point to where the JDK software is located. For example, C:\Program Files\Java\jdk1.8.0_121.
	c. Add the location of the bin folder of the JDK installation for the PATH variable in System Variables. For example, %JAVA_HOME%\bin.

### Setting up Maven

-Check if you have Maven installed on your computer and enviornment variable are set:  

	mvn -v

	It should return something like this:

	Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T15:39:06-04:00)
	Maven home: C:\Users\Jigar\Downloads\apache-maven-3.5.0-bin\apache-maven-3.5.0\bin\..
	Java version: 1.8.0_121, vendor: Oracle Corporation
	Java home: C:\Program Files\Java\jdk1.8.0_121\jre
	Default locale: en_US, platform encoding: Cp1252
	OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

This shows that if similiar output is obtained then Maven is installed and you can skip the next install step.

-To Install the Maven and add into PATH environment variable on a Windows System

1. Install Maven.

	a. Go to https://maven.apache.org/download.cgi.
	b. Download the latest Binary zip archive for example apache-maven-3.5.0-bin.zip and extract distribution archive in any directory.
	
2. Add to PATH:

	a. Right click My Computer and select Properties. Then select the Advanced tab, and the Environment Variables button
	b. Add the location of the bin folder of the installed Maven directory for the PATH variable in System Variables. For example, C:\Program Files\apache-maven-3.5.0\bin.
	
## Running the program

-Setting up the directory to run the program.

Unzip the Geocoding.zip in any directory. For example C:\Users\Jigar\Service\Geocoding

-Setting up the executable jar file to run the program from command line.

Download the executable jar file in any directory. For example C:\Users\Jigar\Service\Geocoding\Geocoding-0.0.1-SNAPSHOT.jar

-Running program from command line

1. Go to the directory where we unzipped the project. For example C:\Users\Jigar\Service\Geocoding

2. Installs a file in the local repository.

	mvn install -DskipTests=true
	
	It should return something like this:

	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time: 5.928 s
	[INFO] Finished at: 2017-07-20T11:22:06-04:00
	[INFO] Final Memory: 20M/322M
	[INFO] ------------------------------------------------------------------------
	
3. Run the program

	java -jar C:\Users\Jigar\Service\Geocoding\Geocoding-0.0.1-SNAPSHOT.jar C:\Users\Jigar\Service\Geocoding\src\input.txt (yourAPIKey)
	
	Input file located at C:\Users\Jigar\Service\Geocoding\src\input.txt contains:
	(37.5, -122.5), (38.2, -121.6), (37.0, -121.4), (36.6, -121.3)
	1706 Forest View Ave Hillsborough CA 94010
	1 Hacker Way, Menlo Park, CA
	3045 Novato Blvd, Novato, CA 94947
	30840 Northwestern Highway, Farmington Hills, MI

	Output:
	1706 Forest View Ave Hillsborough CA 94010
	1 Hacker Way, Menlo Park, CA
	
	If using your own API Key then replace with: java -jar C:\Users\Jigar\Service\Geocoding\Geocoding-0.0.1-SNAPSHOT.jar C:\Users\Jigar\Service\Geocoding\src\input.txt yourAPIKey

## Running the tests

-How to run the automated tests for this system

	mvn test -DAPIKey=yourAPIKey
	
	It should return something like this:
	
	-------------------------------------------------------
	 T E S T S
	-------------------------------------------------------
	Running com.geocoding.MainTest
	Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 10.459 sec

	Results :

	Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time: 12.961 s
	[INFO] Finished at: 2017-07-20T11:23:43-04:00
	[INFO] Final Memory: 9M/245M
	[INFO] ------------------------------------------------------------------------
	
	If using your own API Key then replace with: mvn test yourAPIKey

### Break down of sample tests

```
Triangle
```

I have tested the possibility where the shape of the polygon is triangle given in input-triangle.txt file. The test is passed succesfully

```
Octagon
```

I have tested the possibility where the shape of the polygon is octagon given in input-octagon.txt file. The test is passed succesfully

```
Equator
```

I have tested the possibility where the polygon is lying on equator given in input-equator.txt file. The test is passed succesfully


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Jigar Shah**
