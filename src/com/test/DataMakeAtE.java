package com.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.ArrayList;

public class DataMakeAtE {
	public static void main(String[] args) throws IOException {
		int filenum = 1;
		DataMake dm = new DataMake();	
		ArrayList arr = new ArrayList();
		//��һ��������Լ���������ڶ������Ǳ�������
		arr = dm.buildRandomData(3,3);
		//����1000������
		while(filenum <= 1000){
			dm.buildDataFile("E://data//" + "data" + filenum+".txt", arr);
			filenum ++;
		}
		
		//buildDataFile("E://data1.txt", arr);
	}

	
}
