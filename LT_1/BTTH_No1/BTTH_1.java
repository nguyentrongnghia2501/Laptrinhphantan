package BTTH_No1;

import java.util.Arrays;
import java.util.Random;

public class BTTH_1 extends Thread {

    private int _id;
    private int[] _a;
    private int[] maxValues;  // thêm biến lưu kết quả

    public BTTH_1(int id, int[] a, int[] maxValues) {
        this._id = id;
        this._a = a;
        this.maxValues = maxValues;
    }

    @Override
    public void run() {
        int max = _a[0];
        for (int num : _a) {
            if (num > max) {
                max = num;
            }
        }
        maxValues[_id - 1] = max;  // lưu kết quả vào mảng chung
        System.out.println("Thread " + _id + " - Số lớn nhất: " + max);
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        int K = 4;
        Random rand = new Random();
        int[] a = rand.ints(N, 0, 11).toArray();
        System.out.println("Mảng a (rút gọn): " + Arrays.toString(Arrays.copyOf(a, N)) + "...");

        int baseSize = N / K;
        int remainder = N % K;
        int start = 0;

        BTTH_1[] threads = new BTTH_1[K];
        int[] maxValues = new int[K];

        for (int i = 0; i < K; i++) {
            int currentSize = baseSize + (i < remainder ? 1 : 0);
            int end = start + currentSize;
            int[] part = Arrays.copyOfRange(a, start, end);
            System.out.println("Phần " + (i + 1) + ": " + Arrays.toString(part));

            threads[i] = new BTTH_1(i + 1, part, maxValues);
            threads[i].start();

            start = end;
        }

        for (int i = 0; i < K; i++) {
            threads[i].join();
        }

        // Tính tổng các số lớn nhất
        int sum = 0;
        for (int val : maxValues) {
            sum += val;
        }
        System.out.println("Tổng các số lớn nhất: " + sum);
    }
}
