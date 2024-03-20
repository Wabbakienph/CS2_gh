PROCESSING CHESS POSITIONS

This is a program called ReadFEN.java that will load in a Forsyth-Edwards Notation (FEN) and convert it two a 2D ASCII (i.e. char)
representation. Each chess piece in FEN is represented by a single character as follows:
<br> p  n  b  r  q  k
<br> P  N  B  R  Q  K
<br> (pawn - knight - bishop - rook - queen - king)

The **objective** is to read in a file containing a single line in FEN notation and print a 2D char array chessboard. Here is an example output using the above
position:
<br> $ java ReadFEN valid3.txt
<br> rn...rk.
<br> pbppq.pp
<br> .p..pb..
<br> ....N..Q
<br> ...PN...
<br> ...B....
<br> PPP..PPP
<br> R...K..R

The FEN entered follows an expected format. Here are some rules while processing the input string:
<br>• There are 8 rows in the string 
<br>• Each row contains **8 squares worth of information** (either pieces or numbers indicating empty
squares)
<br>• Extraneous characters are not in the string (e.g., a z or 9 character are not valid)
<br>• Two or more number characters are not next to each other (e.g., 42 should have been written
as 6) <br>
<br> **ERROR HANDLING**
Any FEN that has an issue should result in your program printing “invalid - ” then describing the error (e.g., there are not 8 rows) and finally exit without printing a board.
