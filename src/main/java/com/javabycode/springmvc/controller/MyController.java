package com.javabycode.springmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javabycode.springmvc.configuration.CheckLogin;
import com.javabycode.springmvc.configuration.SendMailTLS;
import com.javabycode.springmvc.dao.OrderDAO;
import com.javabycode.springmvc.dao.ProducerService;
import com.javabycode.springmvc.dao.ProductDAO;
import com.javabycode.springmvc.dao.ShoppingDAO;
import com.javabycode.springmvc.dao.UserDAO;
import com.javabycode.springmvc.model.CartInfo;
import com.javabycode.springmvc.model.CartInfo1;
import com.javabycode.springmvc.model.CartLineInfo;
import com.javabycode.springmvc.model.CustomerInfo;
import com.javabycode.springmvc.model.Order;
import com.javabycode.springmvc.model.Product;
import com.javabycode.springmvc.model.ProductInfo;
import com.javabycode.springmvc.model.ShoppingCart;
import com.javabycode.springmvc.model.User;
import com.javabycode.springmvc.model.UserDetails;

@Controller
@SessionAttributes("user")
@RequestMapping("/")
public class MyController {

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	ShoppingDAO shoppingDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	ProducerService producerService;

	@Autowired
	MessageSource messageSource;

	@ModelAttribute("user")
	public User setUpUserForm() {
		return new User();
	}

	/*------------------------------------------------------------------------------------------------*/

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping({ "/productList" })
	public String listProductHandler(Model model, @ModelAttribute("user") User user) {

		System.out.println("Entered productList api.........................................");
		List<ProductInfo> result = productDAO.queryProducts();

		model.addAttribute("paginationProducts", result);
		model.addAttribute("user", user);
		return "ProductList";
	}

