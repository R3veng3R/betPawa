### REQUIREMENTS:
1. Gradle
2. Node.js / NPM
3. MySQL 5.7


### INSTALLING:
git clone https://github.com/R3veng3R/betPawa.git<br>
cd betPawa

#### 1. Setup DB:
1. cd database.
2. You will see a diagram and a database script.
3. Import database.sql to your local MySQL DB.
4. Setup DB connection under: <br>
    backend/src/main/resources/application.properties <br>
    Or use an existing one.
    
5. Make sure DB is accessible and ready to receive requests.


#### 2. Setup backend:
1. cd backend
2. On UNIX system use: ./gradlew bootRun <br>
   On WINDOWS use: gradlew bootRun
   
   (in case of an error double check DB connectivity. <br>
   <u>The system won't run if DB is not accessible</u>)
   
3. backend is using 9000 port (you can change it under application.properties)
   
   
#### 3. Setup frontend:
1. cd frontend
2. npm install
3. npm run dev
4. frontend should be accessible under http://localhost:8080/
5. <b>NB</b>: You can also try: <br/> 
<i>npm run test</i> - for frontend tests, but there are not so many of them

You are ready to test the project on your local machine.

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The Framework used
* [Gradle](https://gradle.org/) - Gradle
* [Hibernate](https://hibernate.org/) - DB connection
* [Project Lombok](https://projectlombok.org/) - for getters/setters and log
* [MapStruct](http://mapstruct.org/) - for mapping DTOs with Entities
