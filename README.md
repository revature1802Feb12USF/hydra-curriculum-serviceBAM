# hydra-curriculum-service
The curriculum micro-service is crucial to our design as well as the original monoliths. In the monolith, the curriculum model contained information from curriculum service and the topic service. Each of those services retrieved data from their respective repositories for the curriculum model. When converting the monolith to a micro-service architecture, the micro-services are determined by the model and the internal service communication is handled via REST templates. The services in the original monolith arenow basically the internal service communication that is going on in between the business services. We chose to keep many of the same endpoints as the original monolith to reduce the necessary changes in the front end application Janus.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

1. Java JDK 1.8
2. Spring Tool Suite (STS)
3. Add ojdbc8 dependency to pom.xml 
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>12.2.0.1</version>
</dependency>

Link to download OJDBC8.jar if you don't have it. 
http://www.oracle.com/technetwork/database/features/jdbc/jdbc-ucp-122-3110062.html 

Run maven install goal Change Z:\Program Files\OJDBC\ojdbc8.jar to location of your jar. 

Maven install command below (Make sure you replace the filepath that is in between the quotes with the location of your ojdbc8.jar, remove the quotes, and then run the command):
mvn install:install-file -Dfile="Z:\Program Files\OJDBC\ojdbc8.jar" -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar


### Installing

Either:
- Download the ZIP for the repo and unzip it
- Clone the repo
Import the existing Maven project in STS
Run it as a Spring Boot App

## Running the tests

N/A

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Seth Maize** - *Code cleanup* - [sgm4789](https://github.com/sgm4789)
* **Johne Vang** - *Code cleanup* - [vangjk](https://github.com/vangjk)
* **Ricky Baker** - *Code Cleanup* - [riorbak](https://github.com/riorbak)
* **Zain Lateef** - *Code Cleanup* - [zainlateef](https://github.com/zainlateef)
* **John Talanian** - *Code Cleanup* - [talanianj](https://github.com/talanianj)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
