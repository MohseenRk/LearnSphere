package com.learnSphere.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnSphere.entities.Course;
import com.learnSphere.entities.Users;
import com.learnSphere.services.StudentService;
import com.learnSphere.services.TrainerService;
import com.learnSphere.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@Controller
public class orderCreation {
	@Autowired
	UsersService uservice;
	@Autowired
	StudentService sservice;
	@Autowired
	TrainerService tservice;
	
	
@PostMapping("/takeOrder")
	@ResponseBody
	public String takeorder(@RequestParam int amount,String email,int courseId) {
	mapCourseAndUser(email,courseId);   //modified
	 Order order =null;

		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_kTMXY8IGNvKHz0","F5XoKNe4KIDkp9SWanBqzb7o");
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");

			  order  =  razorpay.orders.create(orderRequest);
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
		return order.toString();
	}
public void mapCourseAndUser(String email,int courseId) {
	Users user=uservice.findUserByEmail(email);
	Course course=sservice.fetchCourse(courseId);
	
	user.getCourseList().add(course);
	course.getUserList().add(user);
	
	tservice.saveCourse(course);
	uservice.saveUsers(user);
	
}
}