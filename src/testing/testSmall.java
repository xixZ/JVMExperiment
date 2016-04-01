package testing;

/**
 * Created by xi on 2016/3/31.
 */
public class testSmall {

    public static void main(String []args){
        SerializableVector sv = new SerializableVector(16);
        try{
            TPointer tp = new TPointer();
            while(true) {
                tp.reset(1, 2, 3, 4);
                sv.append(tp);
            }
        }
        catch(OutOfMemoryError E){
            System.out.println("OutOfMemoryError");
            System.out.println("total frames: " + sv.getFrameCount());
            System.out.println("total elements: " + sv.size() * 4);
            System.out.println("memory: " + sv.size() * 16 / 1024 / 1024 + "M");
        }

        TPointer tmp = new TPointer();
        for(int i = 0; i < sv.size(); i ++)
            sv.get(i, tmp);
    }
}
