/**
 * A class representing a key/value pair for use with 
 * HashMaps
 *
 * The key and the value instance variables are both public
 * You can directly access them (no need for get/set methods)
 */
public class Entry
{
    public Object key;
    public Object value;

    /**
     * Default cnstructor
     *
     * @param k the key
     * @param v the value
     */
    public Entry(Object k, Object v)
    {
	key = k;
	value = v;
    }

    /**
     * Copy constructor
     */
    public Entry(Entry e)
    {
	key = e.key;
	value = e.value;
    }

    /**
     * @return the key/value pair as a String in form of (key, value)
     */
    @Override
    public String toString()
    {
	return "(" + key.toString() + ", " + value.toString() + ")";
    }

}
