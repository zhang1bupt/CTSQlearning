package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {

	public static void main(String[] args) throws FileNotFoundException {

		int num_constraint_solve = 2;
		int num_constraint_sat = 0;
		int count_constaint = 1;
		String cons_str = "";
		ArrayList<String> array = new ArrayList<String>();
		//读取数据
		array = readFileByLines("data.txt");
		
//		Scanner sc = new Scanner(System.in);
//		while (count_constaint <= num_constraint_solve) {
//			cons_str = sc.nextLine();
//			array.add(cons_str);
//			count_constaint ++;
//		}//手动输入
		
//		 String cons_str1 = "2*x1+2*x2+3*x3<10";
//		 String cons_str2 = "x1*x2=2";
//		 array.add(cons_str1);
//		 array.add(cons_str2);//检测
		num_constraint_sat = constriantsatisfyNum(array, 1, 2, 1, 2, 1);
		System.out.println("------------the result below--------------");
		System.out.println("满足约束条件的个数为：" + num_constraint_sat);
		System.out.println("不满足约束条件的个数为：" + (num_constraint_solve-num_constraint_sat));
	}

	public static int constriantsatisfyNum(ArrayList<String> list, int x1, int x2, int x3, int x4, int x5) {

		int num = 0;
		int singleconstriant = 0;
		// 处理所有的约束条件
		for (int i = 0; i < list.size(); i++) {

			String current_str = list.get(i);// 获得当前处理约束条件
			System.out.println(current_str);// 打印当前约束条件
			if (current_str == null) {
				return 0;
			}
			String var_str = "";
			ArrayList var_list = new ArrayList<>();
			for (int j = 0; j < current_str.length(); j++) {
				char current_char = current_str.charAt(j);
				if (current_char == '+' || current_char == '-' || current_char == '*' || current_char == '='
						|| current_char == '<' || current_char == '>') {

					var_list.add(var_str);
					var_list.add(current_char);
					var_str = "";

				} else {
					var_str = var_str + current_char;
					// System.out.println(var_str);
					if (current_str.length() - 1 == j) {
						var_list.add(var_str);
					}
				}

			}
			System.out.println("~~~~~~~~~~~~分割线~~~~~~~~~~~~~~");
			// for( int temp=0;temp < var_list.size(); temp++){
			// Object test = var_list.get(temp);
			// System.out.println(test);
			// //String str = test.toString();
			// if(test.equals("x1")){
			// // System.out.println(test);
			// var_list.set(temp, x1);
			// }
			// if(test.equals("x2")){
			// var_list.set(temp, is);
			// }
			// if(test.equals("x3") ){
			// var_list.set(temp, is);
			// }
			// if(test.equals("x4")){
			// var_list.set(temp, is);
			// }
			// if(test.equals("x5")){
			// var_list.set(temp, is);
			// }
			//
			// }
			// System.out.println("~~~~~~~~~~~~分割线~~~~~~~~~~~~~~");
			// for( int temp=0;temp < var_list.size(); temp++){
			// Object test = var_list.get(temp);
			// System.out.println(test);
			// }
			ChangeRealNum(var_list, x1, x2, x3, x4, x5);
			System.out.println("~~~~~~~~~~~~分割线~~~~~~~~~~~~~~");
			singleconstriant = judgeMent(var_list);
			for (int temp = 0; temp < var_list.size(); temp++) {
				Object test = var_list.get(temp);
				System.out.println(test);
			}
			num = singleconstriant + num;
		}

		return num;

	}

	public static void ChangeRealNum(ArrayList var_list, int x1, int x2, int x3, int x4, int x5) {

		for (int temp = 0; temp < var_list.size(); temp++) {
			Object test = var_list.get(temp);
			System.out.println(test);
			// String str = test.toString();
			if (test.equals("x1")) {
				// System.out.println(test);
				var_list.set(temp, x1);
			}
			if (test.equals("x2")) {
				var_list.set(temp, x2);
			}
			if (test.equals("x3")) {
				var_list.set(temp, x3);
			}
			if (test.equals("x4")) {
				var_list.set(temp, x4);
			}
			if (test.equals("x5")) {
				var_list.set(temp, x5);
			}

		}
	}

	public static int judgeMent(ArrayList var_list) {
		int leftsum = 0;
		int left_num = 0;
		int right_num = 0;
		int cheng_result = 0;
		int list_size = var_list.size();
		int last_cons = Integer.parseInt(var_list.get(list_size - 1).toString());
		for (int temp = 0; temp < var_list.size(); temp++) {

			Object test = var_list.get(temp);
			// Object test_left2 = var_list.get(temp+2);
			// if(temp >= 3){
			// Object test_right2 = var_list.get(temp-2);
			// }&& temp > 2
			String str = test.toString();
			if (str.toString().equals("*")) {// 2*2+3*2=10 1+2=3
				// Object test1 = var_list.get(temp+1);
				right_num = Integer.parseInt((var_list.get(temp - 1)).toString());
				left_num = Integer.parseInt((var_list.get(temp + 1)).toString());
				cheng_result = left_num * right_num;
				leftsum = cheng_result + leftsum;
			}
			if (str.equals("+") && !var_list.get(temp + 2).toString().equals("*")) {
				leftsum = leftsum + Integer.parseInt((var_list.get(temp + 1)).toString());
			} else {
				continue;
			}

		}
		System.out.println("~~~~~~~~~~~~左边计算结果~~~~~~~~~~~~~~");
		System.out.println(leftsum);
		System.out.println("~~~~~~~~~~~~左边计算结果~~~~~~~~~~~~~~");
		// 左边计算值和右边常量，中间为<运算符
		if (var_list.get(list_size - 2).toString().equals("<")) {
			if (leftsum < last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		// 左边计算值和右边常量，中间为>运算符
		if (var_list.get(list_size - 2).toString().equals(">")) {
			if (leftsum > last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		// 左边计算值和右边常量，中间为>运算符
		if (var_list.get(list_size - 2).toString().equals(">")) {
			if (leftsum > last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		// 左边计算值和右边常量，中间为=运算符
		if (var_list.get(list_size - 2).toString().equals("=")) {
			if (leftsum == last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		// 左边计算值和右边常量，中间为=运算符
		if (var_list.get(list_size - 2).toString().equals("!=")) {
			if (leftsum != last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
	//约束条件以.TXT的格式输入
	public static ArrayList readFileByLines(String fileName) {
	    File file = new File(fileName);
	    ArrayList list = new ArrayList();
	    BufferedReader reader = null;
	    try {
	        System.out.println("以行为单位读取文件内容，一次读一整行：");
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // 一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null) {
	            // 显示行号
	            System.out.println("line " + line + ": " + tempString);
	            list.add(tempString);
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
		return list;
	}
	public static ArrayList buildRandomData(int constraint_num,int var_num){
		ArrayList arr = new ArrayList();
		int [] var = new int[var_num];
		while(var_num >= 0){
			
		var[var_num-1] = (int) (Math.random() * 10); //
			var_num--;
		}
		
		
		return null;
		
	}
}

