package testing;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by xi on 2016/3/31.
 */
public class SerializableVector implements ISerializableVector<IResetableSerializable> {

    private final int frameSize = 1000000;
    private final int recordPerFrame;
    private ArrayList<ByteBuffer> frames;
    private int recordSize;
    private int numOfRecords;
    private int lastPos; //last position in the last frame

    /**
     * constructor
     * default frameSize is the same as system setting
     *
     * @param recordSize
     */
    public SerializableVector(int recordSize) {
        frames = new ArrayList<>();
        this.recordSize = recordSize;
        recordPerFrame = frameSize / recordSize;
        numOfRecords = 0;
        lastPos = 0;
    }

    @Override
    public void get(int index, IResetableSerializable record) {
        if (index >= numOfRecords) {
            throw new IndexOutOfBoundsException("index: " + index + " current vector size: " + numOfRecords);
        }
        int frameIdx = getFrameIdx(index);
        int offsetInFrame = getOffsetInFrame(index);
        record.deserialize(frames.get(frameIdx).array(), offsetInFrame, recordSize);
    }

    @Override
    public void append(IResetableSerializable record) {
        if (numOfRecords % recordPerFrame == 0) { //add a new frame
            ByteBuffer frame = ByteBuffer.allocate(frameSize);
            record.serialize(frame.array(), 0);
            frames.add(frame);
            lastPos = recordSize;
        } else {
            int frameIdx = frames.size() - 1;
            record.serialize(frames.get(frameIdx).array(), lastPos);
            lastPos += recordSize;
        }
        numOfRecords++;
    }

    @Override
    public void set(int index, IResetableSerializable record) {
        if (index >= numOfRecords) {
            throw new IndexOutOfBoundsException("index: " + index + " current vector size: " + numOfRecords);
        }
        int frameIdx = getFrameIdx(index);
        int offsetInFrame = getOffsetInFrame(index);
        record.serialize(frames.get(frameIdx).array(), offsetInFrame);
    }

    @Override
    public void clear() {
        frames.clear();
        numOfRecords = 0;
        lastPos = 0;
    }

    @Override
    public int size() {
        return numOfRecords;
    }

    @Override
    public int getFrameCount() {
        return frames.size();
    }

    public static void sVectorCopy(SerializableVector src, int srcPos, SerializableVector dst, int dstPos, int len,
                                   IResetableSerializable tmpRecord) {

        for (int i = 0; i < len; i++) {
            src.get(srcPos + i, tmpRecord);
            dst.set(dstPos + i, tmpRecord);
        }
    }

    private int getFrameIdx(int index) {
        return index / recordPerFrame;
    }

    private int getOffsetInFrame(int index) {
        return (index % recordPerFrame) * recordSize;
    }
}