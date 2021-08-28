package com.mana.core.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mana.core.entity.EmployeeEntity;

public interface IEmployeeService {
	List<EmployeeEntity> getAllEmployee();
	EmployeeEntity saveAndUpdate(EmployeeEntity employee);
	EmployeeEntity findById(Integer id);
	void delete(Integer id);
	
	Page<EmployeeEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
