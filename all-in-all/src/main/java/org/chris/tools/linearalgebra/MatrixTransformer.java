package org.chris.tools.linearalgebra;

public class MatrixTransformer {

	public static final int ROW = 1;
	public static final int COLUMN = 2;
	
	private boolean verbose = false;
	
	public MatrixTransformer() {
		
	}
	
	public MatrixTransformer(boolean verbose) {
		this.verbose = verbose;
	}
	
	public void shift(int[][] matrix, int dimension, int i, int j) {
		if (dimension == ROW) {
			int n = matrix[0].length;
			int[] temp = new int[n];
			for (int k = 0; k < n; k++) {
				temp[k] = matrix[i][k];
				matrix[i][k] = matrix[j][k];
				matrix[j][k] = temp[k];
			}
		} else if (dimension == COLUMN) {
			int m = matrix.length;
			int[] temp = new int[m];
			for (int k = 0; k < m; k++) {
				temp[k] = matrix[k][i];
				matrix[k][i] = matrix[k][j];
				matrix[k][j] = temp[k];
			}
		}
		if (verbose) {
			print(matrix);
		}
	}
	
	public void augment(int[][] matrix, int dimension, int i, int j, int k) {
		if (dimension == ROW) {
			int n = matrix[0].length;
			for (int r = 0; r < n; r++) {
				matrix[i][r] += matrix[j][r] * k;
			}
		} else if (dimension == COLUMN) {
			int m = matrix.length;
			for (int r = 0; r < m; r++) {
				matrix[r][i] += matrix[r][j] * k;
			}
		}
		if (verbose) {
			print(matrix);
		}
	}
	
	public void multiply(int[][] matrix, int dimension, int i, int k) {
		augment(matrix, dimension, i, i, k);
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
		int[][] matrix = {
			{2, 0, 1, 1, 0, 0}, {-1, -1, -2, 0, 1, 0}, {-3, 0, 1, 0, 0, 1}	
		};
		MatrixTransformer mt = new MatrixTransformer(true);
		mt.shift(matrix, ROW, 1, 2);
		mt.multiply(matrix, ROW, 1, -1);
		// mt.shift(matrix, ROW, 2, 1);
		// mt.shift(matrix, ROW, 1, 0);
		// mt.print(matrix);
		// System.out.println();
		// mt.augment(matrix, ROW, 1, 0, -3);
		// mt.augment(matrix, ROW, 2, 0, -3);
		// mt.augment(matrix, ROW, 3, 0, -2);
		// mt.print(matrix);
	}
}
