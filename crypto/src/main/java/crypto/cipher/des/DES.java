package crypto.cipher.des;

import java.util.Arrays;

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

    private void perm(byte[] out, byte[] in, byte[] PBlock) {
        for (int i = 0; i < PBlock.length; ++i) {
            out[i] = in[PBlock[i] - 1];
        }
    }
    
    private void lshift(byte[] chunk, int offset) {
        int span = chunk.length;
        byte[] temp = new byte[span];
        int i;
        for (i=0;i<span;i++)
            temp[i] = chunk[(offset+i)%span];
        for (i=0;i<span;i++)
            chunk[i] = temp[i];
    }
    
    private void concat(byte[] left, byte[] right, byte[] jointBytes) throws Exception {
        if (jointBytes == null || jointBytes.length < left.length + right.length) {
            throw new DESException("非法的jointBytes数组长度!");
        }
        System.arraycopy(left, 0, jointBytes, 0, left.length);
        System.arraycopy(right, 0, jointBytes, left.length, right.length);
    }
    
    private void parity_key(byte[] szOut, final byte[] szIn, final int offset) {
        int i;
        int cNext = 0;
        int cWorking = 0;

        for (i = 0; i < 7; i++) {
            cWorking = 0xFF & szIn[i + offset];
            //将每一字节最低位置1
            szOut[i] = (byte)(((cWorking >> i) | cNext | 1) & 0xff);
            cWorking = 0xFF & szIn[i + offset];
            cNext    = ((cWorking << (7 - i)));
        }
        //将每一字节最低位置1
        szOut[i] = (byte) (cNext | 1);
    }
    
    private void doState(byte[][] out, byte[][] in) {
        for (int i = 0; i < 8; i++) {
            int row = in[i][0] * 2 + in[i][5];
            int col = in[i][1] * 8 + in[i][2] * 4 + in[i][3] * 2 + in[i][4];
            for (int pos = 0; pos < 4; pos++) {
                out[i][pos] = (SBox.S[i][row][col] & (1 << (3 - pos))) != 0 ? (byte)1 : (byte)0;
            }
        }
    }
    
    private byte[][] generateKeys() throws Exception {
        if (combination.length != 7) {
            throw new DESException("无效的密钥参数!");
        }
        byte[][] keys = new byte[16][48];           // 返回值, 每个字节保存密钥中的一位
        byte[] paddedKey = new byte[8];             // 56位扩展至64位的密钥
        byte[] extKey = new byte[64];               // 用64字节空间保存64位的值
        byte[] L = new byte[28], R = new byte[28];  // 密钥拆分为L,R两半
        byte[] pk1 = new byte[56];                  // 保存密钥的初始置换结果 
        parity_key(paddedKey, combination, 0);
        for (int i=0; i<64 ; i++) {
            extKey[i] = (paddedKey[i/8] & (1<<(7-(i%8)))) != 0 ? (byte)1 : (byte)0;
        }
        perm(pk1, extKey, Perms.PC1);
        System.arraycopy(pk1, 0, L, 0, 28);
        System.arraycopy(pk1, 28, R, 0, 28);
        //生成十六轮密钥
        for (int i = 0; i < 16; i++) {
            lshift(L, Perms.LSO[i]);
            lshift(R, Perms.LSO[i]);
            byte[] jointBytes = new byte[L.length + R.length];
            concat(L, R, jointBytes);
            perm(keys[i], jointBytes, Perms.PC2);
        }
        return keys;
    }
    
    private void doDES(byte[][] keys, int flag) throws Exception {
        int len = message.length % 8 == 0? 
                message.length : (message.length - message.length % 8 + 8);
        byte[] paddedMsg = new byte[len];
        System.arraycopy(message, 0, paddedMsg, 0, message.length);
        byte[] extMsg = new byte[64];
        byte[] temp = new byte[8];
        byte[] roundRet = new byte[64];
        byte[] epRet = new byte[48];
        byte[][] stateIn = new byte[8][6];
        byte[][] stateOut = new byte[8][4];
        byte[] stateRet = new byte[32];
        byte[] pRet = new byte[32];
        byte[] L = new byte[32], R = new byte[32];
        for (int i = 0; i < paddedMsg.length / 8; i++) {   
            System.arraycopy(paddedMsg, i * 8, temp, 0, 8);
            extMsg = ByteUtils.bitToByte(temp);
            perm(roundRet, extMsg, Perms.IP);
            // 十六轮加密
            for (int round = 0; round < 16; round++ ) {
                System.arraycopy(roundRet, 0, L, 0, L.length);
                System.arraycopy(roundRet, 32, R, 0, R.length);
                perm(epRet, R, Perms.EP);
                int keyIndex = flag == ENCRYPTION ? round : (15 - round);
                for (int pos = 0; pos < epRet.length; pos++) {
                    epRet[pos] = (byte)(epRet[pos] ^ keys[keyIndex][pos]);
                }
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 6; col++) {
                        stateIn[row][col] = epRet[row * 6 + col];
                    }
                }
                doState(stateOut, stateIn);
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 4; col++) {
                        stateRet[row * 4 + col] = stateOut[row][col];
                    }
                }
                perm(pRet, stateRet, Perms.P);
                System.arraycopy(R, 0, roundRet, 0, R.length);
                for (int pos = 0; pos < R.length; pos++) {
                    R[pos] = (byte)(L[pos] ^ pRet[pos]);
                }
                
                System.arraycopy(R, 0, roundRet, L.length, R.length);
            }
            System.arraycopy(roundRet, 0, L, 0, L.length);
            System.arraycopy(roundRet, 32, R, 0, R.length);
            concat(R, L, roundRet);
            perm(extMsg, roundRet, Perms.IPr);
            Arrays.fill(temp, (byte)0);
            temp = ByteUtils.byteToBit(extMsg);
            if (temp == null) {
                throw new DESException("收缩字节格式错误!");
            }
            System.arraycopy(temp, 0, message, i * 8, temp.length);
        }
    }
    
    public void cryption(int flag) throws Exception {
        if (message == null || message.length == 0) {
            throw new DESException("输入的明文或密文为空!");
        }
        if (! (flag == 0 || flag == 1)) {
            throw new DESException("加解密类型错误!无效类型值:" + flag);
        }
        byte[][] keys = generateKeys();
        doDES(keys, flag);
    }
    
    public static int ENCRYPTION = 0;
    public static int DECRYPTION = 1;
    
    public static void main(String[] args) {
        try {
            byte[] msg = new byte[] {
                (byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef
            };
            byte[] msg1 = new byte[] {
                (byte)0x56, (byte)0xcc, (byte)0x9, (byte)0xe7, (byte)0xcf, (byte)0xdc, (byte)0x4c, (byte)0xef   
            };
            byte[] com = new byte[] {
                (byte)0x00, (byte)0x45, (byte)0x13, (byte)0x38, (byte)0x95, (byte)0x73, (byte)0x77
            };
            DES des = new DES(msg, com);
            des.cryption(DES.ENCRYPTION);
            ByteUtils.printBytesInHex("Ciphertext", des.getMessage());
            des = new DES(msg1, com);
            des.cryption(DES.DECRYPTION);
            ByteUtils.printBytesInHex("Plaintext", des.getMessage());
            /*
             * expected output:
             * Plaintext:  0x1 0x23 0x45 0x67 0x89 0xab 0xcd 0xef 
             * Raw key:    0x0 0x45 0x13 0x38 0x95 0x73 0x77 
             * Padded Key: 0x1 0x23 0x45 0x67 0x89 0xab 0xcd 0xef 
             * Ciphertext: 0x56 0xcc 0x9 0xe7 0xcf 0xdc 0x4c 0xef 
             */
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
