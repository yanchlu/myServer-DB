package com.c72;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/bi")
public class HelloController{ 
   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   public Map<String,Object> getStudentList() {
	   ApplicationContext context = new ClassPathXmlApplicationContext("mySpring.xml");
	   StudentJDBCTemplate studentJDBCTemplate = 
	   (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");   
	   List<Student> students = studentJDBCTemplate.listStudents();
	   Map<String, Object> modelMap=new HashMap<String, Object>(1);
	   modelMap.put("studentList", students);
	   return modelMap;
	      //model.addAttribute("message", stringBuffer.toString());
   }
}