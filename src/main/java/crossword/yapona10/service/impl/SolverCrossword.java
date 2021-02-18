/**
 * Japanese crossword puzzle solver
 * My own project
 *
 * Class crossword.yapona10.service.impl.SolverCrossword  - service implementation layer
 * This class implements the basic logic
 *
 * @author Vasil Kozogin
 *
 */

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

	/* Main method.
	method implements control of all logic from reading of data, solving of a  
	crossword puzzle, formation of result for jsp page
	*/
	public List<List<Byte>> solver() {

		List<List<Integer>> vert = vertHorizPullNumber(vertical);		
		List<List<Integer>> horz = vertHorizPullNumber(horizontal);		

		vertical_row = vert.size();
		horizontal_column = horz.size();

		byte[][] matrix = new byte[vertical_row][horizontal_column];
		resultMatrix = new ResultMatrix(matrix);
		int countAllCombination = 0;
		int countAllCombinationPrevious = 0;
		
		boolean conditionExit = true;
		while (conditionExit) {
			resultMatrix.setCountSuccessfulCombination(0);
				resultMatrix.displacementVert(vert, horizontal_column);
				resultMatrix.displacementHorz(horz, vertical_row);
			countAllCombination = resultMatrix.getCountSuccessfulCombination();
					
			if(countAllCombination == countAllCombinationPrevious) {
				conditionExit = false;
			}			
			countAllCombinationPrevious = countAllCombination;
		}
		List<List<Byte>> returnTheList = resultMatrix.returnTheList();

		return returnTheList;
	}

	//* parse array data from form or DB into list Integer*/
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
