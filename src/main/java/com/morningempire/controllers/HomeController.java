package com.morningempire.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.morningempire.models.LoginUser;
import com.morningempire.models.Product;
import com.morningempire.models.User;
import com.morningempire.services.ProductService;
import com.morningempire.services.UserService;

//import com.codingdojo.exam.models.Team;
//import com.codingdojo.exam.models.LoginUser;
//import com.codingdojo.exam.models.User;
//import com.codingdojo.exam.services.UserService;
//import com.codingdojo.exam.services.TeamService;
//import com.codingdojo.exam.models.User;
//import com.codingdojo.exam.models.Team; 

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	// Add once service is implemented:
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input

		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User in session: " + userId);
		if (userId == null) {
			// Redirect to login if user is not logged in
			return "home.jsp";
		}
		// Retrieve the user from the database
		User user = userService.findUserById(userId);
		System.out.println("User in session: " + user);
		if (user == null) {
			// Redirect to login if user is not found
			return "home.jsp";
		}

		// Add the user's name to the model
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "home.jsp";
	}
	
	

	@GetMapping("/RegistrationandLogin")
	public String login(Model model, HttpSession session) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input

//				Long userId = (Long) session.getAttribute("userId");
//				System.out.println("User in session: " + userId);
//				if (userId == null) {
//					// Redirect to login if user is not logged in
//					return "redirect:/RegistrationandLogin";
//				}
//				// Retrieve the user from the database
//				User user = userService.findUserById(userId);
//				System.out.println("User in session: " + user);
//				if (user == null) {
//					// Redirect to login if user is not found
//					return "redirect:/RegistrationandLogin";
//				}

		// Add the user's name to the model
//				model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "RegistrationandLogin.jsp";
	}

	@GetMapping("/menu")
	public String menu(Model model, HttpSession session) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input

		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User in session: " + userId);
		if (userId == null) {
			// Redirect to login if user is not logged in
			return "menu.jsp";
		}
		// Retrieve the user from the database
		User user = userService.findUserById(userId);
		System.out.println("User in session: " + user);
		if (user == null) {
			// Redirect to login if user is not found
			return "menu.jsp";
		}

		// Add the user's name to the model
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "menu.jsp";
	}

	@GetMapping("/about")
	public String about(Model model, HttpSession session) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input

		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User in session: " + userId);
		if (userId == null) {
			// Redirect to login if user is not logged in
			return "about.jsp";
		}
		// Retrieve the user from the database
		User user = userService.findUserById(userId);
		System.out.println("User in session: " + user);
		if (user == null) {
			// Redirect to login if user is not found
			return "about.jsp";
		}

		// Add the user's name to the model
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "about.jsp";
	}

	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input

		List<Product> products = productService.getAllProducts();

		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User in session: " + userId);
		if (userId == null) {
			// Redirect to login if user is not logged in
			return "cart.jsp";
		}
		// Retrieve the user from the database
		User user = userService.findUserById(userId);
		System.out.println("User in session: " + user);
		if (user == null) {
			// Redirect to login if user is not found
			return "cart.jsp";
		}

		// Add the user's name to the model
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		model.addAttribute("products", products);
		return "cart.jsp";
	}

	@GetMapping("/product")
	public String addProduct(Model model, HttpSession session) {
		// Bind empty User, LoginUser and Product objects to the JSP
		// to capture the form input

		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User in session: " + userId);
		if (userId == null) {
			// Redirect to login if user is not logged in
			return "order.jsp";
		}
		// Retrieve the user from the database
		User user = userService.findUserById(userId);
		System.out.println("User in session: " + user);
		if (user == null) {
			// Redirect to login if user is not found
			return "order.jsp";
		}

		// Add the user's name to the model
		model.addAttribute("newProduct", new Product());
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "order.jsp";
	}
	
	@GetMapping("/products/{id}/edit")
	public String editProduct(Model model, @PathVariable(value="id") Long id) {
		
		Product product = productService.findProduct(id);		
		model.addAttribute("product", product);
		model.addAttribute("newProduct", new Product());
		
		return "edit.jsp";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		Product product = productService.findProduct(id);

		productService.deleteProduct(product);

		return "redirect:/cart"; // Redirect to a page showing all books or another appropriate page
	}

