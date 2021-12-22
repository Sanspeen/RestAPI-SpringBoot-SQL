package net.javaguides.springbootbackend.controller;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.model.Job;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        return ResponseEntity.ok(employee);
    }

    @GetMapping("job/{id}")
    public ResponseEntity<Job> getEmployeeJobById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));

        Job job = jobRepository.findById(employee.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("This employee doesn't have a job, yet."));

        return ResponseEntity.ok(job);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping
    public void deleteAllEmployees(){ employeeRepository.deleteAll(); }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        updateEmployee.setSalary(employeeDetails.getSalary());
        updateEmployee.setJobId(employeeDetails.getJobId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    @PutMapping("salary-reduction/{id}")
    public Double updateEmployeeSalary(@PathVariable Long id, @RequestBody Employee employeeSalaryReduction){

        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeSalaryReduction.setSalary(updateEmployee.getSalary() - employeeSalaryReduction.getSalary());
        updateEmployee.setSalary(employeeSalaryReduction.getSalary());

        employeeRepository.save(updateEmployee);

        return updateEmployee.getSalary();
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Long id){
                employeeRepository.deleteById(id);
    }

}
