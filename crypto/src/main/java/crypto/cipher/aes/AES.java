package crypto.cipher.aes;

import crypto.cipher.CryptOption;
import crypto.util.ByteUtils;

public class AES {
    
    public static byte[] RC = new byte[] {
        (byte)0x01, (byte)0x02, (byte)0x04, (byte)0x08, (byte)0x10,
        (byte)0x20, (byte)0x40, (byte)0x80, (byte)0x1b, (byte)0x36
    };
    
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
    
    private void doShift(byte[] chunk) {
        ByteUtils.lshift(chunk, 1);
    }
    
    private void doState(byte[] chunk) {
        int row, column;
        byte[] ret = new byte[chunk.length];
        for (int i = 0; i < ret.length; i++) {
            byte b = chunk[i];
            row = (b >>> 4) & (byte)0x0f;
            column = b & (byte)0x0f;
            ret[i] = SBox.S[row][column];
        }
        System.arraycopy(ret, 0, chunk, 0, chunk.length);
    }
    
    private byte[][][] generateKeys() throws Exception {
        if (combination.length != 16) {
            throw new AESException("无效的密钥参数!");
        }
        byte[][][] keys = new byte[11][4][4];
        int i, j;
        for (i = 0; i < 4; i++) {
           System.arraycopy(combination, i * 4, keys[0][i], 0, 4); 
        }
        for (i = 1; i < 11; i++) {
            for (j = 0; j < 4; j++) {
                byte[] temp = new byte[4];
                if (j % 4 == 0) {
                    System.arraycopy(keys[i-1][3], 0, temp, 0, 4);
                    doShift(temp);
                    doState(temp);
                    temp[0] ^= RC[i-1];
                } else {
                    System.arraycopy(keys[i][j-1], 0, temp, 0, 4);
                }
                for (int k = 0; k < 4; k++) {
                    temp[k] ^= keys[i-1][j][k];
                }
                System.arraycopy(temp, 0, keys[i][j], 0, 4);
            }
        }
        // Test code:        
//        byte[] testByte = new byte[4];
//        byte[] w28 = new byte[] {(byte)0xea,(byte)0xd2,(byte)0x73,(byte)0x21};
//        byte[] w31 = new byte[] {(byte)0x7f,(byte)0x8d,(byte)0x29,(byte)0x2f};
//        System.arraycopy(w31, 0, testByte, 0, 4);
//        doShift(testByte);
//        doState(testByte);
//        testByte[0] ^= (byte)0x1b;
//        for (int k = 0; k < 4; k++) {
//            testByte[k] ^= w28[k];
//        }
//        ByteUtils.printBytesInHex("Result", testByte);
        /*
         * expected output:
         * Result:  0xac 0x77 0x66 0xf3
         */
        return keys;
    }
    
    private void doAES(byte[][][] keys, int flag) throws Exception {
        int len = message.length % 16 == 0 ? 
                message.length : (message.length - message.length % 16 + 16);
        byte[] paddedMsg = new byte[len];
        System.arraycopy(message, 0, paddedMsg, 0, message.length);
        int i, j;
        // 将明文排列为列矩阵
        byte[][] state = new byte[4][4];
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                state[j][i] = message[i*4+j];
            }
        }
       
        // 首次轮密钥加
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                state[i][j] ^= keys[0][i][j];
            }
        }

        // 十轮加密
        byte[] temp = new byte[4];
        for (int round = 0; round < 10; round++ ) {
            // 字节代换
            for (byte[] row : state) {
                doState(row);
            }
            // 行移位
            for (i = 1; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    temp[j] = state[i][(i+j)%4];
                }
                System.arraycopy(temp, 0, state[i], 0, 4);
            }
            for (byte[] col : state) {
                ByteUtils.printBytesInHex("row", col);
            }
            // 列混淆变换
        }
    }
    
    public void cryption(int flag) throws Exception {
        if (message == null || message.length == 0) {
            throw new AESException("输入的明文或密文为空!");
        }
        if (! (flag == 0 || flag == 1)) {
            throw new AESException("加解密类型错误!无效类型值:" + flag);
        }
        byte[][][] keys = generateKeys();
        doAES(keys,flag);
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
            e.printStackTrace();
        }
    }
}
