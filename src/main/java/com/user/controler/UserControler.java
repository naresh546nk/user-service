package com.user.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.repo.DepartmentProxy;
import com.user.repo.UserRepo;
import com.user.service.UserService;
import com.user.vo.Department;
import com.user.vo.ResponseTempleteVO;

@RestController
@RequestMapping("/users")
public class UserControler {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private DepartmentProxy departmentProxy;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userService.save(user);
		
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable("id") Long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/{id}")
	public ResponseTempleteVO getUserWithDepartment(@PathVariable("id") Long userId) {
		//return userService.getUserWithDepartment(userId);
		User user=userRepo.getById(userId);
		Department department=departmentProxy.getUserWithDepartment(user.getDepartmentId());
		ResponseTempleteVO vo=new ResponseTempleteVO();
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
	

}
