package crypto.util;

public class ByteUtils {
    
    /**
     * ��0x��ʽ��ӡbyte����
     * @param title - �������
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
     * ��0x��ʽ��ӡbyte
     * @param title - byte����
     * @param bytes
     */
    public static void printByteInHex(String title, byte b) {
        System.out.println(title + ": " + String.format("0x%x", b));
    }
    
    /**
     * ����bit��ʾ��bitλ��չ��һ���ֽ�
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
     * �����ֽڱ�ʾ��bitλ������һ��bit
     * @param byteByte
     * @return
     */
    public static byte[] byteToBit(byte[] byteByte) {
        try {
            if (byteByte.length % 8 != 0) {
                throw new Exception("�������ֽڳ��Ȳ���8��������!");
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
     * ����
     * @param chunk  ����Դ����
     * @param offset λ��
     * @param span   ���Ʒ�Χ
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
