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
 * �����ύ
 *
 * ����࣬��ʾ���
 * @author ������
 * @version v1.0
 * @ClassName: MainJFrame
 * @Description: ����࣬��ʾ���
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
     * ��������ʽ���˺���ѡ�ļ����µ��ļ�����
     */
    static File[] files;
    /**
     * ����ģʽ����MainJFrame��instance
     */
    private static MainJFrame instance = new MainJFrame();


    /**
     * ���ص���ģ��������instance
     * @return MainJFrameʵ��
     * @Title: getInstance
     * @Description: ���ص���ģ��������instance
     * @auther ������
     * @date 2018/6/21 23:21
     * @version v1.0
     */
    public static MainJFrame getInstance() {
        return instance;
    }

    /**
     * ��MainJFrame���޲ι��캯��,����JFrame,ʵ�ְ�ť����������ļ���ѡ�񡢹��ˡ�����������
     * @Title: MainJFrame
     * @Description: ��MainJFrame���޲ι��캯��,����JFrame,ʵ�ְ�ť����������ļ���ѡ�񡢹��ˡ�����������
     * @auther ������
     * @date 2018/6/21 23:22
     * @version v1.0
     */
    private MainJFrame() {
        /**
         * ����JFrame
         */
		 System.out.println();
        JFrame f = new JFrame("�ļ���������");
        f.setLayout(new FlowLayout());
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        /**
         * ����JFrame���
         */
        JButton bOpen = new JButton("���ļ�");
        JButton bConvert = new JButton("������");
        JLabel l1 = new JLabel("�Զ�����˹���");
        JTextField tfName1 = new JTextField("");
        tfName1.setText("�������Զ�����˹���");
        tfName1.setPreferredSize(new Dimension(160, 30));
        JLabel l2 = new JLabel("�Զ�������������");
        JTextField tfName2 = new JTextField("");
        tfName2.setText("�磺���롰��ʦ��,��������ʦ-0����ʦ-1...");
        tfName2.setPreferredSize(new Dimension(160, 30));
        JLabel l3 = new JLabel("����ǰ���ļ���");
        JTextArea ta2 = new JTextArea();
        ta2.setPreferredSize(new Dimension(170, 150));
        JLabel l4 = new JLabel("���˺���ļ���");
        JTextArea ta1 = new JTextArea();
        ta1.setPreferredSize(new Dimension(170, 150));
        Component[] cs = new Component[]{l1, tfName1, l2, tfName2, l3, ta2, l4, ta1, bOpen, bConvert};
        /**
         * ������FileUtils�µ�addComponent�����������ɵ�JFrameװ�����
         * @see FileUtils#addComponent(JFrame, Component[])
         */
        FileUtils.addComponent(f, cs);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(570, 270);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        /**
         * ��ťbOpen����
         */
        bOpen.addActionListener(new ActionListener() {
            /**
             *����JFileChooserѡȡ�ļ���,���ݹ涨��������ʽ�����ļ�
             * @param e ��������¼�
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * ÿ���¼��������ı���ta1��ta2���
                 */
                ta1.setText("");
                ta2.setText("");

                /**
                 * JFileChooserѡȡ�ļ���
                 */
                int returnVal = fc.showOpenDialog(f);
                File f3 = fc.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(f, "���ļ���");
                }
                /**
                 * ��ȡ������ʽ regex
                 */
                String regex = tfName1.getText();

                /**
                 * ������FileUtils�µ�show��������ѡȡ���ļ�����ʾ������ı���
                 * @see FileUtils#show(File[], JTextArea)
                 */
                FileUtils.show(f3.listFiles(), ta2);
                /**
                 * ���ýӿ�FilenameFilter,����������ʽ regex�����ļ�
                 */
                files = f3.listFiles(new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(regex);

                    @Override
                    public boolean accept(File dir, String name) {
                        return pattern.matcher(name).matches();
                    }
                });
                FileUtils.show(files, ta1);
                //ÿ���¼������������tfName1���
                tfName1.setText("");
            }
        });

        /**
         * ��ťbConvert���� ����������������� �Թ��˺���ļ�������
         */
        bConvert.addActionListener(new ActionListener() {
            /**
             *
             * @param e ��������¼�
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * ��ȡ��������
                 */
                String name = tfName2.getText();
                /**
                 * �ļ�������
                 */
                for (int i = 0; i < files.length; i++) {
                    File f1 = files[i];
                    String filename = f1.getAbsolutePath();
                    String fileEnd = filename.substring(filename.lastIndexOf("."), filename.length());
                    File parentsFile = f1.getParentFile();
                    File newFile = new File(parentsFile, name + "-" + i + fileEnd);
                    try {
                        /**
                         * ����org.apache.commons.io.FileUtils�µ�copyFile�����������ļ�
                         * @see org.apache.commons.io.FileUtils#copyFile(File, File)
                         */
                        org.apache.commons.io.FileUtils.copyFile(f1, newFile);
                        //ɾ��ԭ���ļ�
                        f1.delete();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(f, "����������");
                //ÿ���¼������������tfName2���
                tfName2.setText("");
                return;
            }
        });
    }
}
