
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

public class SharedBufferApp {

    private static final int N = 100;
    private static final int K = 2; // số luồng ghi
    private static final int H = 2; // số luồng xử lý

    // Cấu trúc dữ liệu chia sẻ: BlockingQueue (thread-safe)
    private static BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(N);

    public static void main(String[] args) {
        // Tạo và khởi chạy các luồng sinh dữ liệu
        for (int i = 0; i < K; i++) {
            int id = i + 1;
            new Thread(new Producer(id)).start();
        }

        // Tạo và khởi chạy các luồng xử lý dữ liệu
        for (int i = 0; i < H; i++) {
            int id = i + 1;
            new Thread(new Consumer(id)).start();
        }
    }

    // Luồng sinh dữ liệu
    static class Producer implements Runnable {

        private int id;
        private Random rand = new Random();

        public Producer(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextInt(1000) + 500); // chờ 0.5 - 1.5 giây
                    int value = rand.nextInt(1000);
                    buffer.put(value); // blocking nếu đầy
                    System.out.printf("P%d: %d - %s%n", id, value, LocalTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Luồng xử lý dữ liệu
    static class Consumer implements Runnable {

        private int id;
        private Random rand = new Random();

        public Consumer(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextInt(1500) + 500); // chờ 0.5 - 2 giây
                    int value = buffer.take(); // blocking nếu rỗng
                    int result = value * 2; // xử lý: nhân đôi
                    System.out.printf("C%d: %d - %d - %s%n", id, value, result, LocalTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
// C:\Users\Nguyen Nghia\Documents\Đại Học Thủy Lợi\Laptrinhphantan\LT_1\BTTH_No2\SharedBufferApp.java
