package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.model.User;
import com.ssf.service.CartItemService;
import com.ssf.service.CartService;
import com.ssf.service.UserService;
import com.ssf.utils.CookieUtils;

/**
 * 用户控制层
 * 
 * 控制页面跳转和数据传递
 * 
 * @author wyy
 * 2017年3月23日
 *
 */
@SuppressWarnings("serial")
public class UserController extends HttpServlet
{
	private UserService userService = new UserService();
	private CartService cartService = new CartService();
	private CartItemService cartItemService = new CartItemService();
	
	
	private static String session_remember_username = "c_username";
	private static String session_remember_password = "c_password";
	
	public static final String VIEW_PATH = "/WEB-INF/views/user/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)){
			
			//从cookie里获取登录名和密码
			String username = CookieUtils.getCookie(req, session_remember_username);
			String password = CookieUtils.getCookie(req, session_remember_password);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			req.setAttribute("session_user", user);
			
			req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
		}
		else if("logout".equals(method)){
			req.getSession().setAttribute("session_user", null);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
//		else if("listUser".equals(method)){
//			List<User> lists = new ArrayList<User>();
//			for(int i=0;i<5;i++){ //随机创建5个对象
//				User user = new User();
//				user.setUsername("测试用户"+i);
//				user.setPassword("123456");
//				lists.add(user);
//			}
//			req.setAttribute("userlist", lists);
//			req.getRequestDispatcher("index.jsp").forward(req, resp);
//		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.获取参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		
		String error = userService.login(user);
		if(error!=null && !"".equals(error)){
			req.setAttribute("msg", error);
			req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
		}
		else{
			//登录为存cookie
			CookieUtils.setCookie(resp, session_remember_username, username);
			CookieUtils.setCookie(resp, session_remember_password, password);
			
			
			req.setAttribute("msg", "登录成功");
			User exist = userService.findByName(username);
			req.getSession().setAttribute("session_user", exist);
			Integer userId = exist.getId();
			Cart cart = cartService.findCartByUserId(userId);
			if(cart== null){//1.购物车为空
				cart = new Cart();
				cart.setId(cartService.findMaxId());
				cart.setUserId(userId);
				cartService.save(cart);
			}
			else{//不为空,获取购物车的所有项目
				List<CartItem> items = cartItemService.findItemsByCartId(cart.getId());
				cart.setItems(items);
			}
			req.getSession().setAttribute("session_cart", cart);
			
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			//req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
