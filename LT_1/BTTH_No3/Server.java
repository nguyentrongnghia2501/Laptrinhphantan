package BTTH_No3;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException {
        final int PORT = 3306;

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Đang chờ kết nối ..........");

        Socket clienSocket = serverSocket.accept();
        System.out.println(" Đã kết nối ..........");

        BufferedReader in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clienSocket.getOutputStream(), true);

        String received = in.readLine();
        System.out.println("Server nhận được: " + received);

        int[] send = Arrays.stream(received.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println("Mảng a: " + Arrays.toString(send));

        int max = send[0];
        for (int i = 1; i < send.length; i++) {
            if (send[i] > max) {
                max = send[i];
            }
        }
        System.out.println("Số lớn nhất là: " + max);

        // / gửi Max về client
        out.println(max);

        clienSocket.close();
        serverSocket.close();
        System.out.println("Đã đóng kết nối.");

    }
}

// C:\Users\Nguyen Nghia\Documents\Đại Học Thủy Lợi\Laptrinhphantan\LT_1\BTTH_No3\Server.java/
