package BTTH_No3;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Client {

    int[] _a;

    public static void main(String[] args) throws IOException {

        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 3306;

        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Đã kết nối tới server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        int N = 10;
        Random rand = new Random();
        int[] a = rand.ints(N, 0, 11).toArray();
        String send = Arrays.stream(a)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println("A ---> Server:" + send);
        out.println(send);  // Gửi mảng cho server

        String response = in.readLine();
        System.out.println("Server trả lời: " + response);
        socket.close();
    }
}
