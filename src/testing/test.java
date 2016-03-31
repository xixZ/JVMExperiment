package testing;

public class test {
    private static final int rowSize = 30;
    private static final int totalSize = 30000000;	//30M
    private static final int blockSize = 100000;
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
            int blockNum = totalSize / blockSize;
            System.out.println(Runtime.getRuntime().freeMemory() / 1000000);

            int[][] arr2 = new int[blockNum][blockSize];

            System.out.println("Allocated total size: " + totalSize);

            for (int i = 0; i < blockNum; i ++) {
                for (int j = 0; j < blockSize; j ++ ) {
                    arr2[i][j] = i + j;
                }
            }
        }

        for (int i = 0; i < idx; i ++) {
            for (int j = 0; j < totalSize; j ++) {
                arr1[i][j] = i + j;
            }
        }
    }
}
