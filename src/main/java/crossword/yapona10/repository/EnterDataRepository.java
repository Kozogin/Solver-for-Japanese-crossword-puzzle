/**
 * Japanese crossword puzzle solver
 * My own project
 *
 *Interface crossword.yapona10.repository.EnterDataRepository  - repository layer
 *
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crossword.yapona10.domain.EnterData;

@Repository
public interface EnterDataRepository extends JpaRepository<EnterData, Integer>{
	
	void deleteByCrossword(Integer crossword);
	
	List<EnterData> findByCrossword(Integer crossword);
		
}
