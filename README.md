# Project documentation

## Which tools do you need for this project?

1. IDE: IntelliJ Idea
2. Docker Desktop: Creating temporary database for integration tests
3. Postman: Access to the backend API
4. MongoDB Compass: Access to the database
5. Apache Maven: Install backend libraries/frameworks
6. npm: Install frontend libraries/frameworks

## How to start the project locally

1. Download the project with the "develop" branch
2. Open the project in IntelliJ Idea

### How to start the application

- You have to start the backend and frontend part

#### Start the backend part

1. Start the terminal in the root directory "my-own-ratings"
2. Execute the command: "mvn spring-boot:run"
3. If you have problems you can try to execute the following command: "mvn clean install"
4. At "http://localhost:1234" you can access to the backend.
   - You can adapt the port in the file "application-dev.properties" which is located in the folder 
   "my-own-ratings\src\main\resources"
5. You can access to the database with "mongodb://localhost:27017"
   - You can adapt the port in the file "application-dev.properties"

#### Start the frontend part

1. Start the terminal and go to the folder "my-own-ratings/frontend"
2. For the first time execute the command: "npm install"
3. Then execute the command: "npm start"
   - For the first time it takes some time to start the frontend part
4. At "http://localhost:3333" you can access the website.
   - In the file ".env.development" in the folder "my-own-ratings\frontend" you can adapt the port.
   - In the file "App.tsx" in the folder "my-own-ratings\frontend\src\ts" you can find the website routing.

### How to execute the tests?

1. Go to the folder in "my-own-ratings\src\test\java\en\ratings\own\my\test"
2. Start the tests.

#### For integration tests

1. Start "Docker Desktop"
2. Start any integration test
   - For the first it takes some time because the program downloads a MongoDB docker image.
