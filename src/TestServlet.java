import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

 /** 
 * @ClassName: TestServlet 
 * @Description: 用于测试在没有包的情况下 servlet 是否可以正常配置使用，结论是：可以
 * @author: 章征武【orlando】
 * @date: 2018年9月29日 下午5:03:02 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebServlet(name="testservlet",urlPatterns="/test",loadOnStartup = -1,
initParams = { @WebInitParam(name="name",value="orlando"),@WebInitParam(name="nick",value="maomao") } )
public class TestServlet implements Servlet {

	public TestServlet(){
		System.out.println("构造方法...");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init...");
		String nick = config.getInitParameter("nick");
		System.out.println(nick);
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("config...");
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service...");
		new String();
	}

	@Override
	public String getServletInfo() {
		System.out.println("servletinfo...");;
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("destroy...");
	}


	
}
