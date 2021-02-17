package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverCrossword {

	private int vertical_row;
	private int horizontal_column;
	private String[] vertical;
	private String[] horizontal;

	private ResultMatrix resultMatrix;

	public SolverCrossword(int vertical_row, int horizontal_column, String[] vertical, String[] horizontal) {
		this.vertical_row = vertical_row;
		this.horizontal_column = horizontal_column;
		this.vertical = vertical;
		this.horizontal = horizontal;
	}

	public SolverCrossword(int vertical_row, int horizontal_column) {
		this.vertical_row = vertical_row;
		this.horizontal_column = horizontal_column;
	}

	public SolverCrossword(String[] vertical, String[] horizontal) {
		this.vertical = vertical;
		this.horizontal = horizontal;
	}

	public List<List<Byte>> solver() {

		List<List<Integer>> vert = vertHorizPullNumber(vertical);
		System.out.println(vert);
		List<List<Integer>> horz = vertHorizPullNumber(horizontal);
		System.out.println(horz);

		vertical_row = vert.size();
		horizontal_column = horz.size();

		byte[][] matrix = new byte[vertical_row][horizontal_column];
		resultMatrix = new ResultMatrix(matrix);

		int conditionExit = 0;
		while (conditionExit++ < 20) {
			resultMatrix.displacementVert(vert, horizontal_column);
			resultMatrix.displacementHorz(horz, vertical_row);
		}
		List<List<Byte>> returnTheList = resultMatrix.returnTheList();

		return returnTheList;
	}

	private List<List<Integer>> vertHorizPullNumber(String[] array) {

		List<List<Integer>> verticalAndHorozontal = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			String[] split = array[i].split(",");

			List<Integer> rowColumn = new ArrayList<>();
			for (int j = 0; j < split.length; j++) {
				rowColumn.add(Integer.parseInt(split[j].trim()));
			}
			verticalAndHorozontal.add(rowColumn);
		}
		return verticalAndHorozontal;
	}

	public int getVertical_row() {
		return vertical_row;
	}

	public void setVertical_row(int vertical_row) {
		this.vertical_row = vertical_row;
	}

	public int getHorizontal_column() {
		return horizontal_column;
	}

	public void setHorizontal_column(int horizontal_column) {
		this.horizontal_column = horizontal_column;
	}

	public String[] getVertical() {
		return vertical;
	}

	public void setVertical(String[] vertical) {
		this.vertical = vertical;
	}

	public String[] getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(String[] horizontal) {
		this.horizontal = horizontal;
	}

	@Override
	public String toString() {
		return "SolverCrossword [vertical_row=" + vertical_row + ", horizontal_column=" + horizontal_column
				+ ", vertical=" + Arrays.toString(vertical) + ", horizontal=" + Arrays.toString(horizontal) + "]";
	}

}
