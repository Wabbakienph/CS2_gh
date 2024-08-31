import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestObjectHashMap {
    //expected, actual

    //----isEmpty()/empty hash----
    @Test
    public void mapStartsEmpty(){
        ObjectHashMap m = new ObjectHashMap(.9);
        assertTrue("HashMap should start empty",m.isEmpty());
    }
    @Test
    public void mapInitialSizeZero(){
        ObjectHashMap m = new ObjectHashMap(.9);
        assertEquals("HashMap should stary at size 0",0,m.size());
    }
    
    //----put()----
    @Test
    public void checkPut_one(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("word",1);
        assertEquals(1,m.size());
    }
    @Test
    public void mapIsNotEmpty(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("1",1);
        assertFalse(m.isEmpty());
    }
    @Test
    public void checkPut_multiple(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put(""+i,i);
        }
        assertEquals("Additions of unique words should increase number of items in HashMap",10, m.size());
    }
    @Test
    public void noRepeats(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put("car",i+1);
        }
        assertEquals("Repeated instances of a single word shouldn't increase number of items in HashMap",1,m.size());
    }

    //----getEntries()----
    @Test
    public void checkGetEntriesSize(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put(""+i,i);
        }
        Entry[] check = m.getEntries();
        assertEquals("Length of list returned from getEntries() should match the amount of unique words in the HashMap",10,check.length);
    }
    @Test
    public void noNullsInEmptyHash(){
        ObjectHashMap m = new ObjectHashMap(.9);
        Entry[] check = m.getEntries();
        assertEquals("Length of list returned from getEntries() should match the amount of unique words in the HashMap",0,check.length);
    }
    @Test
    public void noNullsLoadedHash(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put(""+i,i);
        }
        Entry[] check = m.getEntries();
        for(int i = 0; i < check.length; i++){
            assertFalse("List returned from getEntries() shouldn't contain any nulls",check[i]==null);
        }
    }
    
    //----resize()----
    @Test
    public void checkResize(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 100; i++){
            m.put(""+i,i);
        }
        assertEquals("HashMap should properly resize",100,m.size());
    }

    //----containsKey()----
    @Test
    public void checkContainsKey(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("1",1);
        assertTrue("containsKey() shouldn't ignore present key",m.containsKey("1"));
    }
    @Test
    public void checkContainsKey_empty(){
        ObjectHashMap m = new ObjectHashMap(.9);
        assertFalse("containsKey() should only return true if the key is present (map is empty)",m.containsKey("1"));
    }
    @Test
    public void checkContainsKey_notPresent(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("word",1);
        assertFalse("containsKey() should only return true if the key is present",m.containsKey("1"));
    }

    //----find()----
    @Test
    public void checkFind_present(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("word",1);
        assertEquals("find() should return the value paired with the given key",1,m.find("word"));
    }
    @Test
    public void checkFind_empty(){
        ObjectHashMap m = new ObjectHashMap(.9);
        assertEquals("find() should return null if given key isn't present in HashMap (map is empty)",null, m.find("cat"));
    }
    @Test
    public void checkFind_notPresent(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("word",1);
        assertEquals("find() should return null if given key isn't present in HashMap",null, m.find("car"));
    }
    @Test
    public void checkFindLoadedMap(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put(""+i,i);
        }
        for(int i = 0; i < 10; i++){
            assertTrue("find() should return occurance count when key is present in HashMap",m.find(""+i) != null);
        }
    }
    @Test
    public void checkGetBeyondSizeOfMap(){
        ObjectHashMap m = new ObjectHashMap(.9);
        for(int i = 0; i < 10; i++){
            m.put(""+i,i);
        }
        for(int i = 11; i < 25; i++){
            assertTrue("find() should return null if key is not present in HashMap",m.find(""+i) == null);
        }
    }

}
