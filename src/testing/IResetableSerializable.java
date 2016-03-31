package testing;

/**
 * Created by xi on 2016/3/31.
 */
public interface IResetableSerializable<T> extends IResetable<T> {
    /**
     * Serialize data into bytes starting from offset
     *
     * @param bytes
     *            a byte array to serialize data into
     * @param offset
     *            starting offset
     */
    void serialize(byte[] bytes, int offset);

    /**
     * Deserialize data from bytes starting from offset
     *
     * @param bytes
     *            source data byte array
     * @param offset
     *            starting offset to read from
     * @param length
     *            the length of a record
     */
    void deserialize(byte[] bytes, int offset, int length);
}
