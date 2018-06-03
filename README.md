# LPOO_TETRWEED

---- DEVELOPMENT
  UML present in the git repository.
  The only design pattern that was used in the development of our project was singleton. This design pattern was used regarding the Tetris class and the GameState class, meaning that from each could only exist one object.
  The major difficulty we encountered during the development of our project was setting up the server in order to allow not only highscores to be stored as well as implementing a real time multi player mode. We had this second objective in mind when completing the first check-up but as we saw no progress in our work, the decision was made to change this objective to the physics one as this change would allow us to meet our deadline.
  The work done in this project was divided equally and in most of the occasions both members worked at the same times in order to provide immediate help if needed. Regarding who did what, the tasks that needed to be completed were distributed in the moment so there are no specific distinct divisions in the project. We estimate to have spent approximately 130 hours each in this project.

---- USER MANUAL
  The setup screen is as follows:  
![Link to game setup screenshot](https://github.com/MariaTeresaFerreira/LPOO_TETRIS/blob/master/Game%20Setup.png)
  Here the player can select the game mode, and the end mode from classic, Kray-Z Blox, and escape the matrix and sprint, marathon, and time, respectively. The single player and multiplayer options, as was said, were not implemented in time of the deadline.

  Regarding the game screen itself there are some differences between the desktop and android options.
  The commands are these (desktop/android):
  - Move right or left: right or left arrows/tilt right or left (accelerometer)
  - Down: down arrow / tilt down (accelerometer)
  - Hard drop: space / 'D' button
  - Rotate: up arrow /'R' button
  - Hold: shift right or shift left / 'H' button
![Link to Android Game screenshot](https://github.com/MariaTeresaFerreira/LPOO_TETRIS/blob/master/Android%20Game.png)
![Link to Desktop Game screenshot](https://github.com/MariaTeresaFerreira/LPOO_TETRIS/blob/master/Desktop%20Game.png)

  In the Kray-Z Blox mode there are some blocks marked with a '?' that represent special effects when destroyed:
  - speed up for ten seconds
  - slow down for ten seconds
  - block rotation for 10 seconds
  - the following five blocks are the same one
  - all the already placed blocks shift to the left
  
  The escape the matrix uses the physics module to move the player through the playing field.
  
  The game over screens displays the score and the available buttons as follows:  
![Link to Game Over screenshot](https://github.com/MariaTeresaFerreira/LPOO_TETRIS/blob/master/Game%20Over%20Screen.png)
