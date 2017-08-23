package com.test;

import java.util.ArrayList;

public class onlytest {
	public static void main(String[] args) {
		int [][] grid = null;
		Data da = new Data();
		grid =da.buildRewardMatrix(-2, 2, 3);
		da.printRewardMatrix(grid);
		da.printRewardState();

}
	}
