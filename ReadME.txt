ReadME
--------------------
Haoyu Wang  
-----------------
AI project
------------------
Reference

1.<<JAVA2 Game Design>>   publised by Tsinghua University Press
2.https://github.com/xirtam-ch/RandomMazeDesigner learn the prefect Maze generate method (only have one entry and one exit)
3.Moodle (DFS and some Resources)


GITHUB Link : https://github.com/G00330443/Heuristically-Informed-Fuzzy-Maze.git
---------------------------------------------------------------------------------------------------
What is this script?

It is a Maze Game that can random generate maze (prefect Maze) only have one entry and one exit .

People can control this hero in game use keyboard (by press up , down , left , right).

There also have many other characters in this maze  ,for example : Enemy , Hero , Wall(Mon , Tree) , Bomb,
Door(is not work now , it supposed be transform hero in a random location in map), Wepon.

Hero can attack enemy  ,kill enemy  and  take Bombs , wepons , Health ...

When the game is runnig , some game state will show bottom on the window ( Blood , wepon , Wall break tool , bomb).




---------------------------------------------------------------------------------------------------

 
How to play this game?

1. open AI_MAZE file to get the source .

2. put the source in eclipse and run it (through run start.java).

3. when the script is run, a game window will show on the screen and many characters (Bombs, walls, wepon , hero and some other character)

4. move hero use keyboard (up ,down , left ,right) and check next step , if next step is mountion or trees can not move .
But if next step is door , bomb , wepon hero can get those.

5. when u in this game , you can check the zoomout view by press "Z" and press "ENTER" to 
check the short path ( red ">" is tips of short path ) and  there also have some yellow points 
that show  spiders and red points show Health in the map.

6. When hero meet enemy (spider ) hero will fight with spider.
if hero's wepon up than 1 he will win this round and wepon decrease 1.
else hero's wepon less than 1 he will loose this round and blood decrease 1.

7. when the hero experience all blocks and reach the exit point at end .
you will win this game .
else if your blood reach 0 , you will loose game . 
and get fuzzylogic result 
Game OVer


---------------------------------------------------------------------------------------------------

Special character of this game

1. Create a prefect Maze(one entry and one exit )--Removal of a number of horizontal and vertical wall wall

2. Spider can move automatialty throuh check --Class enemy_walk.java 

3. Have wall_break tool can break a wall become road. 

4. Bomb can exposion and can destroy 8 cells nearby.



Try to use Node to create maze and other characters at first but meet some problems so give up it. 
 

