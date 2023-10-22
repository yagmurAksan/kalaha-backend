# Kalaha Game #

This application implements Kalaha Game. In the game, there are 2 players. Each of the two players has his six pits in front of him. To the right of the six pits,
each player has a big pit. At the start of the game, there are six stones in each of the six little pits and there is no stones in the big pits.

This project has both backend and frontend implementations as a separate projects. The repositories are provided in [backend](https://github.com/yagmurAksan/kalaha-backend) and [frontend](https://github.com/yagmurAksan/kalaha-frontend) repositories.


## Technology Stack ##

- `IntelliJ IDEA` is used as IDE for the implementation.
- For the backend, I use `Spring Boot 3.1.4` and `Java 17`. 
- As a build tool I use `Maven`. 
- For generating constructors, getter and setter methods, `Spring Boot Lombok` library is used.
- `MapStruct` library is used to simplify data transfer between classes.
- `springdoc-openapi` library is used for API documentation
- As this project is small, I prefer not to use database.


## Project Architecture ##

For the backend, there are 6 packages:
- builder: I use Builder Design Pattern for creating the board of the game including players and pits. The files regarding this implementation are included in this package.
- controller: It includes files for communicating with the frontend. 
I use RestAPI for the communication. There are 2 endpoints:
  - /startGame - Creates a new game and returns a GameResponseDTO as a json format.
  - /makeMove - Expects a pit id as a path variable and makes the move on the id and returns a GameResponseDTO as a json format.

  I also use mapper-struct for converting objects to dto objects in order to send them to frontend.

- model: It includes model objects. In my design there are 4 objects:
    - `BigPit`
    - `LittlePit`
    - `Board`
    - `Player`
    - `Pit` (abstract class extended by BigPit and LittlePit)
- service: It includes the logic classes of the project. I seperate them into 2:
    - `GameService` is responsible for starting the game. It creates the required objects for the game to start.
    - `SowingService` is the main place for managing sowing behavior.
- constant: It includes the GameConstants class with constant values for the game like player ids, pit starting ids belong to each player and so on.
- exception: It includes the custom exception classes in the project.

In addition, Unit tests and Integration tests are implemented in the test package.


## How to run ##

In order to run the project, Java 17 and Maven must be installed on the computer.

When you run the server, you can access the Game via Swagger at this address: http://localhost:8080/swagger-ui/index.html

In order to reach the UI, you must also run the frontend project according to the instructions in README file provided in that project.
