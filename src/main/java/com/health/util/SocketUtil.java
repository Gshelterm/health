package com.health.util;

import com.health.entity.Monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketUtil {

    public static boolean sendMsgTo(Monitor monitor, String msg) {
        boolean result = false;
        try (Socket s = new Socket(monitor.getAddress(), monitor.getPort());
             Scanner in = new Scanner(s.getInputStream());
             PrintWriter writer = new PrintWriter(s.getOutputStream(), true)) {

            writer.println(msg);

            boolean done = false;
            while (!done && in.hasNextLine()) {
                if (in.nextLine().trim().equals("OK:"+msg)) {
                    System.out.println("sendMsgTo monitor OK:"+msg);
                    result = true;
                    done = true;
                } else throw new RuntimeException("发送的信息不能被监护器处理:"+msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
