import java.io.File;
import java.util.Scanner;

// Read a single line from a file, which contains the FEN notation representing a chess position.
// Validate the FEN notation according to specific rules, including:
    // Ensuring there are 8 rows in the string.
    // Verifying that each row contains exactly 8 squares of information.
    // Checking for extraneous characters and invalid characters.
    // Ensuring that consecutive number characters are not next to each other.
// If the FEN notation is valid, convert it into a 2D ASCII representation of the chessboard.
// Print the 2D ASCII representation of the chessboard to the screen.
// If the FEN notation is invalid, print an error message describing the issue and exit without printing the board.
// The program should handle both valid and invalid FEN notations as described in the prompt, 
// providing appropriate error messages for invalid input. 
// Additionally, it should use a StringBuilder to build the output representation of the chessboard one character at a time,
// allowing for easy printing and error handling.

public class ReadFEN {
    public static void main(String[] args) {
        // Check if at least 1 argument is provided
        if (args.length == 0) {
            System.out.println("invalid - no input detected. To run: java ReadFEN <input_file>");
            return;
        }

        // Read the input FEN string from the file specified in the command-line argument
        String inputFilePath = args[0];

        try {
            File inputFile = new File(inputFilePath);
            Scanner scanner = new Scanner(inputFile);
            StringBuilder inputFENBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                inputFENBuilder.append(scanner.nextLine()).append("\n");
            }

            String inputFEN = inputFENBuilder.toString().trim();
            
            if (validFEN(inputFEN)) {
                char[][] chessboard = convertFENtoChessboard(inputFEN);
                for (int i = 0; i < chessboard.length; i++) {
                    for (int j = 0; j < chessboard[0].length; j++) {
                        System.out.print(chessboard[i][j] + "");
                    }
                    System.out.println();
                } 
            } 
              
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the input file: " + e.getMessage());
        }
    }

    public static boolean validFEN(String fen) {
        if (!fen.contains("/")) {
            System.out.println("Invalid format");
            return false;
        }

        String[] rows = fen.split("/");
        
        int rowAcc = 0;// count rows

        // Check each row for valid characters and lengths
         for (String row : rows) {
            if (!row.trim().isEmpty()) { // handling the "" after the trailing /
                rowAcc++;
                // Checking each character in the row
                char[] fenRow = row.toCharArray();

                // check if each row contains exactly 8 squares of information.
                if (!checkRow(fenRow)) {
                    System.out.println("Invalid - There should be 8 pieces of information in a row.");
                    return false;
                }

                for (int i = 1; i < fenRow.length; i++) {
                    // Check if the character is a valid chess piece or a number 
                    if (!Character.toString(fenRow[i]).matches("[pnbrqkPNBRQK1-8]")) {
                        System.out.println("Invalid info - Should be either a chess piece or a number");
                        return false;
                    }  else { // if it's, check to ensure 2 or more consecutive numbers are not next to each other
                        if (Character.isDigit(fenRow[i-1]) && Character.isDigit(fenRow[i])) {
                            System.out.println("Invalid - Cannot have 2/more consecutive numbers ");
                            return false;
                        }
                    }
                }
            }
        }
        // Check if there are exactly 8 rows
        if (rowAcc != 8) {
            System.out.println("Invalid - There should be 8 rows.");
            return false;
        }

        return true;  
    }

    public static boolean checkRow(char[] row) {
        int acc = 0;
        for (char x : row) {
            if (Character.isDigit(x)) {
                acc += Character.getNumericValue(x);
            } else if ("pnbrqkPNBRQK".indexOf(x) != -1) {
                acc++;
            }
        }
        return acc == 8;
    }

    // Check if each row has exactly 8 pieces of information
    // and if each piece is valid ('p', 'n', 'b', 'r', 'q', 'k') or a number (1-8)
    public static char[][] convertFENtoChessboard(String fen) {
        int currentRow = 0;
        int numSquares = 0;
        char[][] chessboard = new char[8][8]; // a chessboard template of fixed dimensions

        for (int i = 0; i < fen.length(); i++) {
            char square = fen.charAt(i);

            if ("pnbrqkPNBRQK".indexOf(square) != -1) {
                chessboard[currentRow][numSquares] = square;
                numSquares++; // until it reaches the maximum 8 squares
            } else if (Character.isDigit(square)) { 
                // A digit represents an empty square
                for (int j = 0; j < Character.getNumericValue(square); j++) {
                    chessboard[currentRow][numSquares] = '.';
                    numSquares++;}
            } else if (square == '/') {
                // '/' indicates the start of a new row
                currentRow++;
                numSquares = 0; // new row so reset num of squares in a row to 0 and continue filling that row
            }
        }
        return chessboard;
    }
} 
