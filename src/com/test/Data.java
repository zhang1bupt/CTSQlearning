package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Data {
	static Map statemap = new HashMap();
	static int num_constraint_sat =0;
	static int num_constraint_solve = 0;
	static int row = 0;
	static int col = 0;

	public static void main(String[] args) throws FileNotFoundException {

		//int num_constraint_solve = 0;//约束个数	
		//int num_constraint_sat = 0;
		int count_constaint = 1;
		String cons_str = "";
		ArrayList<String> array = new ArrayList<String>();
		
		ArrayList real_list = new ArrayList();
		real_list.add(2);
		real_list.add(2);
		//real_list.add(2);
		// 读取数据
		array = readFileByLines("data1.txt");
		num_constraint_solve = array.size();
		// Scanner sc = new Scanner(System.in);
		// while (count_constaint <= num_constraint_solve) {
		// cons_str = sc.nextLine();
		// array.add(cons_str);
		// count_constaint ++;
		// }//手动输入

		// String cons_str1 = "2*x1+2*x2+3*x3<10";
		// String cons_str2 = "x1*x2=2";
		// array.add(cons_str1);
		// array.add(cons_str2);//检测
		num_constraint_sat = constriantsatisfyNum(array, real_list);
		// System.out.println(array.size());
		System.out.println("------------the result below--------------");
		System.out.println("满足约束条件的个数为：" + num_constraint_sat);
		System.out.println("不满足约束条件的个数为：" + (num_constraint_solve - num_constraint_sat));
	}

	/**
	 * @category 函数用来判断满足约束变量的个数
	 * @param list
	 *            约束条件
	 * @param real_list
	 *            变量取值集合
	 * @return num 变量取值满足约束条件的个数
	 */
	public static int constriantsatisfyNum(ArrayList<String> list, ArrayList real_list) {

		int num = 0;
		int singleconstriant = 0;
		// ArrayList real_list = new ArrayList();
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
				char next_char = 0;
				//获取下一个字符
				if(j != current_str.length()-1){
				  next_char = current_str.charAt(j+1);
				}
				
				if (current_char == '+' || current_char == '-' || current_char == '*' || current_char == '='
						|| current_char == '<' || current_char == '>' || (current_char == '!' && next_char =='=')|| (current_char == '<'&&next_char=='=')|| (current_char == '>'&&next_char=='=')) {

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
			ChangeRealNum(var_list, real_list);
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

	/**
	 * @category 用来给变量赋值
	 * @param var_list
	 *            约束表达式中变量集合（x1，x2..）
	 * @param real_list
	 *            变量的实际取值
	 */
	// 改变Arraylist中变量为实际值：版本2
	public static void ChangeRealNum(ArrayList var_list, ArrayList real_list) {

		for (int temp = 0; temp < var_list.size(); temp++) {
			Object test = var_list.get(temp);
			System.out.println(test);
			// String str = test.toString();
			if (test.equals("x1")) {
				// System.out.println(test);
				var_list.set(temp, real_list.get(0));
			}
			if (test.equals("x2")) {
				var_list.set(temp, real_list.get(1));
			}
			if (test.equals("x3")) {
				var_list.set(temp, real_list.get(2));
			}
			if (test.equals("x4")) {
				var_list.set(temp, real_list.get(3));
			}
			if (test.equals("x5")) {
				var_list.set(temp, real_list.get(4));
			}
			if (test.equals("x6")) {
				var_list.set(temp, real_list.get(5));
			}
			if (test.equals("x7")) {
				var_list.set(temp, real_list.get(6));
			}
			if (test.equals("x8")) {
				var_list.set(temp, real_list.get(7));
			}
			if (test.equals("x9")) {
				var_list.set(temp, real_list.get(8));
			}
			if (test.equals("x10")) {
				var_list.set(temp, real_list.get(9));
			}

		}
	}

	// //改变Arraylist中变量为实际值
	// public static void ChangeRealNum(ArrayList var_list, int x1, int x2, int
	// x3, int x4, int x5) {
	//
	// for (int temp = 0; temp < var_list.size(); temp++) {
	// Object test = var_list.get(temp);
	// System.out.println(test);
	// // String str = test.toString();
	// if (test.equals("x1")) {
	// // System.out.println(test);
	// var_list.set(temp, x1);
	// }
	// if (test.equals("x2")) {
	// var_list.set(temp, x2);
	// }
	// if (test.equals("x3")) {
	// var_list.set(temp, x3);
	// }
	// if (test.equals("x4")) {
	// var_list.set(temp, x4);
	// }
	// if (test.equals("x5")) {
	// var_list.set(temp, x5);
	// }
	//
	// }
	// }
	/**
	 * @category 判定当前约束是否满足
	 * @param 当前约束集合
	 * 
	 */
	public static int judgeMent(ArrayList var_list) {
		int leftsum = 0;
		int left_num = 0;
		int right_num = 0;
		int cheng_resullt= 0;//乘号两边的乘积
	//	ArrayList cheng_result = new ArrayList();
		int list_size = var_list.size();
		int last_cons = Integer.parseInt(var_list.get(list_size - 1).toString());
		int flag = 0;
		//判断是否有特殊符号
		for (int temp = 0; temp < var_list.size(); temp++) {
			Object test = var_list.get(temp);
			String str = test.toString();
			if(str.equals("*")){
				flag = 1;
			}
			if(str.equals("^")){
				flag = 2;
			}
		}
		//flag = 0,代表约束表达式没有*号
		if( flag == 0){
			leftsum = Integer.parseInt((var_list.get(0)).toString());
			for (int temp = 0; temp < var_list.size(); temp++) {
				Object test = var_list.get(temp);
				String str = test.toString();	
				if (str.equals("+") ) {	
					leftsum = leftsum + Integer.parseInt((var_list.get(temp + 1)).toString());	
				}
				if (str.equals("-") ) {	
					leftsum = leftsum - Integer.parseInt((var_list.get(temp + 1)).toString());	
				}
			}
		}
		//有乘号的情况
		if(flag == 1){
			//遍历所有乘号，并求所有乘号的积
			int factor = 1;
			for (int temp = 0; temp < var_list.size(); temp++) {
				Object test = var_list.get(temp);
				String str = test.toString();
				if(str.toString().equals("-")){
					factor =  -1;
				}
				if (str.toString().equals("*")) {// 2*2+3*2=10 1+2=3
					// Object test1 = var_list.get(temp+1);
					right_num = Integer.parseInt((var_list.get(temp + 1)).toString());
					left_num = Integer.parseInt((var_list.get(temp - 1)).toString());
					cheng_resullt = factor * left_num * right_num;
					leftsum = leftsum + cheng_resullt;
					
		//			cheng_result.add(cheng_resullt);
				}
				if (str.equals("+") && !var_list.get(temp + 2).toString().equals("*")) {
					leftsum = leftsum + Integer.parseInt((var_list.get(temp + 1)).toString());
					}else {
						continue;
					}
				if(str.equals("-") && !var_list.get(temp + 2).toString().equals("*")){
					leftsum = leftsum - Integer.parseInt((var_list.get(temp + 1)).toString());
				}else{
					factor  = factor * (-1);
					continue;
					
				}
				factor  = factor * (-1);
			}
		}
		if(flag == 2){
			//遍历所有^，并求所有乘号的积
			for (int temp = 0; temp < var_list.size(); temp++) {
				Object test = var_list.get(temp);
				String str = test.toString();
				if (str.toString().equals("^")) {// 2*2+3*2=10 1+2=3
					// Object test1 = var_list.get(temp+1);
					right_num = Integer.parseInt((var_list.get(temp + 1)).toString());
					left_num = Integer.parseInt((var_list.get(temp - 1)).toString());
					cheng_resullt = (int) Math.pow(left_num, right_num);
					leftsum = leftsum + cheng_resullt;
		//			cheng_result.add(cheng_resullt);
				}
				if (str.equals("+") && !var_list.get(temp + 2).toString().equals("*")) {
					leftsum = leftsum + Integer.parseInt((var_list.get(temp + 1)).toString());
					}else{
						continue;
					}
			}
		}
		

//		for(int i = 0;i < cheng_result.size();i++){
//			cheng_resullt = cheng_resullt + (int)cheng_result.get(i);
//		}

		//单独处理没有乘号的情况
//		for (int temp = 0; temp < var_list.size(); temp++) {
//			Object test = var_list.get(temp);
//			String str = test.toString();
//			if (str.equals("+") && !var_list.get(temp + 2).toString().equals("*")) {
//				leftsum = leftsum + Integer.parseInt((var_list.get(temp + 1)).toString());
//				if ((var_list.get(temp + 2).toString().equals("=")) || var_list.get(temp + 2).toString().equals("!=")
//						|| var_list.get(temp + 2).toString().equals(">")
//						|| var_list.get(temp + 2).toString().equals("<")
//						|| var_list.get(temp + 2).toString().equals(">=")
//						|| var_list.get(temp + 2).toString().equals("<=")) {
//					leftsum = leftsum + Integer.parseInt((var_list.get(temp+1)).toString());
//				}
//			} else {
//				continue;
//			}
//
//		}
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
		// 左边计算值和右边常量，中间为=运算符
		if (var_list.get(list_size - 2).toString().equals(">=")) {
			if (leftsum != last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		// 左边计算值和右边常量，中间为=运算符
		if (var_list.get(list_size - 2).toString().equals("<=")) {
			if (leftsum != last_cons) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}

	// 约束条件以.TXT的格式输入
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

	/**
	 * @category 用来随机生成系数
	 */
	public static ArrayList buildRandomData(int constraint_num, int var_num) {
		ArrayList arr = new ArrayList();
		int[] var = new int[var_num];
		while (var_num >= 0) {

			var[var_num - 1] = (int) (Math.random() * 10); //
			var_num--;
		}

		return null;

	}

	/**
	 * @param1 range 数组列
	 * @param2 var_num 变量数量
	 * @param3 num_constraint_sat 约束满足条数
	 * @param4 num_constraint_solve 总约束个数
	 * 
	 */
	public static int[][] buildRewardMatrix(int min, int max, int var_num) {
		ArrayList real_list = new ArrayList();

		col = max - min + 1;// 列
		setCol(col);
		row = (int) Math.pow(col, var_num - 1);// 行
		setRow(row);
		int[][] grid = new int[row][col];
		int Reward = 0;
		// 变量数量小于1返回-1
		if (var_num < 1) {
			return null;
		}
		String filename = "data1.txt";
		// 变量数量为：1
		if (var_num == 1) {
			int index = 1;
			for (int i1 = 0; i1 < col; i1++) {
				real_list.add(min);
				String info = "S" + index;
				String state = "{x1:" + real_list.get(0) +  "}";
				grid[0][i1] = countReward(real_list,filename);
				statemap.put(info, state);
				real_list.remove(0);
				min++;
				index++;
			}
		}
		// 变量数量为：2
		if (var_num == 2) {
			int index = 1;
			int min1 = min;// 第一层循环的起始点
			for (int i1 = 0; i1 < col; i1++) {
				int min2 = min;// 第二层循环的起始点
				for (int i2 = 0; i2 < col; i2++) {
					if (real_list.isEmpty()) {
						real_list.add(min1);
					}
					real_list.add(min2);
					String info = "S" + index;
					String state = ":{x1:" + real_list.get(0) + ",x2:" + real_list.get(1) + "}";
					grid[i1][i2] = countReward(real_list,filename);
					statemap.put(info, state);
					real_list.remove(1);
					min2++;
					index++;
				}
				real_list.remove(0);
				min1++;
				real_list.add(min1);
			}
		}
		// 变量数量为：3
		if (var_num == 3) {
			int index = 1;
			int min1 = min;// 第一层循环的起始点
			for (int i1 = 0; i1 < col; i1++) {
				int min2 = min;// 第二层循环的起始点
				for (int i2 = 0; i2 < col; i2++) {
					int min3 = min;
					for (int i3 = 0; i3 < col; i3++) {
						if (real_list.isEmpty()) {
							real_list.add(min1);
						}
						real_list.add(min2);
						real_list.add(min3);
						String info = "S" + index;
						String state = ":{x1:" + real_list.get(0) + ",x2:" + real_list.get(1) +",x3:" + real_list.get(2) + "}";
						grid[5*i1+i2][i3] = countReward(real_list,filename);
						statemap.put(info, state);
						real_list.remove(2);
						min3++;
						index++;
					}
				}
				
				real_list.remove(1);
				min2++;
				real_list.add(min2);
			}
			real_list.remove(0);
			min1++;
			real_list.add(min1);
		}
		// 变量数量为：4
		if (var_num == 4) {
			int index = 1;
			int min1 = min;// 第一层循环的起始点
			for (int i1 = 0; i1 < col; i1++) {
				int min2 = min;// 第二层循环的起始点
				for (int i2 = 0; i2 < col; i2++) {
					int min3 = min;
					for (int i3 = 0; i3 < col; i3++) {
						int min4 = min;
						for (int i4 = 0; i4 < col; i4++) {
							if (real_list.isEmpty()) {
								real_list.add(min1);
							}
							real_list.add(min2);
							real_list.add(min3);
							real_list.add(min4);
							String info = "S" + index;
							String state = ":{x1:" + real_list.get(0) + ",x2:" + real_list.get(1) +",x3:" + real_list.get(2)+ ",x4:" + real_list.get(3) + "}";
							grid[5*(5*i1+i2)+i3][i4] = countReward(real_list,filename);
							statemap.put(info, state);
							real_list.remove(3);
							min4++;
							index++;
						}
					}
					real_list.remove(2);
					min3++;
					real_list.add(min3);
				}
				
				real_list.remove(1);
				min2++;
				real_list.add(min2);
			}
			real_list.remove(0);
			min1++;
			real_list.add(min1);
		}
		// 变量数量为：5
		if (var_num == 5) {
			int index = 1;
			int min1 = min;// 第一层循环的起始点
			for (int i1 = 0; i1 < col; i1++) {
				int min2 = min;// 第二层循环的起始点
				for (int i2 = 0; i2 < col; i2++) {
					int min3 = min;
					for (int i3 = 0; i3 < col; i3++) {
						int min4 = min;
						for (int i4 = 0; i4 < col; i4++) {
							int min5 = min;
							for (int i5 = 0; i5 < col; i5++) {
								if (real_list.isEmpty()) {
									real_list.add(min1);
								}
								real_list.add(min2);
								real_list.add(min3);
								real_list.add(min4);
								real_list.add(min5);
								String info = "S" + index;
								String state = ":{x1:" + real_list.get(0) + ",x2:" + real_list.get(1) +",x3:" + real_list.get(2)+ ",x4:" + real_list.get(3) + ",x5:" + real_list.get(4)+ "}";
								grid[5*(5*(5*i1+i2)+i3)+i4][i5] = countReward(real_list,filename);
								statemap.put(info, state);
								real_list.remove(4);
								min5++;
								index++;
							}
						}
						real_list.remove(3);
						min4++;
						real_list.add(min4);
					}
					real_list.remove(2);
					min3++;
					real_list.add(min3);
				}
				
				real_list.remove(1);
				min2++;
				real_list.add(min2);
			}
			real_list.remove(0);
			min1++;
			real_list.add(min1);
		}
		return grid;

	}

	/**
	 * @param x1..代表约束变量数
	 */
	public static int countReward(ArrayList real_list,String filename) {
		int reward = 0;
//		int num_constraint_sat = 0;
		int num_constraint_unsat = 0;
		ArrayList<String> array = new ArrayList<String>();
		// 读取数据
		array = readFileByLines(filename);
		num_constraint_solve = array.size();
		num_constraint_sat = constriantsatisfyNum(array, real_list);
		num_constraint_unsat = num_constraint_solve - num_constraint_sat;
		reward = num_constraint_sat * 5 + (num_constraint_unsat) * (-5);

		return reward;

	}

	// 打印Reward矩阵
	public static void printRewardMatrix(int[][] grid) {
		int m_row = grid[0].length;
		int m_col = grid.length;
		System.out.println("列" + m_row);
		System.out.println("行" + m_col);
		System.out.println("State of Rewards");
		for (int i = 0; i < m_col; i++) {
			for (int j = 0; j < m_row; j++) {
				System.out.print(grid[i][j] + "\t");

			}
			System.out.println();
		}
	}

	// 打印对应的状态集合
	public static void printRewardState() {
		System.out.println();
		System.out.println("---------遍历所有的状态集合----------------");
		Iterator iter = statemap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			String out = "输出info：" + key + "状态对应Reward：" + val;
			System.out.println(out);
		}
	}
	public static int getNum_constraint_solve() {
		return num_constraint_solve;
	}

	public static void setNum_constraint_solve(int num_constraint_solve) {
		Data.num_constraint_solve = num_constraint_solve;
	}
	public int getNum_constraint_sat() {
		return num_constraint_sat;
	}

	public void setNum_constraint_sat(int num_constraint_sat) {
		this.num_constraint_sat = num_constraint_sat;
	}
	public static int getRow() {
		return row;
	}

	public static void setRow(int row) {
		Data.row = row;
	}

	public static int getCol() {
		return col;
	}

	public static void setCol(int col) {
		Data.col = col;
	}

	public static Map getStatemap() {
		return statemap;
	}

	public static void setStatemap(Map statemap) {
		Data.statemap = statemap;
	}
	
}
