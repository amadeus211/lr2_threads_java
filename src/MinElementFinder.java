import java.util.concurrent.CountDownLatch;

public class MinElementFinder {
    public final int[] arr;
    private final int threadNum;
    private int minElement = Integer.MAX_VALUE;
    private int minElementIndex = -1;
    public CountDownLatch latch;

    public MinElementFinder(int[] arr, int threadNum) {
        this.arr = arr;
        this.threadNum = threadNum;
        this.latch = new CountDownLatch(threadNum);
    }

    public void findMinElement() {
        int partSize = arr.length / threadNum;
        for (int i = 0; i < threadNum; i++) {
            int startIndex = i * partSize;
            int endIndex;
            if (i == threadNum - 1) {
                endIndex = arr.length;
            } else {
                endIndex = (i + 1) * partSize;
            }
            new ThreadMinElement(startIndex, endIndex, this).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Min: " + minElement + " || Index: " + minElementIndex);
    }

    public synchronized void updateMinElement(int element, int index) {
        if (element < minElement) {
            minElement = element;
            minElementIndex = index;
        }
    }


}
