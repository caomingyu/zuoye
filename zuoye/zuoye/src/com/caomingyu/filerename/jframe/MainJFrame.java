package com.caomingyu.filerename.jframe;

import com.caomingyu.filerename.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 测试提交
 *
 * 面板类，显示面板
 * @author 曹明宇
 * @version v1.0
 * @ClassName: MainJFrame
 * @Description: 面板类，显示面板
 * @exception IOException
 * @date 2018/6/21 23:13
 * @see com.caomingyu.filerename.utils.FileUtils
 * @see FileUtils
 * @see FileUtils#addComponent(JFrame, Component[])
 * @see FileUtils#show(File[], JTextArea)
 * @see org.apache.commons.io.FileUtils
 * @see org.apache.commons.io.FileUtils#copyFile(File, File)
 * @since jdk 8
 */
public class MainJFrame {
    /**
     * 经正则表达式过滤后，所选文件夹下的文件数组
     */
    static File[] files;
    /**
     * 单列模式，类MainJFrame的instance
     */
    private static MainJFrame instance = new MainJFrame();


    /**
     * 返回单列模型生产的instance
     * @return MainJFrame实例
     * @Title: getInstance
     * @Description: 返回单列模型生产的instance
     * @auther 曹明宇
     * @date 2018/6/21 23:21
     * @version v1.0
     */
    public static MainJFrame getInstance() {
        return instance;
    }

    /**
     * 类MainJFrame的无参构造函数,生成JFrame,实现按钮监听，完成文件的选择、过滤、重命名功能
     * @Title: MainJFrame
     * @Description: 类MainJFrame的无参构造函数,生成JFrame,实现按钮监听，完成文件的选择、过滤、重命名功能
     * @auther 曹明宇
     * @date 2018/6/21 23:22
     * @version v1.0
     */
    private MainJFrame() {
        /**
         * 生成JFrame
         */
		 System.out.println();
        JFrame f = new JFrame("文件批量改名");
        f.setLayout(new FlowLayout());
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        /**
         * 构建JFrame组件
         */
        JButton bOpen = new JButton("打开文件");
        JButton bConvert = new JButton("重命名");
        JLabel l1 = new JLabel("自定义过滤规则");
        JTextField tfName1 = new JTextField("");
        tfName1.setText("请输入自定义过滤规则");
        tfName1.setPreferredSize(new Dimension(160, 30));
        JLabel l2 = new JLabel("自定义重命名规则");
        JTextField tfName2 = new JTextField("");
        tfName2.setText("如：输入“东师”,将生产东师-0、东师-1...");
        tfName2.setPreferredSize(new Dimension(160, 30));
        JLabel l3 = new JLabel("过滤前的文件：");
        JTextArea ta2 = new JTextArea();
        ta2.setPreferredSize(new Dimension(170, 150));
        JLabel l4 = new JLabel("过滤后的文件：");
        JTextArea ta1 = new JTextArea();
        ta1.setPreferredSize(new Dimension(170, 150));
        Component[] cs = new Component[]{l1, tfName1, l2, tfName2, l3, ta2, l4, ta1, bOpen, bConvert};
        /**
         * 调用类FileUtils下的addComponent方法，对生成的JFrame装载组件
         * @see FileUtils#addComponent(JFrame, Component[])
         */
        FileUtils.addComponent(f, cs);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(570, 270);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        /**
         * 按钮bOpen监听
         */
        bOpen.addActionListener(new ActionListener() {
            /**
             *根据JFileChooser选取文件夹,依据规定的正则表达式过滤文件
             * @param e 点击发生事件
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 每次事件发生，文本域ta1，ta2清空
                 */
                ta1.setText("");
                ta2.setText("");

                /**
                 * JFileChooser选取文件夹
                 */
                int returnVal = fc.showOpenDialog(f);
                File f3 = fc.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(f, "打开文件夹");
                }
                /**
                 * 获取正则表达式 regex
                 */
                String regex = tfName1.getText();

                /**
                 * 调用类FileUtils下的show方法，将选取的文件名显示在面板文本域
                 * @see FileUtils#show(File[], JTextArea)
                 */
                FileUtils.show(f3.listFiles(), ta2);
                /**
                 * 调用接口FilenameFilter,根据正则表达式 regex过滤文件
                 */
                files = f3.listFiles(new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(regex);

                    @Override
                    public boolean accept(File dir, String name) {
                        return pattern.matcher(name).matches();
                    }
                });
                FileUtils.show(files, ta1);
                //每次事件结束，输入框tfName1清空
                tfName1.setText("");
            }
        });

        /**
         * 按钮bConvert监听 根据输入的命名规则 对过滤后的文件重命名
         */
        bConvert.addActionListener(new ActionListener() {
            /**
             *
             * @param e 点击发生事件
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 获取命名规则
                 */
                String name = tfName2.getText();
                /**
                 * 文件重命名
                 */
                for (int i = 0; i < files.length; i++) {
                    File f1 = files[i];
                    String filename = f1.getAbsolutePath();
                    String fileEnd = filename.substring(filename.lastIndexOf("."), filename.length());
                    File parentsFile = f1.getParentFile();
                    File newFile = new File(parentsFile, name + "-" + i + fileEnd);
                    try {
                        /**
                         * 调用org.apache.commons.io.FileUtils下的copyFile方法，复制文件
                         * @see org.apache.commons.io.FileUtils#copyFile(File, File)
                         */
                        org.apache.commons.io.FileUtils.copyFile(f1, newFile);
                        //删除原本文件
                        f1.delete();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(f, "重命名结束");
                //每次事件结束，输入框tfName2清空
                tfName2.setText("");
                return;
            }
        });
    }
}
