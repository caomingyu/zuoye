package com.caomingyu.filerename.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
 * 自定义文件操作类
 *@ClassName: FileUtils
 *@Description: 自定义文件操作类 FileUtils
 *@author 曹明宇
 *@date 2018/6/21 23:36
 *@since jdk 8
 *@version v1.0
 */
public class FileUtils {
    /**
     * 将文件数组中的文件名加入指定文本域中
     *@Title: show
     *@Description: 将文件数组中的文件名加入指定文本域中
     *@param files 文件数组
     *@param ta 指定文本域
     *@author 曹明宇
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
     * 将组件数组中的组件加入指定JFrame中
     *@Title: addComponent
     *@Description: 将组件数组中的组建加入指定JFrame中
     *@param f 指定JFrame
     *@param cs 组件数组
     *@author 曹明宇
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
