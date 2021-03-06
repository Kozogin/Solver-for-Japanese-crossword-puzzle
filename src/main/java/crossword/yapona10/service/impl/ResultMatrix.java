/**
 * Japanese crossword puzzle solver
 * My own project
 *
 * Class crossword.yapona10.service.impl.ResultMatrix  - service implementation layer
 * Methods for the solver
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

import crossword.yapona10.domain.ItemStatus;

public class ResultMatrix {

	/* matrix for data processing */
	private byte[][] result;

	/*
	 * counter iteration cycle, considers all combinations are not empty in the
	 * vertical and horizontal processing
	 */
	private int countSuccessfulCombination;

	public ResultMatrix(byte[][] result) {
		this.result = result;
		countSuccessfulCombination = 0;
	}

	/* array to list */
	public List<List<Byte>> returnTheList() {

		List<List<Byte>> resultShow = new ArrayList<>();

		for (int i = 0; i < result.length; i++) {
			List<Byte> row = new ArrayList<>();
			for (int j = 0; j < result[i].length; j++) {
				row.add(result[i][j]);
			}
			resultShow.add(row);
		}
		return resultShow;
	}

	/* starts the mechanism displacement elements all the rows */
	public byte[][] displacementVert(List<List<Integer>> rows, int sizeColumn) {
		
		displacementVertAndHorz(rows, sizeColumn, "vertical");
		
		return result;
	}

	/* starts the mechanism displacement elements all the column */
	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		displacementVertAndHorz(columns, sizeRow, "horizontal");

		return result;
	}
	
	/* processing lines for the presence of overlays*/
	private byte[][] displacementVertAndHorz(List<List<Integer>> rowOrColumns, int sizeColumnOrRow, String direction) {

		byte[] handler = new byte[sizeColumnOrRow];
		byte[] handlerBase = null;
		byte[] handlerLight = new byte[sizeColumnOrRow];
		int summElement;

		List<Integer> indents = new ArrayList<>();

		for (int column = 0; column < rowOrColumns.size(); column++) {

			handlerLight = fillLight(handlerLight);
			if (direction == "vertical") {
				handlerBase = getRowFromMatrix(column, sizeColumnOrRow);
			}
			if (direction == "horizontal") {
				handlerBase = getColumnFromMatrix(column, sizeColumnOrRow);
			}			
			
			int[] indent;
			summElement = rowOrColumns.get(column).stream().reduce((left, right) -> left + right).get();

			SearchCombinations searchCombinations = new SearchCombinations(rowOrColumns.get(column).size(), summElement,
					sizeColumnOrRow);

			searchCombinations.countCombinations();

			do {

				indent = searchCombinations.getIndent();
				indents.clear();
				indents = createLastIndents(rowOrColumns.get(column), sizeColumnOrRow, indent);
				handler = displacement(rowOrColumns.get(column), sizeColumnOrRow, indents);

				if (needEquals(handlerBase, handler)) {
					darkCheck(handlerBase, handler);
					lightCheck(handlerLight, handler);
				}

			} while (searchCombinations.countCombinations());

			handlerBase = blackening(handlerBase, summElement);
			handlerBase = whitening(handlerBase, handlerLight);

			if (direction == "vertical") {
				for (int i = 0; i < handlerBase.length; i++) {
					result[column][i] = handlerBase[i];
				}
			}
			if (direction == "horizontal") {
				for (int i = 0; i < handlerBase.length; i++) {
					result[i][column] = handlerBase[i];
				}
			}			
		}
		return result;
	}

	
	/*
	 * calculates the last indent, return array indents, [] displace parameter - all
	 * the combination indents
	 */
	private List<Integer> createLastIndents(List<Integer> rowOrColumn, int sizeColumnOrRow, int[] displace) {

		List<Integer> indents = new ArrayList<>();
		int elementLength = 0;
		int summAll = 0;
		int indent = 0;

		indents.add(displace[0]);
		summAll += indents.get(0);

		for (int indElement = 0; indElement < rowOrColumn.size(); indElement++) {

			elementLength = rowOrColumn.get(indElement);
			summAll += elementLength;

			indent = 1;
			if (indElement < rowOrColumn.size() - 1) {
				indent = displace[indElement + 1] + 1;
			}
			summAll += indent;

			if (indElement == rowOrColumn.size() - 1) {
				indent = sizeColumnOrRow - summAll + 1;
			}
			indents.add(indent);
		}
		return indents;
	}

	/*
	 * calculate line matrix, uses List<Integer> indent for generated all the
	 * combination position, if indent = {0,0 ... 0}, elements are positioned with a
	 * single indent from the beginning
	 */
	private byte[] displacement(List<Integer> rowOrColumn, int sizeColumnOrRow, List<Integer> indents) {

		byte[] handler = new byte[sizeColumnOrRow];

		// pass along the line vertical or horizontal push into the matrix from the
		// beginning with a single gap

		int lengthElement = 0;
		int lengthIndent = 0;
		int indMatrix = 0;
		int indexExit = indents.get(0);

		if (indents.get(0) != 0) {
			while (indMatrix < indexExit) {
				handler[indMatrix++] = (byte) ItemStatus.LIGHT.ordinal();
			}
		}

		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {

			lengthElement = rowOrColumn.get(indInnerList);
			
			lengthIndent = indents.get(indInnerList + 1);
			indexExit = indMatrix + lengthElement;

			while (indMatrix < indexExit) {
				handler[indMatrix++] = (byte) ItemStatus.DARK.ordinal();
			}

			indexExit += lengthIndent;

			if (indMatrix < sizeColumnOrRow || indexExit < sizeColumnOrRow) {
				while (indMatrix < indexExit) {
					handler[indMatrix++] = (byte) ItemStatus.LIGHT.ordinal();
				}
			}
		}
		return handler;
	}

	private byte[] getRowFromMatrix(int row, int sizeColumn) {

		byte[] handlerBase = new byte[sizeColumn];
		for (int i = 0; i < handlerBase.length; i++) {
			handlerBase[i] = result[row][i];
			if (handlerBase[i] != ItemStatus.BLACK.ordinal() && handlerBase[i] != ItemStatus.WHITE.ordinal()) {
				handlerBase[i] = (byte) ItemStatus.DARK.ordinal();
			}
		}
		return handlerBase;
	}

	private byte[] getColumnFromMatrix(int column, int sizeRow) {

		byte[] handlerBase = new byte[sizeRow];
		for (int i = 0; i < handlerBase.length; i++) {
			handlerBase[i] = result[i][column];
			if (handlerBase[i] != ItemStatus.BLACK.ordinal() && handlerBase[i] != ItemStatus.WHITE.ordinal()) {
				handlerBase[i] = (byte) ItemStatus.DARK.ordinal();
			}
		}
		return handlerBase;
	}

	/*
	 * if generated line rightly superimposed on the base line, check on black
	 * element
	 */
	private boolean needEquals(byte[] base, byte[] handler) {

		for (int i = 0; i < base.length; i++) {
			if (base[i] == ItemStatus.BLACK.ordinal() && handler[i] != ItemStatus.DARK.ordinal()) {
				return false;
			}
			if (base[i] == ItemStatus.WHITE.ordinal() && handler[i] != ItemStatus.LIGHT.ordinal()) {
				return false;
			}
		}
		return true;
	}

	private byte[] fillLight(byte[] space) {

		for (int i = 0; i < space.length; i++) {
			space[i] = (byte) ItemStatus.LIGHT.ordinal();
		}
		return space;
	}

	/*
	 * if generated line rightly superimposed on the base line, check on black
	 * element
	 */
	private byte[] darkCheck(byte[] base, byte[] handler) {

		countSuccessfulCombination++;

		for (int i = 0; i < base.length; i++) {
			switch (base[i]) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				base[i] = handler[i];
				break;
			case 4:
				if (handler[i] != ItemStatus.DARK.ordinal()) {
					base[i] = handler[i];
				}
				break;
			default:
				break;
			}
		}
		return base;
	}

	/*
	 * if generated line rightly superimposed on the base line, check on white
	 * element
	 */
	private byte[] lightCheck(byte[] handlerLight, byte[] handler) {

		for (int i = 0; i < handlerLight.length; i++) {
			if (handler[i] == ItemStatus.DARK.ordinal()) {
				handlerLight[i] = (byte) ItemStatus.DARK.ordinal();
			}
		}
		return handlerLight;
	}

	/* approve base line, after all the combination */
	private byte[] blackening(byte[] base, int summElement) {

		int countBlackElements = 0;

		for (int i = 0; i < base.length; i++) {
			if (base[i] == ItemStatus.DARK.ordinal()) {
				base[i] = (byte) ItemStatus.BLACK.ordinal();
			}
			if (base[i] == ItemStatus.BLACK.ordinal()) {
				countBlackElements++;
			}
		}

		if (countBlackElements == summElement) {
			for (int i = 0; i < base.length; i++) {
				if (base[i] != ItemStatus.BLACK.ordinal()) {
					base[i] = (byte) ItemStatus.WHITE.ordinal();
				}
			}
		}
		return base;
	}

	/* overlay white line on base, after all the combination*/
	private byte[] whitening(byte[] base, byte[] handlerLight) {

		for (int i = 0; i < base.length; i++) {
			if (handlerLight[i] == ItemStatus.LIGHT.ordinal()) {
				base[i] = (byte) ItemStatus.WHITE.ordinal();
			}
		}
		return base;
	}

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

	public int getCountSuccessfulCombination() {
		return countSuccessfulCombination;
	}

	public void setCountSuccessfulCombination(int countSuccessfulCombination) {
		this.countSuccessfulCombination = countSuccessfulCombination;
	}

}
