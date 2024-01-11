package com.hotel.utils;

import java.io.*;
import java.util.ArrayList;

public class PythonCaller {

    public static ArrayList<Double> predict(int user_index, ArrayList<Integer> business_index) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python E:\\学\\大3-1\\生产实习\\big-data\\SpringBoot\\hotel\\src\\main\\test-model.py");
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
            out.write(String.valueOf(user_index));
            out.newLine();
            out.write(String.valueOf(business_index.size()));
            out.newLine();
            for (int i = 0; i < business_index.size(); i++) {
                out.write(String.valueOf(business_index.get(i)));
                out.newLine();
            }
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            ArrayList<Double> results = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                results.add(Double.valueOf(line));
            }
            in.close();
            out.close();
            proc.waitFor();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}

