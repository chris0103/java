package crypto.util;

public class ByteUtils {
    
    /**
     * 以0x形式打印byte数组
     * @param title - 数组标题
     * @param bytes
     */
    public static void printBytesInHex(String title, byte[] bytes) {
        System.out.print(title + ":\t");
        for (byte b : bytes) {
            System.out.print(String.format("0x%x", b) + " ");
        }
        System.out.println();
    }
 
    /**
     * 以0x形式打印byte
     * @param title - byte标题
     * @param bytes
     */
    public static void printByteInHex(String title, byte b) {
        System.out.println(title + ": " + String.format("0x%x", b));
    }
    
    /**
     * 将以bit表示的bit位扩展至一个字节
     * @param bitByte
     * @return
     */
    public static byte[] bitToByte(byte[] bitByte) {
        byte[] byteByte = new byte[8 * bitByte.length];
        for (int i = 0; i < byteByte.length; i++) {
            byteByte[i] = (bitByte[i/8] & ( 1 << (7 - (i % 8)))) != 0 ? (byte)1 : (byte)0;
        }
        return byteByte;
    }
    
    /**
     * 将以字节表示的bit位收缩至一个bit
     * @param byteByte
     * @return
     */
    public static byte[] byteToBit(byte[] byteByte) {
        try {
            if (byteByte.length % 8 != 0) {
                throw new Exception("收缩的字节长度不是8的整数倍!");
            }
            byte[] bitByte = new byte[byteByte.length / 8];
            for (int i = 0; i < byteByte.length; i++) {
                if (byteByte[i] == (byte)1) {
                    bitByte[i/8] |= 1 << (7 - (i % 8));
                }
            }
            return bitByte;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * 左环移
     * @param chunk  操作源数组
     * @param offset 位移
     * @param span   环移范围
     * @return
     */
    public static void lshift(byte[] chunk, int offset) {
        int span = chunk.length;
        byte[] ret = new byte[span];
        int i;
        for (i = 0; i < span; i++)
            ret[i] = chunk[(offset+i)%span];
        System.arraycopy(ret, 0, chunk, 0, 4);
    }
}
