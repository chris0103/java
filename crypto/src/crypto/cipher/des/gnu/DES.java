package crypto.cipher.des.gnu;

import gnu.crypto.cipher.CipherFactory;
import gnu.crypto.cipher.IBlockCipher;

import java.util.HashMap;
import java.util.Map;

import crypto.cipher.CryptOption;
import crypto.cipher.des.DESException;
import crypto.util.ByteUtils;

public class DES {
    
    private byte[] message;
    private byte[] combination;
 
    public DES() {
        message = null;
        combination = null;
    }
   
    public DES(byte[] message, byte[] combination) {
        this.message = message;
        this.combination = combination;
    }
    
    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public void setCombination(byte[] combination) {
        this.combination = combination;
    }
    
    private void parity_key(byte[] szOut, final byte[] szIn, final int offset) {
        int i;
        int cNext = 0;
        int cWorking = 0;
        for (i = 0; i < 7; i++) {
            cWorking = 0xFF & szIn[i + offset];
            szOut[i] = (byte)(((cWorking >> i) | cNext | 1) & 0xff);
            cWorking = 0xFF & szIn[i + offset];
            cNext    = ((cWorking << (7 - i)));
        }
        szOut[i] = (byte) (cNext | 1);
    } 
    
    private void doDES(int flag) throws Exception {
        byte[] ret = new byte[8];
        byte[] szParityKey = new byte[8];
        parity_key(szParityKey, combination, 0);
        ByteUtils.printBytesInHex("Padded key", szParityKey);
        IBlockCipher cipher = CipherFactory.getInstance("DES");
        if (cipher == null) {
            throw new DESException("无效的gnu-cipher!");
        }
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put(IBlockCipher.CIPHER_BLOCK_SIZE, new Integer(8));
        attributes.put(IBlockCipher.KEY_MATERIAL, szParityKey);
        cipher.init(attributes);
        switch (flag) {
        case CryptOption.ENCRYPTION: cipher.encryptBlock(message, 0, ret, 0);break;
        case CryptOption.DECRYPTION: cipher.decryptBlock(message, 0, ret, 0);break;
        default: 
        }
        message = ret;
    }
    
    public void cryption(int flag) throws Exception {
        if (message == null || message.length == 0) {
            throw new DESException("输入的明文或密文为空!");
        }
        if (! (flag == 0 || flag == 1)) {
            throw new DESException("加解密类型错误!无效类型值:" + flag);
        }
        doDES(flag);
    }
    

    
    public static void main(String[] args) {
        try {
            byte[] msg = new byte[] {
                (byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef
            };
            ByteUtils.printBytesInHex("Plaintext", msg);
            byte[] com = new byte[] {
                (byte)0x00, (byte)0x45, (byte)0x13, (byte)0x38, (byte)0x95, (byte)0x73, (byte)0x77
            };
            ByteUtils.printBytesInHex("Raw key", com);
            DES des = new DES(msg, com);
            des.cryption(CryptOption.ENCRYPTION);
            ByteUtils.printBytesInHex("Ciphertext", des.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
