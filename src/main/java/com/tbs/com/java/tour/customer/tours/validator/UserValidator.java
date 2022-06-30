package com.tbs.com.java.tour.customer.tours.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tbs.com.java.tour.customer.tours.pojo.User;

@Component
public class UserValidator implements Validator{
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	String STRING_PATTERN = "[a-zA-Z]+";

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class c) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors error) {
		User user=(User) target;
		System.out.println("Inside validator!!!!");
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName",
				"required.user.userName", "User Name is required.");
		
		if (user.getUserName() != null && !(user.getUserName().isEmpty())) {
			if (user.getUserName().length() < 2) {
				error.rejectValue("userName", "user.userName.exceed",
						"Enter valid User Name");
			} else if (user.getUserName() != null) {
				pattern = Pattern.compile(STRING_PATTERN);
				matcher = pattern.matcher(user.getUserName());

				if (!matcher.matches()) {
					error.rejectValue("userName", "user.userName.incorrect",
							"User Id can't be Numeric value");
				}
			}
			
		
	}
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email",
				"required.user.email", "Email is required.");

		if (!(user.getEmail()!= null && user.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				error.rejectValue("email", "user.email.incorrect",
						"Enter a correct email");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password","required.user.password", "Password is required.");

	}
}

