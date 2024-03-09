public class Main {

    public static void main(String[] args) {
        int arrayLength = 1000000;
        int threadNum = 4;
        int[] arr = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        int randomIndex = (int) (Math.random() * arrayLength);
        arr[randomIndex] = -100;

        MinElementFinder finder = new MinElementFinder(arr, threadNum);
        long startTime = System.currentTimeMillis();
        finder.findMinElement();
        long endTime = System.currentTimeMillis();
        System.out.println("Time:" + (endTime - startTime));
    }
}