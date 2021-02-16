package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultMatrix {

	private byte[][] result;

	public ResultMatrix(byte[][] result) {
		this.result = result;
//		this.result[0][2] = 1;
//		this.result[0][7] = 1;
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
			if (handlerBase[i] != 1) {
				handlerBase[i] = 11;
			}
		}
		return handlerBase;
	}

	public byte[][] displacementVert(List<List<Integer>> rows, int sizeColumn) {

		byte[] handler = new byte[sizeColumn];
		byte[] handlerBase;
		int summElement;

		List<Integer> indents = new ArrayList<>();

		for (int row = 0; row < rows.size(); row++) {

			handlerBase = getRowFromMatrix(row, sizeColumn);
			int[] indent;
			summElement = rows.get(row).stream().reduce((left, right) -> left + right).get();

			SearchCombinations searchCombinations = new SearchCombinations(rows.get(row).size(), summElement,
					sizeColumn);

			while (searchCombinations.countCombinations()) {

				indent = searchCombinations.getIndent();

				indents.clear();
				indents = createIndents(rows.get(row), sizeColumn, indent);
				handler = displacement(rows.get(row), sizeColumn, indents);

				if (needEquals(handlerBase, handler)) {
					equalsLine(handlerBase, handler);
				}

			} // while new
			
			handlerBase = approve(handlerBase, summElement);

			for (int i = 0; i < handlerBase.length; i++) {
				result[row][i] = handlerBase[i];
			}

			
		}
		return result;
	}

	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];
		List<Integer> indents = new ArrayList<>();

		for (int column = 0; column < columns.size(); column++) {

			indents.clear();
			handler = displacement(columns.get(column), sizeRow, indents);

			for (int i = 0; i < handler.length; i++) {
				result[i][column] = handler[i];
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
				handler[indMatrix++] = 12;
			}
		}

		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {

			lengthElement = rowOrColumn.get(indInnerList);
			lengthIndent = indents.get(indInnerList + 1);
			indexExit = indMatrix + lengthElement;

			while (indMatrix < indexExit) {
				handler[indMatrix++] = 11;
			}

			indexExit += lengthIndent;

			if (indMatrix < sizeColumnOrRow || indexExit < sizeColumnOrRow) {
				while (indMatrix < indexExit) {
					handler[indMatrix++] = 12;
				}
			}
		}
		return handler;
	}

	private boolean needEquals(byte[] base, byte[] handler) {

		System.out.println("base      " + Arrays.toString(base));
		System.out.println("handler   " + Arrays.toString(handler));

		for (int i = 0; i < base.length; i++) {
			if (base[i] == 1 && handler[i] != 11) {
				return false;
			}
			if (base[i] == 2 && handler[i] != 12) {
				return false;
			}
		}
		return true;
	}

	private byte[] equalsLine(byte[] base, byte[] handler) {

		for (int i = 0; i < base.length; i++) {
			switch (base[i]) {
			case 1:
				break;
			case 2:
				break;
			case 11:
				base[i] = handler[i];
				break;
			case 12:
				if (handler[i] != 11) {
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

		System.out.println("--------------base      " + Arrays.toString(base));

		int countBlackElements = 0;

		for (int i = 0; i < base.length; i++) {
			if (base[i] == 11) {
				base[i] = 1;
			}
			if (base[i] == 1) {
				countBlackElements++;
			}
		}

		if (countBlackElements == summElement) {
			for (int i = 0; i < base.length; i++) {
				if (base[i] != 1) {
					base[i] = 2;
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

}
