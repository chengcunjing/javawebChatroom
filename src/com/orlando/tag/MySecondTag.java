package com.orlando.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class MySecondTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private int num = 4;
	@Override
	public int doStartTag() throws JspException {
		System.out.println("执行doStartTag()...");
//		return super.doStartTag();
//		return Tag.SKIP_BODY; //default 跳过 doAfterBody()
		return Tag.EVAL_BODY_INCLUDE;  //处理标签主体，执行doAfterBody() 
	}
	
	@Override
	public int doAfterBody() throws JspException {
//		return super.doAfterBody();
//		return Tag.SKIP_BODY;  //执行一次以后，不再重复处理标签主体
		System.out.println("执行doAfterBody()...");
		if(num > 0){
			num -- ;
			return IterationTag.EVAL_BODY_AGAIN;  //重复处理标签主体
		}else{
			return Tag.SKIP_BODY;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		System.out.println("执行doEndTag()...");
//		return super.doEndTag();
		return Tag.EVAL_PAGE; //继续处理剩余的jsp
//		return Tag.SKIP_PAGE; //执行以后不再处理剩余的jsp
	}
	
	@Override
	public void release() {
		super.release();
	}
}