//	@GetMapping("/teams/new")
//	public String addTeam(Model model, HttpSession session) {
//		Long userId = (Long) session.getAttribute("userId");
//		// Retrieve the user from the database
//		User user = userService.findUserById(userId);
//		// Bind empty User and LoginUser objects to the JSP
//		// to capture the form input
//		model.addAttribute("newUser", new User());
//		model.addAttribute("newLogin", new LoginUser());
//		model.addAttribute("newTeam", new Team());
//		model.addAttribute("userName", user.getUserName());
//		model.addAttribute("userId", user.getId());
//		return "new.jsp";
//	}
//	
//	
//
//	@GetMapping("/welcome")
//	public String welcome(Model model, HttpSession session) {
//		
//		List<Team> teams = teamService.allTeams();
//		
//		Long userId = (Long) session.getAttribute("userId");
//		if (userId == null) {
//			// Redirect to login if user is not logged in
//			return "redirect:/";
//		}
//		// Retrieve the user from the database
//		User user = userService.findUserById(userId);
//
//		if (user == null) {
//			// Redirect to login if user is not found
//			return "redirect:/";
//		}
//
//		// Add the user's name to the model
//		model.addAttribute("userName", user.getUserName());
//		model.addAttribute("teams", teams);
//		return "welcome.jsp";
//	}
//
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {

		session.invalidate();
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "redirect:/";
	}

//	
//	@GetMapping("/teams/{id}")
//	public String teamDetails(Model model, @PathVariable(value="id") Long id, HttpSession session) {
//		
//		Long userId = (Long) session.getAttribute("userId");
//		Team team = teamService.findTeam(id);
//		User user = userService.findUserById(userId);
//		
//		model.addAttribute("userName", user.getUserName());
//		model.addAttribute("team", team);
//		
//		
//		return "details.jsp";
//	}
//	@GetMapping("/teams/{id}/edit")
//	public String editTeam(Model model, @PathVariable(value="id") Long id) {
//		Team team = teamService.findTeam(id);
//		
//		model.addAttribute("team", team);
//		model.addAttribute("newTeam", new Team());
//		
//		return "edit.jsp";
//	}
//
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		// TO-DO Later -- call a register method in the service
		// to do some extra validations and create a new user!
		User registeredUser = userService.register(newUser, result);

		if (result.hasErrors()) {
			// Be sure to send in the empty LoginUser before
			// re-rendering the page.
			model.addAttribute("newLogin", new LoginUser());
			return "RegistrationandLogin.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		session.setAttribute("userId", registeredUser.getUserId());

		return "redirect:/";
	}

//
	@PostMapping("/RegistrationandLogin")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		// Add once service is implemented:
		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "RegistrationandLogin.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		session.setAttribute("userId", user.getUserId());

		return "redirect:/";
	}

//
	@PostMapping("/newProduct")
	public String createTeam(@Valid @ModelAttribute("newProduct") Product product, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "order.jsp";
		} else {
			productService.saveProduct(product);

			return "redirect:/cart";
		}

	}

	@PostMapping("/product")
	public String addProduct1(@RequestParam("productName") String productName, Model model, HttpSession session) {
		// Bind empty User, LoginUser and Product objects to the JSP
		// to capture the form input

//		Long userId = (Long) session.getAttribute("userId");
//		System.out.println("User in session: " + userId);
//		if (userId == null) {
//			// Redirect to login if user is not logged in
//			return "order.jsp";
//		}
		// Retrieve the user from the database
//		User user = userService.findUserById(userId);
//		System.out.println("User in session: " + user);
//		if (user == null) {
//			// Redirect to login if user is not found
//			return "order.jsp";
//		}

		// Add the user's name to the model
		model.addAttribute("productName", productName);
		model.addAttribute("newProduct", new Product());
//		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "order.jsp";
	}
	
	@PostMapping("/editProduct/{id}")
	public String updateProduct(@PathVariable(value="id") Long id, @Valid @ModelAttribute("product") Product updatedProduct, 
			BindingResult result, Model model) {

		Product product = productService.findProduct(id);
		
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			product.setDescription(updatedProduct.getDescription());
			
			
			productService.saveProduct(product);

			return "redirect:/cart";
		}

	}
//	@PostMapping("/editTeam/{id}")
//	public String updateTeam(@PathVariable(value="id") Long id, @Valid @ModelAttribute("team") Team updatedTeam, BindingResult result, Model model) {
//
//		Team team = teamService.findTeam(id);
//		
//		if (result.hasErrors()) {
//			return "edit.jsp";
//		} else {
//			team.setTitle(updatedTeam.getTitle());
//			team.setAuthor(updatedTeam.getAuthor());
//			team.setSkillLevel(updatedTeam.getSkillLevel());
//			team.setGameDay(updatedTeam.getGameDay());
//			
//			teamService.createTeam(team);
//
//			return "redirect:/welcome";
//		}
//
//	}
//	
//	@GetMapping("/delete/{id}")
//    public String deleteTeam(@PathVariable("id") Long id) {
//        Team team = teamService.findTeam(id);
//        
//        teamService.deleteTeam(team);
//        
//        return "redirect:/welcome"; // Redirect to a page showing all teams or another appropriate page
//    }

}