	@RequestMapping({ "/buyProduct" })
	public String listProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "code", defaultValue = "") String code, @ModelAttribute("user") User user) {
		System.out.println("code" + code);
		Product product = new Product();
		if (code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
			System.out.println("1 product............" + product.toString());
		}
		if (product != null) {
			ShoppingCart cart = new ShoppingCart();
			cart.setCustomerId(user.getUserId());
			cart.setProductCode(product.getCode());
			cart.setProductName(product.getName());
			cart.setAmount(product.getPrice());
			System.out.println("2 product............" + product.toString());
			int quantity = shoppingDAO.add(cart);
			System.out.println("Added in shoppingCart" + code);

			// fetching shopping cart details from db
			List<ShoppingCart> cartList = getShoppingCart(user.getUserId());
			model.addAttribute("cartList", cartList);
			System.out.println("shoppingCart details" + cartList.toString());
		}
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart";
	}

	@RequestMapping({ "/shoppingCartRemoveProduct" })
	public String removeProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "code", defaultValue = "") String code,@ModelAttribute("user") User user) {
		Product product = null;
		if (code != null && code.length() > 0) {
			ShoppingCart cart = new ShoppingCart(user.getUserId(),code,0,"");
			cart =  shoppingDAO.getCartEntry(cart);
			shoppingDAO.remove(cart);
		}
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart";
	}

	// GET: Show Cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model, @ModelAttribute("user") User user) {

		System.out.println("User Id.............." + user.getUserId());
		List<ShoppingCart> cartList = getShoppingCart(user.getUserId());
		model.addAttribute("cartList", cartList);

		// model.addAttribute("cartForm", myCart);
		return "shoppingCart";
	}

	// GET: Enter customer information.
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

		// model.addAttribute("customerForm", customerInfo);

		return "shoppingCartCustomer";
	}

	// POST: Save customer information.
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public String shoppingCartCustomerSave(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		// If has Errors.
		if (result.hasErrors()) {
			customerForm.setValid(false);
			// Forward to reenter customer info.
			return "ShoppingCartCustomer";
		}

		customerForm.setValid(true);
		// CartInfo cartInfo = Utils.getCartInSession(request);

		// cartInfo.setCustomerInfo(customerForm);

		// Redirect to Confirmation page.
		return "redirect:/shoppingCartConfirmation";
	}

	// GET: Review Cart to confirm.
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		// CartInfo cartInfo = Utils.getCartInSession(request);

		// Cart have no products.
		/*
		 * if (cartInfo.isEmpty()) { // Redirect to shoppingCart page. return
		 * "redirect:/shoppingCart"; } else if (!cartInfo.isValidCustomer()) {
		 * // Enter customer info. return "redirect:/shoppingCartCustomer"; }
		 */

		return "shoppingCartConfirmation";
	}

	// POST: Send Cart (Save).
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	// Avoid UnexpectedRollbackException (See more explanations)
	@Transactional(propagation = Propagation.NEVER)
	public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
		// CartInfo cartInfo = Utils.getCartInSession(request);

		// Cart have no products.
		/*
		 * if (cartInfo.isEmpty()) { // Redirect to shoppingCart page. return
		 * "redirect:/shoppingCart"; } else if (!cartInfo.isValidCustomer()) {
		 * // Enter customer info. return "redirect:/shoppingCartCustomer"; }
		 * try { orderDAO.saveOrder(cartInfo); } catch (Exception e) { // Need:
		 * Propagation.NEVER? return "shoppingCartConfirmation"; }
		 */
		/*
		 * // Remove Cart In Session. Utils.removeCartInSession(request);
		 * 
		 * // Store Last ordered cart to Session.
		 * Utils.storeLastOrderedCartInSession(request, cartInfo);
		 */

		// Redirect to successful page.
		return "redirect:/shoppingCartFinalize";
	}

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Product product = null;
		if (code != null) {
			product = this.productDAO.findProduct(code);
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public @ResponseBody String sendMessage(@RequestBody String message) {
		producerService.produceMessage(message);
		return "Message published";
	}

	/*
	 * Index.
	 */
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index() {

		// List<Student> students = service.findAllStudents();
		// model.addAttribute("students", students);
		return "index";
	}

	/****************************************************************************************/
	/*
	 * @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	 * public String accountInfo(Model model) {
	 * 
	 * UserDetails userDetails = new UserDetails();
	 * userDetails.setUsername("manager1"); userDetails.setPassword("123");
	 * userDetails.setUserRole("MANAGER");
	 * 
	 * System.out.println(userDetails.getUsername());
	 * 
	 * model.addAttribute("userDetails", userDetails); return "AccountInfo"; }
	 */

	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String orderList(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("user") User user) {
		HttpSession session = request.getSession(true);
		List<Order> orderList = orderDAO.listOrderDetailInfos(user.getUserId());

		model.addAttribute("orderList", orderList);
		return "orderList";
	}

	// GET: Show product.
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;

		if (code != null && code.length() > 0) {
			productInfo = productDAO.findProductInfo(code);
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}
		model.addAttribute("productForm", productInfo);
		return "product";
	}

	@RequestMapping(value = { "/trackOrder" }, method = RequestMethod.GET)
	public String trackOrder() {

		return "about";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {

		return "index1";
	}

	@RequestMapping(value = { "/loginUser" }, method = RequestMethod.GET)
	public String checklogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
			Model model) {
		CheckLogin checkLogin = new CheckLogin();
		User user = new User();
		int Id = checkLogin.login(username, password);
		System.out.println("Id" + Id);
		if (Id != 0) {
			// fetch User
			user = getUser(Id);
			model.addAttribute("user", user);
			return "AccountInfo";
		}
		return "index1";
	}

	@RequestMapping(value = { "/confirmOrder" }, method = RequestMethod.GET)
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("user") User user) {

		// remove from shoppingcart
/*System.out.println("//////////////////////" + user.getUserId());
		List<ShoppingCart> shoppingCartList = getShoppingCart(user.getUserId());
		for (ShoppingCart cart : shoppingCartList) {
				Order order = new Order();
				order.setCustomerId(user.getUserId());
				order.setProductCode(cart.getProductCode());
				order.setAmount(cart.getAmount());
				//orderDAO.saveOrder(order);
				//shoppingDAO.remove(cart);
			

			// add to orders
*/			
			SendMailTLS sendEmail = new SendMailTLS();
			sendEmail.send(user.getEmailId());

		
		return "confirmOrder";
	}

	public User getUser(int Id) {
		User user = userDAO.getCustomerId(Id);

		return user;

	}

	public List<ShoppingCart> getShoppingCart(int customerId) {
		return shoppingDAO.getShoppingCart(customerId);

	}

	@GetMapping("/info")
	public User userInfo(@SessionAttribute("user") User user) {

		return user;
	}


	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout(@ModelAttribute User user, SessionStatus sessionStatus, WebRequest request) {
		//store.cleanupAttribute(request, "user");
		sessionStatus.setComplete();
		request.removeAttribute("user", WebRequest.SCOPE_SESSION);
		return "redirect:/" + "login";
		
	}}