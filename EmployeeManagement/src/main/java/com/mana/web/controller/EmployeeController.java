package com.mana.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mana.core.entity.EmployeeEntity;
import com.mana.core.service.IEmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	// get list
	@GetMapping("/employee")
	public String getAll(Model model) {
//		model.addAttribute("employeesList", employeeService.getAllEmployee());
//		return "employee";
		return findPaginated(1, "firstName", "asc", model);
	}
	//add
	@GetMapping("/employee/add")
	public String createAddForm(Model model) {
		EmployeeEntity employee = new EmployeeEntity();
		model.addAttribute("employeesAdd", employee);
		return "add_employee";
	}
	@PostMapping("/employee")
	public String save(Model model, EmployeeEntity employee) {
		model.addAttribute("employeesAdd", employeeService.saveAndUpdate(employee));
		return "redirect:/employee";
	}
	//add end
	
	//update
	@GetMapping("/employee/edit/{id}")
	public String editForm(Model model, @PathVariable Integer id) {
		model.addAttribute("employee", employeeService.findById(id));
		return "edit_employee";
	}
//	add voi update dung chung duoc
//	@PostMapping("/employee/{id}")
//	public String update(@PathVariable Integer id, @ModelAttribute("employee") EmployeeEntity employee, Model model ) {
//		EmployeeEntity existingEmployee = employeeService.findById(id);
//		existingEmployee.setId(employee.getId());
//		existingEmployee.setFirstName(employee.getFirstName());
//		existingEmployee.setLastName(employee.getLastName());
//		existingEmployee.setEmail(employee.getEmail());
//		
//		employeeService.saveAndUpdate(existingEmployee);
//		return "redirect:/employee";
//	}
	
	@GetMapping("/employee/{id}")
	public String delete(@PathVariable Integer id) {
		employeeService.delete(id);
		return "redirect:/employee";
	}
	
	@GetMapping("page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<EmployeeEntity> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<EmployeeEntity> listEmployee = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc" );
		
		model.addAttribute("employeesList", listEmployee);
		return "employee";
	}
}
