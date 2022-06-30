package com.tbs.com.java.tour.customer.tours.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tbs.com.java.tour.customer.tours.pojo.User;
import com.tbs.com.java.tour.customer.tours.validator.UserValidator;

@Controller
public class CustomerLoginController {
	@Autowired
	UserValidator userValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@Autowired
	private IUserService userServices;

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value="/loginUser")
	public String showUser(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		User user=userServices.getAuthenticatedUser(userName, password);
		if(user != null){
			request.getSession().setAttribute("user",user);
			return "redirect:/listUser";
		} else {
			return "redirect:/login";
		}
	}


	@RequestMapping(value="/listUser")
	public String listUser(HttpServletRequest request,Model model) {
		User user=(User) request.getSession().getAttribute("user");
		List<User> userList=userServices.listUser();
		model.addAttribute("userList", userList);
		model.addAttribute("user", user);
		return "listuser";
	}

	@RequestMapping(value="/registerUser")
	public String registerUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String insertUser(@Valid @ModelAttribute("user") User user,BindingResult result) {
		if(result.hasErrors()){
			return "register";
		}else {
			user.setRole("user");
			userServices.insertData(user);
			return "redirect:/login";
		}
	}

	@RequestMapping(value="/editUser", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody User editUser(@RequestParam("userName")String userName, Model model) {
		User user=userServices.getUserByUserName(userName);
		return user;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST, headers = "Accept=*/*")
	public String updateUser(Model model,HttpServletRequest request) {
		String userName=request.getParameter("userName");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		String state=request.getParameter("state");
		String email=request.getParameter("email");
		User user=userServices.getUserByUserName(userName);
		user.setCity(city);
		user.setCountry(country);
		user.setState(state);
		user.setEmail(email);
		userServices.editUser(user);
		return "redirect:/listUser";
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST, headers = "Accept=*/*")
	public String deleteUser(@RequestParam("userName")String userName) {
		userServices.deleteUser(userName);
		return "redirect:/listUser";
	}

	@RequestMapping(value="/logout")
	public String logoutUser(HttpServletRequest request,HttpServletResponse response) {
		request.getSession(false).invalidate();
		return "redirect:/login";
	}

}

}
