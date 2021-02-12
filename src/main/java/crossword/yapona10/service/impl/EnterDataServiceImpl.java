package crossword.yapona10.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crossword.yapona10.domain.EnterData;
import crossword.yapona10.repository.EnterDataRepository;
import crossword.yapona10.service.EnterDataService;

@Service
public class EnterDataServiceImpl implements EnterDataService{
	
	@Autowired
	private EnterDataRepository enterDataRepository;

	@Override
	public EnterData save(EnterData enterData) {
		return enterDataRepository.saveAndFlush(enterData);
	}	

	@Override
	public EnterData update(EnterData enterData) {
		return enterDataRepository.save(enterData);
	}	

	@Override
	public List<EnterData> findAll() {
		return enterDataRepository.findAll();
	}

	@Override
	public void deleteByCrossword(Integer crossword) {
		enterDataRepository.deleteByCrossword(crossword);
		
	}

	@Override
	public List<EnterData> findByCrossword(Integer crossword) {
		return enterDataRepository.findByCrossword(crossword);		
	}	
	
	@Override
	public Integer countCrossword() {
		boolean more = true;
		int i = 0;
		List<EnterData> enterData = null;
		while(more) {
			enterData = enterDataRepository.findByCrossword(i);	
			if(enterData.size() == 0) {
				more = false;
			}
			i++;
		}
		return i-1;
		
	}
	
	

}
