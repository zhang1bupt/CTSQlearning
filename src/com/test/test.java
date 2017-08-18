package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) throws IOException {
		//readFileByLines("data.txt");
		ArrayList arr = new ArrayList();
		arr = buildRandomData(2,2);
		buildDataFile("/data1.txt", arr);
		//buildDataFile("E://data1.txt", arr);
		
	}
	
	public static ArrayList buildRandomData(int constraint_num,int var_num){
		ArrayList arr = new ArrayList();
		int [] var = new int[var_num];
		String [] var_str = {"x1","x2","x3","x4","x5"};
		String [] operator = {"+","-"};
		String [] operator_last = {"=","!=","<","<=",">",">="};
		int var_str_index = (int) (Math.random() * var_str.length);
		int operator_index = (int) (Math.random() * operator.length);
		int operator_last_index = (int) (Math.random() * operator_last.length);
		for(int i = 0;i < constraint_num;i++){
			int currentvar = 0;
			String build = "";
			int con_int = (int) (Math.random() * 10);
			while(currentvar < var_num){	
				//int var_int = (int) (Math.random() * 10);	
				var[currentvar] = (int) (Math.random() * 10); //
				currentvar++;
				}
			currentvar = 0; 
			for(int j = 0;j < var.length; j++){
				//System.out.println(i1);
				build = build + (var[j] + "*") + var_str[j] + operator[operator_index];
			}
			build = build.substring(0, build.length()-1);
			build = build + operator_last[operator_last_index] + con_int;
			arr.add(build);

		}
//		for(int temp = 0;temp < arr.size(); temp++){
//			System.out.println(arr.get(temp));
//		}
		
		return arr;
		
	}
	public static void  buildDataFile(String filename,ArrayList arr) throws IOException{
		//filename = "data1.txt";
		File file =new File(filename);
		if(!file.exists())
		{
			file.createNewFile();
		}
		FileOutputStream fs = new FileOutputStream(file,false);//��true��Ϊfalse�������Ը��ǵķ�ʽд�����ݡ�
		//OutputStreamWriter out =new OutputStreamWriter(fs,"utf-8");//???? ������û������
		PrintStream p = new PrintStream(fs);
		for(int temp = 0;temp < arr.size(); temp++){
			p.println(arr.get(temp));
			//System.out.println(arr.get(temp));
		}
		p.close();
	}
	/**
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
