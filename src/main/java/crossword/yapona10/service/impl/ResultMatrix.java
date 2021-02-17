package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import crossword.yapona10.domain.ItemStatus;

public class ResultMatrix {

	private byte[][] result;
	private int countSuccessfulCombination;

	public ResultMatrix(byte[][] result) {
		this.result = result;
		countSuccessfulCombination = 0;
	}

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

	private byte[] fillLight(byte[] space) {

		for (int i = 0; i < space.length; i++) {
			space[i] = (byte) ItemStatus.LIGHT.ordinal();
		}
		return space;
	}

	public byte[][] displacementVert(List<List<Integer>> rows, int sizeColumn) {

		byte[] handler = new byte[sizeColumn];
		byte[] handlerBase;
		byte[] handlerLight = new byte[sizeColumn];

		int summElement;

		List<Integer> indents = new ArrayList<>();

		for (int row = 0; row < rows.size(); row++) {

			System.out.println(
					"----------------------new row " + row + " -----------------------------------------------");

			handlerLight = fillLight(handlerLight);
			handlerBase = getRowFromMatrix(row, sizeColumn);
			int[] indent;
			summElement = rows.get(row).stream().reduce((left, right) -> left + right).get();

			SearchCombinations searchCombinations = new SearchCombinations(rows.get(row).size(), summElement,
					sizeColumn);

			searchCombinations.countCombinations();

			do {

				indent = searchCombinations.getIndent();
				indents.clear();
				indents = createIndents(rows.get(row), sizeColumn, indent);
				handler = displacement(rows.get(row), sizeColumn, indents);

				if (needEquals(handlerBase, handler)) {
					equalsLine(handlerBase, handler);
					equalsLight(handlerLight, handler);
				}

			} while (searchCombinations.countCombinations());

			handlerBase = approve(handlerBase, summElement);
			handlerBase = equalsBaseLight(handlerBase, handlerLight);

			for (int i = 0; i < handlerBase.length; i++) {
				result[row][i] = handlerBase[i];
			}

		}
		return result;
	}

	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];
		byte[] handlerBase;
		byte[] handlerLight = new byte[sizeRow];

		int summElement;

		List<Integer> indents = new ArrayList<>();

		for (int column = 0; column < columns.size(); column++) {

			System.out.println(
					"----------------------new column " + column + "-----------------------------------------------");

			handlerLight = fillLight(handlerLight);
			handlerBase = getColumnFromMatrix(column, sizeRow);
			int[] indent;
			summElement = columns.get(column).stream().reduce((left, right) -> left + right).get();

			SearchCombinations searchCombinations = new SearchCombinations(columns.get(column).size(), summElement,
					sizeRow);

			searchCombinations.countCombinations();

			do {

				indent = searchCombinations.getIndent();
				indents.clear();
				indents = createIndents(columns.get(column), sizeRow, indent);
				handler = displacement(columns.get(column), sizeRow, indents);

				if (needEquals(handlerBase, handler)) {
					equalsLine(handlerBase, handler);
					equalsLight(handlerLight, handler);
				}

			} while (searchCombinations.countCombinations());

			handlerBase = approve(handlerBase, summElement);
			handlerBase = equalsBaseLight(handlerBase, handlerLight);

			for (int i = 0; i < handlerBase.length; i++) {
				result[i][column] = handlerBase[i];
			}
		}
		return result;
	}

	public List<Integer> createIndents(List<Integer> rowOrColumn, int sizeColumnOrRow, int[] displace) {

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

	public byte[] displacement(List<Integer> rowOrColumn, int sizeColumnOrRow, List<Integer> indents) {

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
	
	private byte[] equalsBaseLight(byte[] base, byte[] handlerLight) {
		
		for (int i = 0; i < base.length; i++) {			
		if(handlerLight[i] == ItemStatus.LIGHT.ordinal()) {
			base[i] = (byte) ItemStatus.WHITE.ordinal();
		}
	}		
		return base;		
	}
	
	private byte[] equalsLight(byte[] handlerLight, byte[] handler) {
		
		for (int i = 0; i < handlerLight.length; i++) {			
		if(handler[i] == ItemStatus.DARK.ordinal()) {
			handlerLight[i] = (byte) ItemStatus.DARK.ordinal();
		}
	}		
		return handlerLight;		
	}

	private byte[] equalsLine(byte[] base, byte[] handler) {

		System.out.println("base      " + Arrays.toString(base));
		System.out.println("handler   " + Arrays.toString(handler));

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

	private byte[] approve(byte[] base, int summElement) {

//		System.out.println("--------------base      " + Arrays.toString(base));

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
