/**
 * Japanese crossword puzzle solver
 * My own project
 *
 * Class crossword.yapona10.domain.EnterData  - domain layer
 *
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enter_data")
public class EnterData {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	/* Number crossword for read from DB*/
	@Column
	private Integer crossword;
	
	/* Number row or column (with sign -)*/
	@Column
	private Integer rowNum;	
	
	/* String line with data (are entered through a comma)*/
	@Column
	private String lineNumbers;

	public EnterData(Integer id, Integer crossword, Integer rowNum, String lineNumbers) {
		this.id = id;
		this.crossword = crossword;
		this.rowNum = rowNum;
		this.lineNumbers = lineNumbers;
	}

	public EnterData(Integer crossword, Integer rowNum, String lineNumbers) {
		this.crossword = crossword;
		this.rowNum = rowNum;
		this.lineNumbers = lineNumbers;
	}
	
	public EnterData(Integer rowNum, String lineNumbers) {		
		this.rowNum = rowNum;
		this.lineNumbers = lineNumbers;
	}
	
	public EnterData() {}	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCrossword() {
		return crossword;
	}

	public void setCrossword(Integer crossword) {
		this.crossword = crossword;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public String getLineNumbers() {
		return lineNumbers;
	}

	public void setLineNumbers(String lineNumbers) {
		this.lineNumbers = lineNumbers;
	}

	@Override
	public String toString() {
		return "EnterData [id=" + id + ", crossword=" + crossword + ", rowNum=" + rowNum + ", lineNumbers="
				+ lineNumbers + "]";
	}
	
	
}
