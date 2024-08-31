import java.util.ArrayList;

public class ObjectHashMap extends AbstractHashMap{
    private ArrayList<Entry>[] table;

    public ObjectHashMap(double maxLoad) {
        super(maxLoad);
        table = new ArrayList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<Entry>();
        }
    }

    // O(n)
    @Override
    public void put(Object key, Object value) {
        int idx = hash(key); // find the hash value of the key

    // The chain at idx is EMPTY = the entry is added to the top
        if (table[idx].isEmpty()){
            table[idx].add(0, new Entry(key, value));
            numKeys++; // update # of key-value pairs: +1 
        } 
    // The chain is created --> 2 cases: key already added - key not found
        else {
            boolean found = false;
            // Search through the Entry ArrayList for existing keys
            for (Entry entry : table[idx]){
                if (entry.key.equals(key)){
                    entry.value = value;
                    found = true;
                    break;
                }
            }

            if (found == false) {
                table[idx].add(new Entry(key, value));
                numKeys++;
            }
        }

        // When the ratio of keys/available spaces exceeds the maxLoad threshold
        if ((double) size() / capacity > maxLoad) {
            resize();
        }
    }

    // O(n)
    @Override
    public Object find(Object key) {
        int index = hash(key); // find the hash value of the key
        ArrayList<Entry> bucket = table[index];

        if (bucket.isEmpty()) {
            return null;
        } 

        int keyCheck = 0; // loop through th
        while (keyCheck < bucket.size()) {
            if (bucket.get(keyCheck).key.equals(key)){
                return bucket.get(keyCheck).value;
            }
            keyCheck++;
        }
        
        return null; // if key is not found in the bucket
    }

    // 
    @Override
    protected void resize() {
        int newCapacity = capacity * 4;
        ArrayList<Entry>[] newTable = new ArrayList[newCapacity]; 

        // initializing: fill the newTable with empty array lists of entry
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new ArrayList<Entry>();   
        }

        // Copy all rehashed keys and corresponding values from the old 
        // table into their correct positions in newTable
        for (ArrayList<Entry> bucket : table) {
            if (!bucket.isEmpty()){
                for (Entry entry : bucket) {
                    // Calculate the new index for each entry
                    int newIdx = (entry.key.hashCode() % newCapacity + newCapacity) % newCapacity; 
                    newTable[newIdx].add(entry); // Add the entry to the corresponding bucket in the new table
                }
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    @Override
    public boolean containsKey(Object key) {
        return find(key) != null;
    }

    @Override
    public Entry[] getEntries() {
        Entry[] allEntries = new Entry[numKeys];
        int nextOpenIndex = 0; // keeps track of the next open index in allEntries[]
        for (int i = 0; i < table.length; i++){
            if (!table[i].isEmpty()) {
                for (int j = 0; j < table[i].size(); j++) {
                    allEntries[nextOpenIndex] = table[i].get(j);
                    nextOpenIndex++;
                }
            }
        }
        return allEntries;
    }
    
    public static void main(String[] args) {
        ObjectHashMap table = new ObjectHashMap(.9);
        table.put("hello", 1);
        table.put("hi", 2);
        table.put("hi", 1);
        table.put("howdy", 1);
        table.put("what up", 5);
        table.put("wassup", 3);
        for(int i = 0; i < 15; i++){
            table.put("A"+i,i);
        }
        for (Entry entry : table.getEntries()){
            System.out.print(entry.toString() + " ");
        }
        System.out.println();
        System.out.println(table.capacity);
    }
}
