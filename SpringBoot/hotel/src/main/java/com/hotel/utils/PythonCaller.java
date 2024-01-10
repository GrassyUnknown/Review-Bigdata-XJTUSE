package com.hotel.utils;

import java.io.*;

public class PythonCaller {

    public static void main(String[] args) {
        try {
            // 设置Python脚本路径
            String pythonScriptPath = "E:\\学\\大3-1\\生产实习\\big-data\\SpringBoot\\hotel\\src\\main\\test-model.py";

            // 创建ProcessBuilder并传入Python脚本路径
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);

            // 启动外部进程
            Process process = processBuilder.start();

            // 获取进程的输出流，用于读取Python脚本的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 获取进程的输入流，用于向Python脚本传递输入
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            // 向Python脚本传递输入，这里可以根据需要进行输入
            writer.write("123");
            writer.newLine();
            writer.write("120");
            writer.flush();

            // 读取Python脚本的输出
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python Output: " + line);
            }

            // 等待外部进程执行完毕
            int exitCode = process.waitFor();
            System.out.println("Python script exited with code " + exitCode);

            // 关闭输入流和进程
            reader.close();
            process.destroy();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

