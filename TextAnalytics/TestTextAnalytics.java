import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class TestTextAnalytics {

    public Entry word = new Entry("word",7);
    public Entry car = new Entry("car",3);
    public Entry job = new Entry("job",1);
    public Entry sandwich = new Entry("sandwich",2);
    public Entry cube = new Entry("cube",15);
    public Entry candy = new Entry("candy",6);
    public Entry dog = new Entry("dog",15);
    public Entry[] checkSort = {cube, word, candy, car, sandwich, job};
    public Entry[] checkSortIncreasing = {job, sandwich, car, candy, word, cube};
    
    public ObjectHashMap makeList(){
        ObjectHashMap m = new ObjectHashMap(.9);
        m.put("word",7);
        m.put("car",3);
        m.put("job",1);
        m.put("sandwich",2);
        m.put("cube",15);
        m.put("candy",6);
        return m;
    }


    @Test
    public void checkSorted(){
        ObjectHashMap m = makeList();
        TextAnalytics t = new TextAnalytics();
        Entry[] actual = m.getEntries();
        t.insertionSort(actual);

        try{
            assertArrayEquals("Improper sort result", checkSort, actual);
        } catch (AssertionError ae){
            assertArrayEquals("Improper sort result", checkSortIncreasing, actual);
        }
    }
}
