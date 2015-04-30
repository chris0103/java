package org.chris.tools.linearalgebra;

public class MatrixCalculation {

	public int[][] multiply(int[][] mtx1, int[][] mtx2) {
		int m = mtx1.length;
		int n1 = mtx1[0].length;
		int n2 = mtx2.length;
		int s = mtx2[0].length;
		assert n1 == n2;
		int[][] product = new int[m][s];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < s; j++) {
				int sum = 0;
				for (int k = 0; k < n1; k++) {
					sum += mtx1[i][k] * mtx2[k][j];
				}
				product[i][j] = sum;
			}
		}
		return product;
	}
	
	public void print(int[][] matrix) {
		for (int[] row : matrix) {
			for (int data : row) {
				System.out.print(data + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		MatrixCalculation mc = new MatrixCalculation();
		int[][] matrix1 = {
			{1, 0, -1}, {-7, -5, -3}, {3, 0, 2}
		};
		int[][] matrix2 = {
			{4, 2, 3}, {1, 1, 0}, {-1, 2, 3}
		};
		int[][] ret = mc.multiply(matrix1, matrix2);
		mc.print(ret);
	}
}
