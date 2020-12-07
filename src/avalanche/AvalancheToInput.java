package avalanche;

import cipher.DESCBCMAC;
import cipher.DESedeCBCMAC;
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
import stats.Histogram;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AvalancheToInput {

    private DESCBCMAC descbcmac;
    private DESedeCBCMAC desedecbcmac;
    private Histogram histogram;
//    public String key = "123456781122334499887766";
    public String key = "12345678";
    public String pathPlain = "C:\\Users\\SONY\\Desktop\\Text\\dibawah 1 kb\\Plain3.txt";
//    public String pathEncrypt = "C:\\Users\\SONY\\Desktop\\Text\\dibawah 1 kb\\enkrpsides.txt";
//    public String random = "C:\\Users\\SONY\\Desktop\\Text\\random.txt";
//    long akhirEnkripsi = System.currentTimeMillis();

    // Initialize attributes and launch the avalanche effect
    public AvalancheToInput(int muestras) {
        String text = readFileAsString(pathPlain);
        descbcmac = new DESCBCMAC();
//        desedecbcmac = new DESedeCBCMAC();
        // 64-bit key
        // Histogram. 33 elements because the Hamming distance
        // it could take 33 possible values ​​(the output block has 32 bits)
        histogram = new Histogram(33, muestras);
        long awalEnkripsi1 = System.currentTimeMillis();
        startAvalanche(muestras);
        long akhirEnkripsi1 = System.currentTimeMillis();
        long waktuEnkripsi1 = akhirEnkripsi1 - awalEnkripsi1;
        System.out.println("Waktu Enkripsi 1 : " + waktuEnkripsi1 + " ms");
    }

    // Repeat the avalanche effect n times modifying the input by 1 random bit
    public void startAvalanche(int n) {
        for (int i = 0; i < n; i++) {
            byte[] input1 = createInput();
            byte[] input2 = flipRandomBit(input1);
            avalanche(input1, input2);
        }
    }

    public byte[] createInput() {
        // Create byte sesuai entry because
        // the algorithm does not specify input block size
        String text = readFileAsString(pathPlain);
        byte[] input = text.getBytes();
//        System.out.println("Awal = "+input);
//        for (int i = 0; i < inp; i++) {
//            input[i] = 10;
//        }
//      
//		new Random().nextBytes(input);
        return input;
    }

    // Complements a random bit in an array of bytes and returns the array of bytes
    // with bit changed
    public byte[] flipRandomBit(byte[] bytes) {
        byte[] res = bytes.clone();
        int randomBit = ThreadLocalRandom.current().nextInt(0, 7);
        int randomByte = ThreadLocalRandom.current().nextInt(0, 7);
        res[randomByte] ^= 1 << randomBit;
        return res;
    }

    // Add the avalanche and probability effect values ​​to the histogram for
    // a case study
    public void avalanche(byte[] input1, byte[] input2) {

        byte[] output1 = descbcmac.encode(key.getBytes(), input1);
        byte[] output2 = descbcmac.encode(key.getBytes(), input2);
        int hD = getHammingDistance(output1, output2);
//        System.out.println("Encrypt = "+output1);
//        String s = new String(output1);
//        try (FileOutputStream fos = new FileOutputStream(pathEncrypt)) {
//
//            byte[] mybytes = output1;
//
//            fos.write(mybytes);
//        } catch (IOException e) {
//
//        }      
        histogram.addData(hD);
    }

    // Calculate the Hamming distance for a case study
    public int getHammingDistance(byte[] a, byte[] b) {
        int res = 0;
        byte[] xnor = new byte[a.length];
        for (int i = 0; i < xnor.length; i++) {
            xnor[i] = (byte) ((a[i] & b[i]) | (~a[i] & ~b[i]));
        }
        for (int j = 0; j < xnor.length; j++) {
            for (int k = 0; k < 8; k++) {
                if ((xnor[j] & (1 << k)) == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    // Methods to display the histogram values ​​on the screen
    public void printHistogram() {
        histogram.print();
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

    private void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[64];
        int numOfByteRead;
        while ((numOfByteRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, numOfByteRead);
        }
        out.close();
        in.close();

    }

}
