public class AlbumArrayList extends AlbumList {
    /* AlbumList.java properties
     * protected int numItems;
     * public int size(){
		return numItems;
	}
     */
    private Album[] albumList;

    public AlbumArrayList() {
        albumList = new Album[5];  
    }

    // Create a  with length of user's choice
    public AlbumArrayList(int i) {
        albumList = new Album[i];  
    }

    @Override
    public void add(Album newA) {
        if (numItems == albumList.length) {
            System.out.println("Resized at index: " + numItems);

            // make a bigger Album List when the current one is full
            Album[] newList = new Album[albumList.length * 2];

            // copy content over 
            for (int i = 0; i < albumList.length; i++) {
                newList[i] = albumList[i];
            }

            // set the bigger list as the current list
            albumList = newList;
        }

        albumList[numItems] = newA;
        numItems++;
    }

    @Override
    public Album remove(Album targetA) {

        for (int i = 0; i < numItems; i++) {
            if (albumList[i].equals(targetA)) {
                Album removed = albumList[i];

                // Shift subsequent elements to the left to fill the gap
                // This loop ensures againts IndexOutOfBoundException
                for (int j = i; j < numItems - 1; j++) {
                    albumList[j] = albumList[j+1];
                }
        /* albumList[numItems - 1] = null; setting last element = null*/      
                
                numItems--;

                return removed;
            }

        }
        
        return null; // if the target album doesn't even exist in the array list
    }

    @Override
    public Album remove(int idx) {
        if (!checkIndex(idx, numItems)) { 
            return null;
        }

        Album removed = albumList[idx];

        // Shift subsequent elements to the left
        for (int i = idx; i < numItems - 1; i++) {
            albumList[i] = albumList[i+1];
        }

        numItems--;

        return removed;
    }

    @Override
    public Album get(int idx) {
        if (checkIndex(idx, numItems)) {
            return albumList[idx];
        }
        return null; // if index invalid 
    }

    @Override
    public void set(int idx, Album newA) throws IndexOutOfBoundsException {
        if (checkIndex(idx, numItems)) {
        albumList[idx] = newA;
        }
    }
    
    // utility method
    /* Checks whether the given index is in the range [0, −1] */
    protected boolean checkIndex(int i, int n) {
        if (i < 0 || i >= n) {
            return false;
        }
        return true;
    }

    public boolean contains(Album album) {
        for (int i = 0; i < numItems; i++) {
            if (albumList[i].equals(album)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(albumList[i] + " ");
        }
    }
}
