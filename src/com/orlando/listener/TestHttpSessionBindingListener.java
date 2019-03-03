package com.orlando.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

 /** 
 * @ClassName: TestHttpSessionBindingListener 
 * @Description: 监听请求绑定会话的事件
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午4:45:06 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestHttpSessionBindingListener implements HttpSessionBindingListener {
	
    String username;
    public TestHttpSessionBindingListener(){}
    public TestHttpSessionBindingListener(String username){
        this.username=username;
    }
	/**
	 * HttpSessionListener只需要设置到web.xml中就可以监听整个应用中的所有session。
	 * HttpSessionBindingListener必须实例化后放入某一个session中，才可以进行监听。
	 * 从监听范围上比较，HttpSessionListener设置一次就可以监听所有session，HttpSessionBindingListener通常都是一对一的。
	 * 正是这种区别成就了HttpSessionBindingListener的优势，我们可以让每个listener对应一个username，
	 * 这样就不需要每次再去session中读取username，进一步可以将所有操作在线列表的代码都移入listener，更容易维护。
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionBindingListener valueBound() ... 监听保存在session中的 绑定属性启动 ... ");	
	}
	/**
	 * valueUnbound的触发条件是以下三种情况：
	 *	a.执行session.invalidate()时。
	 *	b.session超时，自动销毁时。
	 *	c.执行session.setAttribute("onlineUserListener", "其他对象");
	 *	或session.removeAttribute("onlineUserListener");将listener从session中删除时。
	 *	因此，只要不将listener从session中删除，就可以监听到session的销毁。
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionBindingListener valueUnbound() ... 监听保存在session中的属性 解绑方法启动 ... ");	
	}

}
