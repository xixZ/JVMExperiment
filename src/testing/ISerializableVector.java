package testing;

/**
 * Created by xi on 2016/3/31.
 */
public interface ISerializableVector<T> {

    /**
     * Get the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @param record the returned record will be to reset
     * Throw IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    void get(int index, T record);

    /**
     * Appends the specified element to the end of this list.
     * @param record the record to be added
     */
    void append(T record);


    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to update
     * @param record the new record
     * Throw IndexOutOfBoundsException if the index is out of range
     */
    void set(int index, T record);


    /**
     * Clears the vector
     */
    void clear();

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns the number of frames.
     *
     * @return the number of frames
     */
    int getFrameCount();
}