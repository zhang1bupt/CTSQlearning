package com.test;

import java.util.ArrayList;

public class onlytest {
	public static void main(String[] args) {
		int [][] grid = null;
		Data da = new Data();
		grid =da.buildRewardMatrix(-3, 3, 1);
		da.printRewardMatrix(grid);
		da.printRewardState();

}
	}
