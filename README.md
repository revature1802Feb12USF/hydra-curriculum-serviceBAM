# hydra-curriculum-service
The curriculum micro-service is crucial to our design as well as the original monoliths. In the monolith, the curriculum model contained information from curriculum service and the topic service. Each of those services retrieved data from their respective repositories for the curriculum model. When converting the monolith to a micro-service architecture, the micro-services are determined by the model and the internal service communication is handled via REST templates. The services in the original monolith arenow basically the internal service communication that is going on in between the business services. We chose to keep many of the same endpoints as the original monolith to reduce the necessary changes in the front end application Janus.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java JDK 1.8
Spring Tool Suite (STS)

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
* **John Talanian** - *Code Cleanup* - [talanianj](https://github.com/talanianj)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
