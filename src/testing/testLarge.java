package testing;

/**
 * Created by xi on 2016/3/31.
 */
public class testLarge {
    private static final int rowSize = 30;
    private static final int totalSize = 30000000;	//30M

    public static void main(String []args) {
        int[][] arr1 = new int[rowSize][];
        int idx = 0;
        try {
            while (idx < rowSize) {
                System.out.println("before allocation: " + Runtime.getRuntime().freeMemory() / 1000000 + "M");
                System.out.println("Space size to be allocated:" + totalSize);
                arr1[idx] = new int[totalSize];
                System.out.println("after  allocation: " + Runtime.getRuntime().freeMemory() / 1000000 + "M");
                idx++;
            }
        } catch (OutOfMemoryError E) {
            System.out.println("OutOfMemoryError");
            System.out.println("total memory allocated: " + totalSize * idx / 1024 * 4 / 1024+ "M");
            System.out.println("total elements: " + totalSize * idx);
        }

        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < totalSize; j++) {
                arr1[i][j] = i + j;
            }
        }
    }
}
