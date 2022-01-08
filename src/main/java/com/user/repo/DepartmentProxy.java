package com.user.repo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.vo.Department;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface DepartmentProxy {
	
	@GetMapping("/departments/{id}")
	public Department getUserWithDepartment(@PathVariable("id") Long departmentId);
	
	
}
