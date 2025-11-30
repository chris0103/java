package crypto.cipher.des.sdes;


public class SDES {
    private byte[] message;
    private byte[] combination;
    
    private byte[][] S1 = new byte[][] {
        {(byte)0x01, (byte)0x00, (byte)0x03, (byte)0x02},
        {(byte)0x03, (byte)0x02, (byte)0x01, (byte)0x00},
        {(byte)0x00, (byte)0x02, (byte)0x01, (byte)0x03},
        {(byte)0x03, (byte)0x01, (byte)0x03, (byte)0x02}
    };
    
    private byte[][] S2 = new byte[][] {
        {(byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03},
        {(byte)0x02, (byte)0x00, (byte)0x01, (byte)0x03},
        {(byte)0x03, (byte)0x00, (byte)0x01, (byte)0x00},
        {(byte)0x02, (byte)0x01, (byte)0x00, (byte)0x03}
    };

    public SDES() {
        message = null;
        combination = null;
    }
    
    public SDES(byte[] message, byte[] combination) {
        this.message = message;
        this.combination = combination;
    }
    
    /**
     * 初始置换(2 6 3 1 4 8 5 7)
     * @return
     */
    private void initialPerm() {
        byte temp = 0;
        temp |= (byte)(message[0] <<  1) & 0x80;
        temp |= (byte)(message[0] <<  4) & 0x40;
        temp |= (byte)(message[0]      ) & 0x20;
        temp |= (byte)(message[0] >>> 3) & 0x10;
        temp |= (byte)(message[0] >>> 1) & 0x08;
        temp |= (byte)(message[0] <<  2) & 0x04;
        temp |= (byte)(message[0] >>> 2) & 0x02;
        temp |= (byte)(message[0] >>> 1) & 0x01;
        message[0] = temp;
    }
    
    private void functionK(byte key) {
        byte temp = 0;
        int i, j;
        temp |= (byte)(message[0] <<  7) & 0x80;
        temp |= (byte)(message[0] <<  3) & 0x40;
        temp |= (byte)(message[0] <<  3) & 0x20;
        temp |= (byte)(message[0] <<  3) & 0x10;
        temp |= (byte)(message[0] <<  1) & 0x08;
        temp |= (byte)(message[0] <<  1) & 0x04;
        temp |= (byte)(message[0] <<  1) & 0x02;
        temp |= (byte)(message[0] >>> 3) & 0x01;
        temp ^= key;

        i = ((byte)(temp >>> 6) & 0x02) ^ ((byte)(temp >>> 4) & 0x01);
        j = ((byte)(temp >>> 5) & 0x02) ^ ((byte)(temp >>> 5) & 0x01);
        temp = (byte)((byte)(temp << 4) ^ S1[i][j]);
        i = ((byte)(temp >>> 6) & 0x02) | ((byte)(temp >>> 4) & 0x01);
        j = ((byte)(temp >>> 5) & 0x02) | ((byte)(temp >>> 5) & 0x01);
        temp = (byte)(temp << 2 ^ S2[i][j]);
        message[0] ^= (byte)(temp << 4 ) & 0xf0;
    }
    
    private void simpleSwap() {
        message[0] = (byte)((message[0] << 4) | (message[0] >>> 4));
     }
    
    private void inverseInitialPerm() {
        byte temp = 0;
        temp |= (byte)(message[0] & 0x10) << 3;
        temp |= (byte)(message[0] & 0x80) >>> 2;
        temp |= (byte)(message[0] & 0x20);
        temp |= (byte)(message[0] & 0x08) << 1;
        temp |= (byte)(message[0] & 0x02) << 2;
        temp |= (byte)(message[0] & 0x40) >>> 4;
        temp |= (byte)(message[0] & 0x01) << 1;
        temp |= (byte)(message[0] & 0x04) >>> 2;
        message[0] = temp;
    }
    
    private byte[] generateKeys() throws InvalidKeyLengthException {
        if (combination.length != 2) {
            throw new InvalidKeyLengthException("无效的密钥参数!");
        }
        byte[] keys = new byte[2];
        byte[] pair = new byte[2];
        //1、置换  (3 5 2 7 4 10 1 9 8 6)
        pair[0] |= (byte)(combination[0] <<  2) & 0x10;
        pair[0] |= (byte)(combination[0] <<  3) & 0x08;
        pair[0] |= (byte)(combination[0] >>> 1) & 0x04;
        pair[0] |= (byte)(combination[1] >>> 2) & 0x02;
        pair[0] |= (byte)(combination[0] >>> 1) & 0x01;
        pair[1] |= (byte)(combination[1] <<  4) & 0x10;
        pair[1] |= (byte)(combination[0] >>> 1) & 0x08;
        pair[1] |= (byte)(combination[1] <<  1) & 0x04;
        pair[1] |= (byte)(combination[1] >>> 1) & 0x02;
        pair[1] |= (byte)(combination[1] >>> 4) & 0x01;
        //2、左环移1位
        pair[0] = (byte)(((pair[0] << 1) | (pair[0] >>> 4)) & 0x1f);
        pair[1] = (byte)(((pair[1] << 1) | (pair[1] >>> 4)) & 0x1f);
        //3、置换 (6 3 7 4 8 5 10 9), 得到K1
        keys[0] |= (byte)(pair[1] <<  3) & 0x80;
        keys[0] |= (byte)(pair[0] <<  4) & 0x40;
        keys[0] |= (byte)(pair[1] <<  2) & 0x20;
        keys[0] |= (byte)(pair[0] <<  3) & 0x10;
        keys[0] |= (byte)(pair[1] <<  1) & 0x08;
        keys[0] |= (byte)(pair[0] <<  2) & 0x04;
        keys[0] |= (byte)(pair[1] <<  1) & 0x02;
        keys[0] |= (byte)(pair[1] >>> 1) & 0x01;
        //4、左环移2位
        pair[0] = (byte)(((pair[0] << 2) | (pair[0] >>> 3)) & 0x1f);
        pair[1] = (byte)(((pair[1] << 2) | (pair[1] >>> 3)) & 0x1f);
        //5、置换 (6 3 7 4 8 5 10 9), 得到K2
        keys[1] |= (byte)(pair[1] <<  3) & 0x80;
        keys[1] |= (byte)(pair[0] <<  4) & 0x40;
        keys[1] |= (byte)(pair[1] <<  2) & 0x20;
        keys[1] |= (byte)(pair[0] <<  3) & 0x10;
        keys[1] |= (byte)(pair[1] <<  1) & 0x08;
        keys[1] |= (byte)(pair[0] <<  2) & 0x04;
        keys[1] |= (byte)(pair[1] <<  1) & 0x02;
        keys[1] |= (byte)(pair[1] >>> 1) & 0x01;
        return keys;
    }

    
    public void cryption(int flag) {
        try {
            if (message == null || message.length == 0) {
                throw new InvalidKeyLengthException("");
            }
            if (! (flag == 0 || flag == 1)) {
                throw new InvalidKeyLengthException("加解密类型错误!无效类型值:" + flag);
            }
            byte[] keys = generateKeys();
            initialPerm();
            functionK(keys[flag]);
            simpleSwap();
            functionK(keys[1-flag]);
            inverseInitialPerm();
        } catch (InvalidKeyLengthException e) {
            System.err.println(e.getMessage());
        }
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message.getBytes();
    }
    
    
    public void setCombination(byte[] combination) {
        this.combination = combination;
    }
    
    public static int ENCRYPTION = 0;
    public static int DECRYPTION = 1;
    
    public static void main(String[] args) {
        byte[] msg = new byte[] {(byte)0xae};
        System.out.println("Text: " + String.format("0x%x", msg[0]));
        byte[] com = new byte[] {(byte)0x0f, (byte)0x1d};
        SDES sdes = new SDES(msg,com);
        sdes.cryption(SDES.ENCRYPTION);
        System.out.println("Cipher: " + String.format("0x%x", sdes.getMessage()[0]));
    }
}
