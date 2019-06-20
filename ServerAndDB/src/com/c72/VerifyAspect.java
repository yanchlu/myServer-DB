package com.c72;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class VerifyAspect {
	private ApplicationContext context = new ClassPathXmlApplicationContext("mySpring.xml");
	   private TaskDAOImpl taskDAOImpl = 
	   (TaskDAOImpl)context.getBean("TaskDAOImpl");   
	   
	@Pointcut("execution(* com.c72.TaskController.testLink(..))")
	private void pointcut() {
		
	}
	@Around("pointcut()")
	public Object passwordVerify(ProceedingJoinPoint pjp)throws Throwable{
		//首先获取方法名称列表
        MethodSignature msg = (MethodSignature)pjp.getSignature();
        String[] paramName = msg.getParameterNames();
        List<String> paramNameList = Arrays.asList(paramName);

        //获取传入的参数
        Object[] args = pjp.getArgs();
        
        int v_uid=1;
        String password="default";
        //如果有userId这个参数
        if (paramNameList.contains("v_uid")) {
        	Integer pos = paramNameList.indexOf("v_uid");
        	v_uid=Integer.valueOf((String)args[pos]);
        }
        if (paramNameList.contains("v_password")) {
        	Integer pos = paramNameList.indexOf("v_password");
        	password=(String)args[pos];
        }
        System.out.println(v_uid);
        System.out.println(password);
        if(taskDAOImpl.getUserbyUid(v_uid).getPassword().equals(password)) {
        	System.out.println("Verify success");
        }else {
        	System.out.println("Verify failed");
        	args[paramNameList.indexOf("v_uid")]=Integer.valueOf(-1).toString();
        }
        return pjp.proceed(args);
	}
}
