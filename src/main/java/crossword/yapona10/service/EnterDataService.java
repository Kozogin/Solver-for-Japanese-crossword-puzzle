/**
 * Japanese crossword puzzle solver
 * My own project
 *
 *Interface crossword.yapona10.service.EnterDataService  - service layer
 *
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.service;

import java.util.List;

import crossword.yapona10.domain.EnterData;

public interface EnterDataService {
	
	EnterData save (EnterData enterData);	
	
	EnterData update (EnterData enterData);	
	
	List<EnterData> findAll();
	
	void deleteByCrossword(Integer crossword);
	
	List<EnterData> findByCrossword(Integer crossword);

	Integer countCrossword();
	

}
