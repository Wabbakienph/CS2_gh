# _FLY AWAY HOME, MOSCA!_ GAME

In a land far far away, assuming a pond in the backyard, one lost little fly called Mosca just found a map home. Along its way lie hungry predators like frogs and spiders ready to hunt down and devour Mosca. "Draw" Mosca a way home on the map that also avoids such terrifying forces!
<br> SNEAK PEEK AT CHARACTERS IN THE GAME:
| Mosca        | Frog        | Spider      |
| ------------ | ----------- | ----------- | 
| <img src="Moscathefly.png" width="100" height="100" />    | <img src="frog.png" width="100" height="100" />   |<img src="spider.png" width="100" height="100" /> |


### How the world works:
Read and process a text file with the following format: the first line indicates the dimensions of the world (row x column), and the rest of the text lays out how the world is gonna look. Maybe there's no rectangular/square land with a pond but just pretend there was right now. Let's say A square/rectangle land suspended mid-air. The game characters are represented with 1 single uncapitalized letter: **s** for *start*, Mosca's starting position; **h** for *home* where Mosca needs to get; **f** for *frog*, which will likely come in multiple; **a** for *another predator*, in this case spiders, as if Mosca's livelihood is not already under imminent threat; finally, **.** for a blank square.
<br>**Example: a file like this**
<br>10 10 
<br>...f..a..f
<br>..........
<br>.f.......f
<br>....a.....
<br>........h.
<br>a.........
<br>..........
<br>..........
<br>....s.....
<br>..........
<br>will churn out 
<br><img src="example.png" width="400" height="400" />

NO CHARACTER SHOULD MOVE **OFF GRID** OR ELSE THEY WILL FALL DOWN INTO THE ABYSS (again nothing down there!).

### How Mosca moves:
The user controls Mosca's movements by arrow keys (i.e Mosca can go N/S/E/W) one space at a time. Every time Mosca moves (ie. the user moves), all present predators should move.

### How frogs operate:
Frogs are allowed to move North/South/East/West 1 space at a time. Frogs **randomly** move and can only EAT the fly only if they are
on the same square or an immediately adjacent square (above/ below/ left/ right)No diagonals! It's weird like that. Maybe frogs in this game can only turn 90 degrees and stretch their fly-slurping tongue to the next square at best. <br> **Side note**: Can't move off-grid as indicated above and can't be in the same square with another predator (fellow frogs or spiders).

### How spider operate:
Spiders are almost like frogs (1 space a time, respect other predators, no off-grid) except they are more cunning. They randomly move **CLOSER** to Mosca. If a spider and Mosca are on the same row and column, and Mosca tries to move within the same column/row, the spider will move 1 space closer. If Mosca and a spider are on a different row/col now, the spider has 2 options: move CLOSER horizontally or vertically. Whether it will move UP or DOWN or RIGHT or LEFT depends on its current relative position to the fly (above or below? further left or further right?).

### GOAL: Get Mosca home in one piece!