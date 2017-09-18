package edu.mum.cs544;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyWebController {
	

	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Spring Security");
		model.addAttribute("message", "You are in a public area!");		
		return "hello";
	}


	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage(Model model) { 
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		model.addAttribute("title", "Spring Security - ADMIN AREA");
		model.addAttribute("message", "You are in a protected area!");
		model.addAttribute("name", username);
		return "admin";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/welcome";
	}
}
