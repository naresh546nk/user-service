package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entity.User;
import com.user.repo.DepartmentProxy;
import com.user.repo.UserRepo;
import com.user.vo.Department;
import com.user.vo.ResponseTempleteVO;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;


	@Autowired
	private DepartmentProxy departmentProxy;
	
	
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.getById(userId);
	}

	public ResponseTempleteVO getUserWithDepartment(Long userId) {
		ResponseTempleteVO rtv=new ResponseTempleteVO();
		User user =userRepo.getById(userId);
		//Department department =restTemplate.getForEntity("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId() , Department.class).getBody();
		
		Department department =departmentProxy.getUserWithDepartment(user.getDepartmentId());
		rtv.setDepartment(department);
		rtv.setUser(user);
		return rtv;
	}
	

}
