public class ThreadMinElement extends Thread {
    private final int startIndex;
    private final int endIndex;
    private final MinElementFinder finder;

    public ThreadMinElement(int startIndex, int endIndex, MinElementFinder finder) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.finder = finder;
    }

    @Override
    public void run() {
        int localMin = Integer.MAX_VALUE;
        int localMinIndex = -1;
        for (int i = startIndex; i < endIndex; i++) {
            if (finder.arr[i] < localMin) {
                localMin = finder.arr[i];
                localMinIndex = i;
            }
        }
        finder.updateMinElement(localMin, localMinIndex);
        finder.latch.countDown();
    }
}
