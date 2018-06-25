package com.ace.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtils {
	/**
	 * 读取txt文件
	 * @param filePath 文件路径
	 * @param txtNum -1:读取全部
	 * @return
	 */
	public static String readTxtFile(String filePath,int txtNum){
		String str = "";
		try {
			String encoding = "gbk";
			File file = new File(filePath);
			if(file.isFile() && file.exists()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				int c = -1;
				while((c = bufferedReader.read()) != -1){
					char text = (char)c;
					if(txtNum > -1){
						if(str.length() > txtNum){
							break;
						}else{
							str += text;
						}
					}else{
						str += text;
					}
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
