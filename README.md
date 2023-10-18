# Kalaha Game Project #

## Technology Stack ##
This project has both backend and frontend as a separate project.

IntelliJ IDEA is used as IDE for both of the projects.

For the backend, I use Spring Boot 3.1.4 and Java 17. As a build tool I use maven. As this project is small, I prefer not to use database.


## Project Architecture ##
For the backend, there are 6 packages:
+ builder: I use Builder Design Pattern for creating the board of the game including players and pits. So the files regrading to this are included in this package.
+ config: It includes the constant values for the game like player ids, pit starting ids belong to each player and so on.
+ controller: It includes files for communicating with the frontend. I also use mapperstruct for converting objects to dto objects in order to send them to frontend.
+ model: It includes domain model objects. In my design there are 4 objects:
    + BigPit
    + LittlePit
    + Board
    + Player
    + Pit (abstract class extended by BigPit and LittlePit)
+ service: It includes the logic classes of the project. I seperate them into 3:
    + Game is like a starting point of the behavior.
    + GameLogic is like a manager position in the game. It is responsible for starting sowing and determining winner.
    + Sowing is the main place for managing sowing behavior.
+ utils: It includes Turn class which keeps the state of the game as for the turn of the players. And it also includes the custom exception classes in the project.

In addition, Unit tests and Integration tests are written.
