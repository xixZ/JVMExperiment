package testing;

public class testSVector {
    private static final int rowSize = 30;
    private static final int totalSize = 30000000;	//30M

    public static void main(String []args){
        int[][] arr1 = new int[rowSize][];
        int idx = 0;
        try{
            while(idx < rowSize){
                System.out.println("before allocation: " + Runtime.getRuntime().freeMemory() / 1000000 + "M");
                System.out.println("Space size to be allocated:" + totalSize);
                arr1[idx] = new int[totalSize];
                System.out.println("after  allocation: " + Runtime.getRuntime().freeMemory() / 1000000 + "M");
                idx ++;
            }
        }
        catch(OutOfMemoryError E){
            System.out.println("OutOfMemoryError");
            System.out.println("Try to allocate memory in small blocks");
            System.out.println(Runtime.getRuntime().freeMemory() / 1000000);

            SerializableVector sv = new SerializableVector(16);
            TPointer tp = new TPointer();
            for (int i = 0; i < totalSize / 4; i ++) {
                tp.reset(1, 2, 3, 4);
                sv.append(tp);
            }
            System.out.println("Succeed! total frames: " + sv.getFrameCount());
        }

        for (int i = 0; i < idx; i ++) {
            for (int j = 0; j < totalSize; j ++) {
                arr1[i][j] = i + j;
            }
        }
    }
}
