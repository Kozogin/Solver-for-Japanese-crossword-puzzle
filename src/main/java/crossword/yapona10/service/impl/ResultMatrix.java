package crossword.yapona10.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ResultMatrix {

	private byte[][] result;

	public ResultMatrix(byte[][] result) {
		this.result = result;
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

	public byte[][] impositionVert(List<List<Integer>> rows, int sizeColumn) {

		byte[] handler = new byte[sizeColumn];
		for (int row = 0; row < rows.size(); row++) {

			handler = imposition(rows.get(row), sizeColumn);

			for (int i = 0; i < handler.length; i++) {
				result[row][i] = handler[i];
			}
		}
		return result;
	}

	public byte[][] impositionHorz(List<List<Integer>> columns, int sizeRow) {

		byte[] handler = new byte[sizeRow];
		for (int column = 0; column < columns.size(); column++) {

			handler = imposition(columns.get(column), sizeRow);

			for (int i = 0; i < handler.length; i++) {
				result[i][column] = handler[i];
			}
		}
		return result;
	}

	public byte[] imposition(List<Integer> rowOrColumn, int sizeColumnOrRow) {

		byte[] handler = new byte[sizeColumnOrRow];

		// pass along the line vertical or horizontal push into the matrix from the
		// beginning with a single gap

		int lengthElement = 0;
		int indMatrix = 0;
		int indexExit = 0;
		for (int indInnerList = 0; indInnerList < rowOrColumn.size(); indInnerList++) {

			lengthElement = rowOrColumn.get(indInnerList);
			indexExit = indMatrix + lengthElement;

			while (indMatrix < indexExit) {
				handler[indMatrix++] = 1;
			}

			if (indMatrix < sizeColumnOrRow) {
				 do {
					handler[indMatrix++] = 2;
				 }while(indMatrix < sizeColumnOrRow && indInnerList == rowOrColumn.size() - 1);
			}
		}
		return handler;
	}

	public byte[][] getResult() {
		return result;
	}

	public void setResult(byte[][] result) {
		this.result = result;
	}

}
