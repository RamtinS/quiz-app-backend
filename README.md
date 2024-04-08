# Quiz-app-Backend: Hubro

Hubro is a full-stack application developed with Spring Boot and Vue.js.
The application is developed as part of an assessment in the subject 
IDATT2105 Full-stack application development at NTNU.

## Team
- Ramtin Forouzandehjoo Samavat
- Tobias Skipevåg Oftedal
- Jeffrey Yaw Annor Tabiri

## Table of Contents 
- [Overview](#overview)
- [Wiki](#wiki)
- [Features](#features)
- [Setup and Run](#setup-and-run)
- [Acknowledgements](#acknowledgements)

## Overview
The semester project is a full-stack web application aiming to provide a comprehensive and user-friendly platform for creating, managing, and taking quizzes for educational, training, or entertainment purposes. 

The project utilizes the following technologies:
- Frontend: Vue.js with Node.js.
- Backend: Spring Boot V3 with Java 21 and Maven.
- Database: MySQL V8 for runtime and H2 for tests.

## Wiki
These are system documents which descirves the various aspects of a software system. These documents capture the details of the system's design, implementation, operation and maintenance, serving as a reference for developers.
- [System Architecture Program](docs/system-architect-diagram.png)
- [Class Diagram](docs/Class-Diagram.png)
- [Sequence diagram for getting quiz](docs/getQuizDiagram.png)
- [Sequence diagram for login user](docs/loginDiagram.png)

## Features
- **Secure Authentication**: Users can securely log in, register, and manage their accounts.
- **Quiz Creation**: Authenticated users can create quizzes by adding questions and specifying categories.
- **Question Tagging**: Users can tag questions with keywords and categorize them into topics or subjects.
- **Advanced Search**: Users can search for quizzes and filter them based on various criteria.
- **Quiz Management**: Users have full control over their quizzes, including adding, editing, deleting, and organizing them.
- **Progress Tracking**: Users can track their progress and scores for each quiz they take.
- **Feedback Channel**: Users can easily reach out to us with any issues or feedback through our feedback channel.

## Setup and Run

### Prerequisites
 - JDK 21
 - Maven installed on device
 - A maven management tool
 - Docker

### Set up and run the backend.

1. Clone the repository
   ```
   git clone git@github.com:RamtinS/quiz-app-backend.git
   ```

2. Start the database.
   ```
   docker compose up
   ```
   This command will start the database required for the backend to function.

3. Start the backend-application
   ```
   mvn run
   ```
   From here on out you can send request to to backend.

## Test
To run the tests, you would use the command
   ```
   mvn test
   ```
This command will execute all the tests in the project.

## Acknowledgements
Special thanks to the subject teachers for creating this assignment and providing us with the opportunity to develop this project.
