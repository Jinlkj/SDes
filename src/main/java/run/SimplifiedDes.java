package run;

import exercise.support.PBox;
import exercise.support.SBox;
import exercise.support.SecretKey;

import java.util.Arrays;

public class SimplifiedDes {
    static PBox pBox_10 = new PBox(new int[]{3, 5, 2, 7,4,10,1,9,8,6});
    static PBox pBox_8= new PBox(new int[]{6,3,7,4,8,5,10,9});
    static SBox sBox1= new SBox(new int[][]{{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,0,2}});
    static SBox sBox2= new SBox(new int[][]{{0,1,2,3},{2,3,1,0},{3,0,1,2},{2,1,0,3}});
    static PBox ip =new PBox(new int[]{2,6,3,1,4,8,5,7});
    static PBox ipLast=new PBox(new int[]{4,1,3,5,7,2,8,6});
    static PBox spBox =new PBox(new int[]{2,4,3,1});
    static PBox leftShift1 =new PBox(new int[]{2,3,4,5,1});
    static PBox leftShift2 =new PBox(new int[]{3,4,5,1,2});
    public static String Hacker(SecretKey plainText,SecretKey cipherText){
        int[] binaryArray = new int[10]; // 创建一个包含十位的二进制数组
        for (int i = 0; i < (1 << 10); i++) {
            // 使用位运算来生成所有情况
            for (int j = 0; j < 10; j++) {
                binaryArray[j] = (i & (1 << j)) == 0 ? 0 : 1;
            }
            SecretKey secretKey=new SecretKey(binaryArray);
            if(encrypt(secretKey,plainText).equals(cipherText)){
                secretKey.showKey();
                plainText.showKey();
                cipherText.showKey();
                return secretKey.keyToString();
            }
        }
        return "破解失败";
    }
    public static SecretKey encrypt(SecretKey secretKey,SecretKey plainText){
        // 初始置换
        plainText=ip.boxing(plainText);
        SecretKey plainText1=plainText.group().get(0);
        SecretKey plainText2=plainText.group().get(1);
        secretKey=pBox_10.boxing(secretKey);
        for(int i=1;i<=2;i++){
            SecretKey roundKey=generateRoundKey(secretKey,i);
            plainText1=bitwise_XOR(plainText1, wheel_Function(plainText2, roundKey));
            // SWAP
            SecretKey tempText=plainText1;
            plainText1=plainText2;
            plainText2=tempText;
            // EndSWAP
        }
        // SWAP
        SecretKey tempKey=plainText1;
        plainText1=plainText2;
        plainText2=tempKey;
        // EndSWAP
        plainText=mergeKeys(plainText1, plainText2);
        plainText=ipLast.boxing(plainText);
        return plainText;
//        System.out.printf("加密后的密文是:");
//        plainText.showKey();
    }
    public static SecretKey Decrypt(SecretKey secretKey,SecretKey secretText){
        secretText=ip.boxing(secretText);
        SecretKey text1=secretText.group().get(0);
        SecretKey text2=secretText.group().get(1);
        secretKey=pBox_10.boxing(secretKey);
        for(int i=2;i>0;i--){
            SecretKey roundKey=generateRoundKey(secretKey, i);
            text1=bitwise_XOR(text1,wheel_Function(text2,roundKey));
            // SWAP
            SecretKey tempText=text1;
            text1=text2;
            text2=tempText;
            // END SWAP
        }
        // SWAP
        SecretKey tempText=text1;
        text1=text2;
        text2=tempText;
        // END SWAP
        secretText=mergeKeys(text1, text2);
        secretText=ipLast.boxing(secretText);
        return secretText;
    }
    public static SecretKey wheel_Function(SecretKey plainText,SecretKey key){
        // 拓展置换
        PBox epBox=new PBox(new int[]{4,1,2,3,2,3,4,1});
        plainText=epBox.boxing(plainText);
        plainText=bitwise_XOR(plainText, key);
        SecretKey leftText=plainText.group().get(0);
        SecretKey rightText=plainText.group().get(1);
        leftText=sBox1.boxing(leftText);
        rightText=sBox2.boxing(rightText);
        plainText=mergeKeys(leftText,rightText);
        plainText=spBox.boxing(plainText);
        return plainText;
    }
    // 按位异或
    public static SecretKey bitwise_XOR(SecretKey key1, SecretKey key2){
        if(key1.key.length==key2.key.length){
            SecretKey newKey= new SecretKey(key1.key.length);
            for(int i=0;i<key1.key.length;i++){
                newKey.key[i]=key1.key[i]^key2.key[i];
            }
            return newKey;
        }else{
            throw new IllegalArgumentException("长度不相同");
        }
    }
    // 轮密钥生成函数
    public static SecretKey generateRoundKey(SecretKey key,int round){
        SecretKey key1=key.group().get(0);
        SecretKey key2=key.group().get(1);
        for(int i=0;i<round;i++){
            key1=leftShift1.boxing(key1);
            key2=leftShift2.boxing(key2);
        }
        key=mergeKeys(key1, key2);
        key=pBox_8.boxing(key);
        return key;
    }
    // 合并两个密钥
    public static SecretKey mergeKeys(SecretKey key1,SecretKey key2){
        SecretKey mergedKey=new SecretKey(key1.key.length+key2.key.length);
        int index=0;
        for(int i=0;i<key1.key.length;i++){
            mergedKey.key[i]=key1.key[i];
            index++;
        }
        for(int i=0;i<key2.key.length;i++){
            mergedKey.key[i+index]=key2.key[i];
        }
        return mergedKey;
    }
}
