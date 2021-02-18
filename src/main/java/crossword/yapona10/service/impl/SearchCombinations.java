/**
 * Japanese crossword puzzle solver
 * My own project
 *
 * Class crossword.yapona10.service.impl.SearchCombinations  - service implementation layer
 * This is a counter designed to generate all possible combinations of element indents
 *
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.service.impl;

public class SearchCombinations {
	
	/* array of indents, counter status*/
	private int[] indent;	
	private int numberElement;
	private int summElement;
	private int matrixLength;
	
	/* max sum all indents should not exceed this value*/
	private int sumAllIndent;
	
	/* number index array*/
	private int actualyIndex;

	public SearchCombinations(int numberElement, int summElement, int matrixLength) {
		this.numberElement = numberElement;
		this.summElement = summElement;
		this.matrixLength = matrixLength;
		this.actualyIndex = 0;
		this.sumAllIndent = matrixLength - (summElement + numberElement - 1);
		this.indent = new int[numberElement];
		
		/* preset counter to set the zero displacement*/
		this.indent[0] = -1;
	}
	
	
	public boolean countCombinations() {
		
		/* condition for exit (all the combinations found)*/
		if(this.sumAllIndent == 0) {					
			for (int i = 0; i < indent.length; i++) {	
				indent[i] = 0;							
			}									
			return false;								
		}		
		
		/* if sum array more than needed to incremented next digit number
		 and clear previous digit number*/
		if (summArray() + 1 > this.sumAllIndent) {

			this.actualyIndex = 0;
			do {
				
				this.indent[this.actualyIndex] = 0;
				this.actualyIndex++;
			} while (summArray() >= this.sumAllIndent);

			if (this.actualyIndex < this.numberElement) {
				this.indent[this.actualyIndex]++;
			} else {
				return false;
			}
		} else {
			this.actualyIndex = 0;
			this.indent[this.actualyIndex]++;
		}
		return true;
	}

	private int summArray() {
		int summ = 0;
		for (int i = 0; i < this.indent.length; i++) {
			summ += indent[i];
		}
		return summ;
	}

	public int[] getIndent() {
		return indent;
	}

	@Override
	public String toString() {
		return "SearchCombinations [numberElement=" + numberElement + ", summElement=" + summElement + ", matrixLength="
				+ matrixLength + ", sumAllIndent=" + sumAllIndent + ", actualyIndex=" + actualyIndex + "]";
	}

}
