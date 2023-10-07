package exercise.support;

public class PBox {
    int[] index;
    int length;

    public PBox(int[] index) {
        this.index = index;
        this.length = this.index.length;
    }

    public SecretKey boxing(SecretKey inputKey) {
        SecretKey resultKey = new SecretKey(this.length);
        for (int i = 0; i < this.length; i++) {
            resultKey.key[i]=inputKey.key[this.index[i]-1];
        }
        return resultKey;
    }
}
