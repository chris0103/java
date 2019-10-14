package crypto.cipher.aes.gnu;

import gnu.crypto.cipher.CipherFactory;
import gnu.crypto.cipher.IBlockCipher;

import java.util.HashMap;
import java.util.Map;

import crypto.cipher.CryptOption;
import crypto.cipher.aes.AESException;
import crypto.util.ByteUtils;

public class AES {
    
    private byte[] message;
    private byte[] combination;
 
    public AES() {
        message = null;
        combination = null;
    }
   
    public AES(byte[] message, byte[] combination) {
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
    
    private void doAES(int flag) throws Exception {
        byte[] ret = new byte[16];
        IBlockCipher cipher = CipherFactory.getInstance("AES");
        if (cipher == null) {
            throw new AESException("无效的gnu-cipher!");
        }
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put(IBlockCipher.CIPHER_BLOCK_SIZE, new Integer(16));
        attributes.put(IBlockCipher.KEY_MATERIAL, combination);
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
            throw new AESException("输入的明文或密文为空!");
        }
        if (! (flag == 0 || flag == 1)) {
            throw new AESException("加解密类型错误!无效类型值:" + flag);
        }
        doAES(flag);
    }
    
    public static void main(String[] args) {
        try {
            byte[] msg = new byte[] {
                (byte)0xe3, (byte)0xfd, (byte)0x51, (byte)0x12, (byte)0x3b, (byte)0x48, (byte)0xa2, (byte)0xe2,
                (byte)0xab, (byte)0x1d, (byte)0xb2, (byte)0x98, (byte)0x94, (byte)0x20, (byte)0x22, (byte)0x22            
            };
            
            ByteUtils.printBytesInHex("Plaintext", msg);
            byte[] com = new byte[] {
                (byte)0x44, (byte)0x41, (byte)0x6a, (byte)0xc2, (byte)0xd1, (byte)0xf5, (byte)0x3c, (byte)0x58,
                (byte)0x33, (byte)0x03, (byte)0x91, (byte)0x7e, (byte)0x6b, (byte)0xe9, (byte)0xeb, (byte)0xe0
            };
            ByteUtils.printBytesInHex("Raw key", com);
            AES aes = new AES(msg, com);
            aes.cryption(CryptOption.ENCRYPTION);
            ByteUtils.printBytesInHex("Ciphertext", aes.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
