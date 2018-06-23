package com.caomingyu.filerename.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
 * �Զ����ļ�������
 *@ClassName: FileUtils
 *@Description: �Զ����ļ������� FileUtils
 *@author ������
 *@date 2018/6/21 23:36
 *@since jdk 8
 *@version v1.0
 */
public class FileUtils {
    /**
     * ���ļ������е��ļ�������ָ���ı�����
     *@Title: show
     *@Description: ���ļ������е��ļ�������ָ���ı�����
     *@param files �ļ�����
     *@param ta ָ���ı���
     *@author ������
     *@date 2018/6/21 23:37
     *@version v1.0
     */
    public static void show(File[]files, JTextArea ta){
        for (File f : files
                ) {
            ta.append(f.getName() + "\n");
        }
    }
    /**
     * ����������е��������ָ��JFrame��
     *@Title: addComponent
     *@Description: ����������е��齨����ָ��JFrame��
     *@param f ָ��JFrame
     *@param cs �������
     *@author ������
     *@date 2018/6/21 23:39
     *@version v1.0
     */
    public static void addComponent(JFrame f,Component[]cs){
        for (Component c:cs
             ) {
            f.add(c);
        }
    }
}
