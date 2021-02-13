package crossword.yapona10.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import crossword.yapona10.domain.EnterData;
import crossword.yapona10.service.EnterDataService;
import crossword.yapona10.service.impl.SolverCrossword;

@Controller
public class EnterDataController {
		
	private int verticalInt;
	private int horizontalInt;
	private int crosswordInt;
	
	private SolverCrossword solverCrossword;	
	
	@Autowired
	private EnterDataService enterDataService;
	
	@RequestMapping(value = { "/", "/enter_data" }, method = RequestMethod.GET)
	public ModelAndView enter(Model model) {
		
		ModelAndView map = new ModelAndView("/enter_data");
		map.addObject("crossword", enterDataService.countCrossword());
		return map; 
	}
	
	@RequestMapping(value = "/enter_data", method = RequestMethod.POST)
    public ModelAndView swonTable(@RequestParam(required = false, defaultValue = "0") String vertical,
    		@RequestParam(required = false, defaultValue = "0") String horizontal,
    		@RequestParam(required = false, defaultValue = "0") String crossword
    		) {
        
        verticalInt = Integer.parseInt(vertical);
        horizontalInt = Integer.parseInt(horizontal);
        crosswordInt = Integer.parseInt(crossword);
        
        ModelAndView map = new ModelAndView("redirect:/form_table");
		return map; 
    } 
	
	@RequestMapping(value = { "/form_table" }, method = RequestMethod.GET)
	public ModelAndView enterTable(Model model) {
				
		if(crosswordInt != 0) {
			ModelAndView map = new ModelAndView("table_from_DB");
			
			List<EnterData> eData = enterDataService.findByCrossword(crosswordInt - 1);
			List<EnterData> vertic = eData.stream().filter(o->o.getRowNum() > 0).collect(Collectors.toList());
			List<EnterData> horiz = eData.stream().filter(o->o.getRowNum() < 0).collect(Collectors.toList());
			
			map.addObject("vertic", vertic);
			map.addObject("horiz", horiz);
			return map; 
		}
				
		ModelAndView map = new ModelAndView("form_table");
		map.addObject("vertical", verticalInt);
		map.addObject("horizontal", horizontalInt);
		return map; 
	}	
	
	@RequestMapping(value = "/form_table", method = RequestMethod.POST)
	public ModelAndView saveToDB(@RequestParam String [] vertical_row,
			@RequestParam String [] horizontal_column
			) throws IOException {	
		
		solverCrossword = new SolverCrossword(vertical_row, horizontal_column);

		int numberBD = 0;
		try {
			numberBD = enterDataService.countCrossword();
		} catch (Exception e) {}		
		
		for (int i = 0; i < vertical_row.length; i++) {
			enterDataService.save(new EnterData(numberBD, i + 1, vertical_row[i]));
		}
		for (int i = 0; i < horizontal_column.length; i++) {
			enterDataService.save(new EnterData(numberBD, -1*i - 1, horizontal_column[i]));
		}
		
		return new ModelAndView("redirect:/show_result");
	}
	
	@RequestMapping(value = "/table_from_DB", method = RequestMethod.GET)
	public String readFromBD(Model model) {
		return "table_from_DB";
	}
	
	@RequestMapping(value = "/table_from_DB", method = RequestMethod.POST)
	public ModelAndView readFromBDPost(@RequestParam String [] vertical_row,
			@RequestParam String [] horizontal_column
			) throws IOException {

		solverCrossword = new SolverCrossword(vertical_row, horizontal_column);	
		ModelAndView map = new ModelAndView("redirect:show_result");
		return map;
	}
	
	@RequestMapping(value = "/show_result", method = RequestMethod.GET)
	public ModelAndView showResult(Model model) {
		
		ModelAndView map = new ModelAndView("show_result");
		map.addObject("showCrossword", solverCrossword.solver());
		return map;
	}


}
