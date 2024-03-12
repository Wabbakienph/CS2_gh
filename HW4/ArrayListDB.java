import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayListDB {
    public static void main(String[] args) {
        try {
            // Check if any command-line argument is provided
            if (args.length == 0) {
                System.out.println("How to run the program: java AlbumListDB <input_file>");
                return; // get out of program
            }

            String fileName = args[0]; // get the file's name from command-line arg
            File inputFile = new File(fileName);
            Scanner scan = new Scanner(inputFile);

            AlbumArrayList albumList = new AlbumArrayList();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.indexOf(":") != -1 && line.indexOf(" - ") != -1) {
                    String command_album[] = line.trim().split(":");
                    Album album = new Album(command_album[1]);

                    if (command_album[0].equals("ADD")) {
                        albumList.add(album);
                    } else if (command_album[0].equals("REMOVE")) {
                        if (albumList.contains(album)) {
                            albumList.remove(album);
                        } else {
                            System.out.println("Album " + album + " is not found.");
                        }
                    }     
                }
            }

            // Create a scanner for user input
            Scanner input = new Scanner(System.in);
            System.out.println("Instructions:");
            System.out.println("You can interact with the Album Management Software" + 
                "by typing the following commands: ADD, REMOVE, LIST, QUIT");
            boolean quit = false; // default interaction status

            while (!quit) {
                String command = input.nextLine().strip();
                if (command.equals("ADD")) {
                    try {
                        String inputAlbum = input.nextLine();
                        Album newA = new Album(inputAlbum);
                        albumList.add(newA);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid album input format: Artist - Album Name");
                    }
                } else if (command.equals("REMOVE")) {
                    try {
                        int inputIdx = Integer.parseInt(input.nextLine());
                        // the user assumes that the index is the actual order of the album in the current list
                        albumList.remove(inputIdx - 1); 
                    } catch (Exception e) {
                        System.out.println("Integer not found");
                    }
                } else if (command.equals("LIST")) {
                    if (albumList.numItems == 0) {
                        System.out.println("0 albums");
                    } else {
                        for (int i = 0; i < albumList.numItems; i++) {
                            System.out.println(i+1 + ". " + albumList.get(i));
                        }
                    }
                } else if (command.equals("QUIT")) {
                    quit = true;
                } else {
                    System.out.println("Invalid command. You can only input ADD, REMOVE, LIST, or QUIT");
                }
            }  
            input.close();
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
