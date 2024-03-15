PROCESSING CHESS POSITIONs

This is a program called ReadFEN.java that will load in a Forsyth-Edwards Notation (FEN) and convert it two a 2D ASCII (i.e. char)
representation. Each chess piece in FEN is represented by a single character as follows:
<br> p  n  b  r  q  k
<br> P  N  B  R  Q  K
<br> (pawn - knight - bishop - rook - queen - king)

The objective is to read in a file containing a single line in FEN notation and print a 2D char array chessboard. Here is an example output using the above
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

$ java ReadFEN invalid2.txt
invalid - Expected more information before row terminator ’/’
$ java ReadFEN invalid4.txt
invalid - Invalid character 9 in FEN.
You must ensure that the FEN entered follows the expected format. Here are some rules that you should check while processing the string:
• There are 8 rows in the string
• Each row contains 8 squares worth of information (either pieces or numbers indicating empty
squares)
• Extraneous characters are not in the string (e.g., a z or 9 character are not valid)
• Two or more number characters are not next to each other (e.g., 42 should have been written
as 6)
Any FEN that has an issue should result in your program printing “invalid - ” then describing
the error (e.g., there are not 8 rows) and finally exit without printing a board. I have provided
several good and bad FENs on the Canvas to test against. Make sure your program runs successfully against all of these test cases before submission. Hint: Use a StringBuilder to build up
your output one character at a time and only print it at the end. This way, if part of the input is
invalid, you can print the error without printing any of the board.
