package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import exercise.support.SecretKey;
import run.SimplifiedDes;

public class Home {
    public static void main(String[] args) {
//        设置密钥
        SecretKey secretKey=new SecretKey(new int[]{1,0,1,0,0,0,0,0,1,0});
//        1010000010
        JFrame firstPage = new JFrame("S-Des");
        firstPage.setSize(600, 800);
        firstPage.setBackground(Color.lightGray);
        //设置窗口可见
        firstPage.setVisible(true);
        //设置关闭窗口即为退出程序
        firstPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置字体
        Font font = new Font("宋体",Font.PLAIN, 30);

        //添加面板
        JPanel mainPanel = new JPanel();
        firstPage.add(mainPanel);
        mainPanel.setLayout(null);

        //创建JLabel
        JLabel plain = new JLabel("明  文");
        plain.setBounds(70,50,100,50);
        plain.setFont(font);
        plain.setOpaque(true);
        plain.setBackground(Color.white);
        mainPanel.add(plain);

        JLabel ecp = new JLabel("加密后");
        ecp.setBounds(70,150,100,50);
        ecp.setFont(font);
        ecp.setOpaque(true);
        ecp.setBackground(Color.white);
        mainPanel.add(ecp);

        JLabel cipher = new JLabel("密  文");
        cipher.setBounds(70,250,100,50);
        cipher.setFont(font);
        cipher.setOpaque(true);
        cipher.setBackground(Color.white);
        mainPanel.add(cipher);

        JLabel dcp = new JLabel("解密后");
        dcp.setBounds(70,350,100,50);
        dcp.setFont(font);
        dcp.setOpaque(true);
        dcp.setBackground(Color.white);
        mainPanel.add(dcp);

        JLabel key = new JLabel("密  钥");
        key.setBounds(70,650,100,50);
        key.setFont(font);
        key.setOpaque(true);
        key.setBackground(Color.white);
        mainPanel.add(key);

        JLabel ming = new JLabel("明  文");
        ming.setBounds(70,450,100,50);
        ming.setFont(font);
        ming.setOpaque(true);
        ming.setBackground(Color.white);
        mainPanel.add(ming);

        JLabel mi = new JLabel("密  文");
        mi.setBounds(70,550,100,50);
        mi.setFont(font);
        mi.setOpaque(true);
        mi.setBackground(Color.white);
        mainPanel.add(mi);

        //设置文本框以显示输入输出
        JTextField plainText = new JTextField(8);
        plainText.setBounds(200,50,200,50);
        mainPanel.add(plainText);
        plainText.setVisible(true);

        JTextField ecpText = new JTextField(8);
        ecpText.setBounds(200,150,200,50);
        mainPanel.add(ecpText);
        ecpText.setVisible(true);

        JTextField cipherText = new JTextField(8);
        cipherText.setBounds(200,250,200,50);
        mainPanel.add(cipherText);
        cipherText.setVisible(true);

        JTextField dcpText = new JTextField(8);
        dcpText.setBounds(200,350,200,50);
        mainPanel.add(dcpText);
        dcpText.setVisible(true);

        JTextField keyText = new JTextField(10);
        keyText.setBounds(200,650,200,50);
        mainPanel.add(keyText);
        keyText.setVisible(true);

        JTextField mingText = new JTextField(10);
        mingText.setBounds(200,450,200,50);
        mainPanel.add(mingText);
        mingText.setVisible(true);

        JTextField miText = new JTextField(10);
        miText.setBounds(200,550,200,50);
        mainPanel.add(miText);
        miText.setVisible(true);

        //添加按钮
        JButton encipher = new JButton("加密");
        encipher.setBounds(450,75,100,50);
        mainPanel.add(encipher);
        encipher.setFont(font);
        encipher.setBackground(Color.green);
        encipher.setVisible(true);

        JButton decrypt = new JButton("解密");
        decrypt.setBounds(450,325,100,50);
        mainPanel.add(decrypt);
        decrypt.setFont(font);
        decrypt.setBackground(Color.green);
        decrypt.setVisible(true);

        JButton dekey = new JButton("破解");
        dekey.setBounds(450,600,100,50);
        mainPanel.add(dekey);
        dekey.setVisible(true);
        dekey.setFont(font);
        dekey.setBackground(Color.green);



        encipher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWords=plainText.getText();
                char[] keyCharArray=keyWords.toCharArray();
                int[] intArray = new int[keyWords.length()]; // 创建一个整数数组，长度与字符串相同

                for (int i = 0; i < keyWords.length(); i++) {
                    // 将字符转换为整数并存储在整数数组中
                    intArray[i] = Character.getNumericValue(keyCharArray[i]);
                }
                SecretKey mingWen=new SecretKey(intArray);
                mingWen=SimplifiedDes.encrypt(secretKey,mingWen);
                ecpText.setText(mingWen.keyToString());
            }
        });
        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWords=cipherText.getText();
                char[] keyCharArray=keyWords.toCharArray();
                int[] intArray = new int[keyWords.length()]; // 创建一个整数数组，长度与字符串相同

                for (int i = 0; i < keyWords.length(); i++) {
                    // 将字符转换为整数并存储在整数数组中
                    intArray[i] = Character.getNumericValue(keyCharArray[i]);
                }
                SecretKey miWen=new SecretKey(intArray);
                miWen=SimplifiedDes.Decrypt(secretKey,miWen);
                dcpText.setText(miWen.keyToString());
            }
        });
        dekey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mingWen=mingText.getText();
                String miWen=miText.getText();
                char[] mingArray=mingWen.toCharArray();
                int[] mingIntArray = new int[mingWen.length()]; // 创建一个整数数组，长度与字符串相同

                for (int i = 0; i < mingWen.length(); i++) {
                    // 将字符转换为整数并存储在整数数组中
                    mingIntArray[i] = Character.getNumericValue(mingArray[i]);
                }
                char[] miArray=miWen.toCharArray();
                int[] miIntArray = new int[miWen.length()]; // 创建一个整数数组，长度与字符串相同

                for (int i = 0; i < miWen.length(); i++) {
                    // 将字符转换为整数并存储在整数数组中
                    miIntArray[i] = Character.getNumericValue(miArray[i]);
                }
                SecretKey mingwenKey=new SecretKey(mingIntArray);
                SecretKey miwenKey=new SecretKey(miIntArray);
                String key=SimplifiedDes.Hacker(mingwenKey,miwenKey);
                keyText.setText(key);
            }
        });
    }
}
