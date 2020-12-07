package cipher;

//import org.bouncycastle.crypto.engines.DESEngine;
import java.io.File;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.engines.DESedeEngine;

public class DESedeCBCMAC {

    public DESedeCBCMAC() {
    }

    // Encrypts input and returns the result as an array of bytes
    public byte[] encode(byte[] key, byte[] input) {         
//The output block of the algorithm is always 32 bits
        byte[] output = new byte[4];
        KeyParameter keyParameter = new KeyParameter(key);
        DESedeEngine desedeEngine = new DESedeEngine();
        CBCBlockCipherMac cbcBlockCipherMac = new CBCBlockCipherMac(desedeEngine);
        cbcBlockCipherMac.init(keyParameter);
        cbcBlockCipherMac.update(input, 0, input.length);
        cbcBlockCipherMac.doFinal(output, 0);
        return output;
    }

}
