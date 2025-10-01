package com.college;

import com.college.domain.Payment;
import com.college.service.PaymentService;
import com.college.utilities.TextFileWriter;

public class MainForTest {
    public static void main(String[] args) {
        TextFileWriter writer = new TextFileWriter("records/output.txt");
        writer.writeLine("Hello, world!");
        System.out.println("Successfully wrote to file");



    }
}
