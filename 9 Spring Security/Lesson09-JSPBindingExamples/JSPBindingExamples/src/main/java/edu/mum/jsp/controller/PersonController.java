package edu.mum.jsp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.jsp.domain.Person;

@Controller
public class PersonController {

	@RequestMapping(value="/person", method= RequestMethod.GET) 
	public String getPerson(Model model){
		Person p = new Person();

		p.setName("person"+randBetween(0, 1000));
		p.setDateOfBirth(getRandomDOB());
		p.setTimeOfBirth(getRandomTime());

		model.addAttribute("person", p);
		
		List<Person> people = new ArrayList<Person>();
		if (model.containsAttribute("savedperson")) {
			Person sp = (Person) model.asMap().get("savedperson");
			people.add(sp);
		}
		model.addAttribute("people", people);
						
		return "/person";		
	}

	@RequestMapping(value="/updatePerson", method= RequestMethod.POST) 
	public String updatePerson(@ModelAttribute Person person, BindingResult result,
			final RedirectAttributes redirectAttributes){
		if(result.hasErrors()) {
			return "/person";
		}
		System.out.println("Person name:"+person.getName()
				+" DOB:"+person.getDateOfBirth()
				+" TOB:"+person.getTimeOfBirth()
				+" Retire:"+person.getRetireDate()
				+" Gender:"+person.getGender()
				);
		redirectAttributes.addFlashAttribute("savedperson", person);
		return "redirect:/person";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss aaa");
		timeFormat.setLenient(true);
		
		binder.registerCustomEditor(Date.class, "person.dateOfBirth", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "person.timeOfBirth", new CustomDateEditor(timeFormat, true));
		binder.registerCustomEditor(Date.class, "person.retireDate", new CustomDateEditor(dateFormat, true));		
	}

	private Date getRandomTime() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(gc.YEAR, randBetween(1900, Calendar.getInstance().get(gc.YEAR)-1));
        gc.set(gc.DAY_OF_YEAR, randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)));        
        gc.set(gc.HOUR_OF_DAY, randBetween(0, 23));
        gc.set(gc.MINUTE, randBetween(0, 59));
        gc.set(gc.SECOND, randBetween(0, 59));
		return gc.getTime();
	}
	
	static private Date getRandomDOB() {
		GregorianCalendar gc = new GregorianCalendar();

		gc.set(gc.YEAR, randBetween(1900, Calendar.getInstance().get(gc.YEAR)-1));
        gc.set(gc.DAY_OF_YEAR, randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)));        
        gc.set(gc.HOUR_OF_DAY, 0);
        gc.set(gc.MINUTE, 0);
        gc.set(gc.SECOND, 0);
        
		return gc.getTime();
	}

	static private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
	}
}
