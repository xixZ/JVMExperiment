package testing;

/**
 * Created by xi on 2016/3/31.
 */
class TPointer implements IResetableSerializable<TPointer>{
    int frameID;
    int tupleStart;
    int tupleEnd;
    int normalKey;

    public TPointer(int frameID, int tupleStart, int tupleEnd, int normalKey){
        this.frameID = frameID;
        this.tupleStart = tupleStart;
        this.tupleEnd = tupleEnd;
        this.normalKey = normalKey;
    }
    public TPointer(){}
    @Override
    public void reset(TPointer other) {
        frameID = other.frameID;
        tupleStart = other.tupleStart;
        tupleEnd = other.tupleEnd;
        normalKey = other.normalKey;
    }

    public void reset(int frameID, int tupleStart, int tupleEnd, int normalKey){
        this.frameID = frameID;
        this.tupleStart = tupleStart;
        this.tupleEnd = tupleEnd;
        this.normalKey = normalKey;
    }

    @Override
    public void serialize(byte[] bytes, int offset){
        writeInt(bytes, offset, frameID);
        writeInt(bytes, offset + 4, tupleStart);
        writeInt(bytes, offset + 8, tupleEnd);
        writeInt(bytes, offset + 12, normalKey);
    }

    @Override
    public void deserialize(byte[] bytes, int offset, int length){
        frameID = readInt(bytes, offset);
        tupleStart = readInt(bytes, offset + 4);
        tupleEnd = readInt(bytes, offset + 8);
        normalKey = readInt(bytes, offset + 12);
    }

    //write int value to bytes[offset] ~ bytes[offset+3]
    void writeInt(byte[] bytes, int offset, int value) {
        int byteIdx = offset;
        bytes[byteIdx++] = (byte) (value >> 24);
        bytes[byteIdx++] = (byte) (value >> 16);
        bytes[byteIdx++] = (byte) (value >> 8);
        bytes[byteIdx] = (byte) (value);
    }

    int readInt(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xff) << 24) + ((bytes[offset + 1] & 0xff) << 16) + ((bytes[offset + 2] & 0xff) << 8)
                + ((bytes[offset + 3] & 0xff) << 0);
    }
    public void print() {
        System.out.println(frameID + " " + tupleStart + " " + tupleEnd + " " + normalKey);
    }
}
