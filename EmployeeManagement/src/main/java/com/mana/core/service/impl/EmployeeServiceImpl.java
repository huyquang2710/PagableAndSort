package com.mana.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mana.core.entity.EmployeeEntity;
import com.mana.core.repository.EmployeeRepository;
import com.mana.core.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public List<EmployeeEntity> getAllEmployee() {
		
		return employeeRepo.findAll();
	}

	@Override
	public EmployeeEntity saveAndUpdate(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}

	@Override
	public EmployeeEntity findById(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepo.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		employeeRepo.deleteById(id);
		
	}

	@Override
	public Page<EmployeeEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending(); 
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepo.findAll(pageable);
	}
	

}
