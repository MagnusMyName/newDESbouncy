package main;

import avalanche.AvalancheToInput;
import avalanche.AvalancheToKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    public static String pathPlain = "C:\\Users\\SONY\\Desktop\\Text\\dibawah 1 kb\\Plain3.txt";

    public static void main(String[] args) {

        int samples = pathPlain.length();
        AvalancheToInput avalancheToInput = new AvalancheToInput(samples);

        System.out.println("Histogram avalanche to input:");
        avalancheToInput.printHistogram();

    }

    public String readFileAsString(String fileName) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
//    	AvalancheToKey avalancheToKey = new AvalancheToKey(muestras);
//		System.out.println("Histogram avalanche to key:");
//		avalancheToKey.printHistogram();
//
//    public static void printBits(byte[] bytes) {
//        for (byte b : bytes) {
//            System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//        }
//    }

