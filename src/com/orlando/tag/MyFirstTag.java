package com.orlando.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyFirstTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("my first taglib...");
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		out.print("看到我说明你就对了...");
		super.doTag();
	}

}
