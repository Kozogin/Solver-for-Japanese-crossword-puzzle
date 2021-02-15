package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ResultMatrix {

	private byte[][] result;

	private boolean finish;

	public ResultMatrix(byte[][] result) {
		this.result = result;
		this.finish = false;
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

	public byte[][] displacementVert(List<List<Integer>> rows, int sizeColumn) {

		byte[] handlerBase = new byte[sizeColumn];
		for (int i = 0; i < handlerBase.length; i++) {
			handlerBase[i] = 11;
		}

		byte[] handler = new byte[sizeColumn];
		List<Integer> indents = new ArrayList<>();

		for (int row = 0; row < 1; row++) {
//		for (int row = 0; row < rows.size(); row++) {					

			int visual = 0;

			int increaseIndent = 0;

			this.finish = true;
			while (this.finish) {

				indents.clear();
				indents = createIndents(rows.get(row), sizeColumn, 0, increaseIndent++);
				handler = displacement(rows.get(row), sizeColumn, indents);
				handlerBase = equalsLine(handlerBase, handler);

				for (int j = 0; j < handler.length; j++) {
					result[visual][j] = handler[j];
				}

				visual++;
				if (visual > 20) {
					visual = 20;
				}
			}

			for (int j = 0; j < handler.length; j++) {
				result[17][j] = 1;
				result[18][j] = handlerBase[j];
			}

//			}

//			for (int i = 0; i < handler.length; i++) {
//				result[row][i] = handler[i];
//			}
		}
		return result;
	}

	public byte[][] displacementHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];
		List<Integer> indents = new ArrayList<>();

		for (int column = 0; column < columns.size(); column++) {

			indents.clear();
			indents = createIndents(columns.get(column), sizeRow, 0, 0);
			handler = displacement(columns.get(column), sizeRow, indents);

			for (int i = 0; i < handler.length; i++) {
				result[i][column] = handler[i];
			}
		}
		return result;
	}

	public List<Integer> createIndents(List<Integer> rowOrColumn, int sizeColumnOrRow, int indexIndentAdd,
			int increaseIndent) {

		List<Integer> indents = new ArrayList<>();

		int elementLength = 0;
		int summAll = 0;
		int indent = 0;

		if (indexIndentAdd == 0) {
			indents.add(increaseIndent);
		} else {
			indents.add(0);
		}

		summAll += indents.get(0);

		for (int indElement = 0; indElement < rowOrColumn.size(); indElement++) {

			elementLength = rowOrColumn.get(indElement);
			summAll += elementLength;

			indent = 1;
			if (indexIndentAdd == indElement + 1) {
				indent = increaseIndent + 1;
			}
			summAll += indent;

			if (indElement == rowOrColumn.size() - 1) {
				indent = sizeColumnOrRow - summAll + 1;
				if (indent == 0) {
					this.finish = false;
				}
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

	private byte[] equalsLine(byte[] base, byte[] handler) {

		for (int i = 0; i < handler.length; i++) {

			switch (base[i]) {
			case 1:				
				break;
			case 2:				
				break;				
			case 11:
				base[i] = handler[i];
				break;
			case 12:
				if(handler[i] != 11) {
					base[i] = handler[i];
				}
				break;
			default:
				break;
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
