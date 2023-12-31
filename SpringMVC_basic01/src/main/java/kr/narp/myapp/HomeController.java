//package kr.narp.myapp;
//
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	
////	Spring Legacy Project : Spring의 가장 기초적인 프로젝트.	
////	Context Path : /myapp
////	클라이언트가 서버의 컨트롤러로 요청(http://localhost:8080/myapp/) -> 객체바인딩(data) -> home.jsp로 포워딩(데이터 전달 후 출력) ! 
////	요청이 들어오면, 외부로부터 model객체를 받는다.(객체바인딩에 필요한 객체!)
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
////		객체바인딩 ! : model이라는 클래스는 전문적으로 객체바인딩을 한다. (cf. request.setAttribute("list", list); 후, 포워딩...)
//		model.addAttribute("serverTime", formattedDate );
//		
////		포워딩 !
//		return "home"; // view의 논리적 이름만 적는다. /WEB-INF/vies/home.jsp : viewresolver가 내장되어있어서, 자동으로 처리해준다.
//	}
//	
//}
