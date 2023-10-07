package exercise.support;

public class SBox {
    int[][] sbox;
    public SBox(int[][] sbox){
        this.sbox=sbox;
    }
    public SecretKey boxing(SecretKey key){
        if (key.key.length!=4){
            throw new IllegalArgumentException("不合法的KEY");
        }else{
            int row=key.key[0]*2+key.key[3];
            int col=key.key[1]*2+key.key[2];
            int value=sbox[row][col];
            SecretKey resultKey;
            switch (value) {
                case 0:
                    resultKey = new SecretKey(new int[]{0, 0});
                    break;
                case 1:
                    resultKey = new SecretKey(new int[]{0, 1});
                    break;
                case 2:
                    resultKey = new SecretKey(new int[]{1, 0});
                    break;
                case 3:
                    resultKey = new SecretKey(new int[]{1, 1});
                    break;
                default:
                    // 处理默认情况，如果 value 不在 0 到 3 的范围内
                    throw new IllegalArgumentException("SBOX错误");
            }
            return resultKey;

        }
    }
}

