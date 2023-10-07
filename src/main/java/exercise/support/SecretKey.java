package exercise.support;
import java.util.ArrayList;
import java.util.List;
public class SecretKey {
    public int[] key;
    public SecretKey() {
    }
    public SecretKey(int[] key) {
        this.key = key;
    }
    public SecretKey(int length){
        this.key=new int[length];
    }
    public void leftShift(){
        int firstNum=this.key[0];
        for(int i=1;i<this.key.length;i++){
            this.key[i-1]=this.key[i];
        }
        key[this.key.length-1]=firstNum;
    }


    public boolean equals(SecretKey targetKey) {
        for(int i=0;i<this.key.length;i++){
            if(this.key[i]!=targetKey.key[i]){
                return false;
            }
        }
        return true;
    }

    public List<SecretKey> group(int numGroup) {
        int length = this.key.length;
        if (numGroup == 0) {
            return null;
        }
        if (length % numGroup != 0) {
            return null;
        } else {
            int[][] result;
            int groupSize = length / numGroup;
            result = new int[numGroup][groupSize];
            for (int i = 0; i < numGroup; i++) {
                for (int j = 0; j < groupSize; j++) {
                    int index = i * groupSize + j;
                    result[i][j] = this.key[index];
                }
            }
            List<SecretKey> keyResults= new ArrayList<>();
            for(int i=0;i<result.length;i++){
                keyResults.add(new SecretKey(result[i]));
            }
            return keyResults;
        }
    }
    public List<SecretKey> group() {
        int numGroup = 2;
        int length = this.key.length;
        if (length % numGroup != 0) {
            return null;
        } else {
            int[][] result;
            int groupSize = length / numGroup;
            result = new int[numGroup][groupSize];
            for (int i = 0; i < numGroup; i++) {
                for (int j = 0; j < groupSize; j++) {
                    int index = i * groupSize + j;
                    result[i][j] = this.key[index];
                }
            }
            List<SecretKey> keyResults= new ArrayList<>();
            for(int i=0;i<result.length;i++){
                keyResults.add(new SecretKey(result[i]));
            }
            return keyResults;
        }
    }
    public void showKey() {
        for (int i = 0; i < key.length; i++) {
            System.out.printf("%d ", key[i]);
        }
        System.out.println();
    }
    public String keyToString(){
        StringBuilder keyStringBuilder=new StringBuilder();
        for(int num:key){
            keyStringBuilder.append(num);
        }
        String keyString=keyStringBuilder.toString();
        return keyString;
    }
}
