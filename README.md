# Quiz-app-Backend: Hubro
This project serves as the backend for a full-stack application. This is a part of a project of IDATT2105 Full-stack application development at NTNU.

### Team: Ramtin Forouzandehjoo Samavat, Tobias Skipevåg Oftedal, Jeffrey Yaw Annor Tabiri

## Table of Contents 
- [Overview](#overview)
- [Wiki](#wiki)
- [Features](#features)
- [Setup and Run](#setup-and-run)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Overview
The Semester Project is a full-stack web application aiming to provide a comprehensive and user-friendly platform for creating, managing, and taking quizzes for educational, training, or entertainment purposes. The project utilizes the following technologies:

Frontend: Vue.js
Backend: Java 21 with Spring Boot V3
Database: MySQL for runtime and H2 for tests

## Wiki
These are system documents which descirves the various aspects of a software system. These documents capture the details of the system's design, implementation, operation and maintenance, serving as a reference for developers.
- [System Architecture Program](docs/system-architect-diagram.png)
- [Class Diagram](docs/getQuizDiagram.png)
- [Sequence diagram for getting quiz](docs/getQuizDiagram.png)
- [Sequence diagram for login user](docs/loginDiagram.png)

## Features
* Users of the program can log in securely, register themselves and manage their accounts.
* Authenticated users can create quizzes by adding questions and specifying categories.
* Users can tag questions with keywords and categorize them into topics or subjects.
* Users can search for quzzes and filter them based on their fields.
* Allow users to add, edit, delete, organize, and manage their quiz.
* Allows users to gain score on every quiz and track their progress
* Allow users to come if there where to be any problems by writing in our feedback channel.

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
   This command will start the database required for the backend to function.
   From here on out you can send request to to backend.

## Test
To run the tests, you would use the command
   ```
   mvn test
   ```
This command will execute all the tests in the project.


## License
This project is licensed under the MIT License.

## Acknowledgements
Special thanks to the subject teachers for creating this assignment and providing us with the opportunity to develop this project.
