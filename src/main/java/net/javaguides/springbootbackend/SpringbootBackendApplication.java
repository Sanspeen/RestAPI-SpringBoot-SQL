package net.javaguides.springbootbackend;

import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
//		Employee employee = new Employee();
//		employee.setFirstName("Santiago");
//		employee.setLastName("Franco");
//		employee.setEmailId("Santaigo.Franco@gmail.com");
//		employeeRepository.save(employee);
//
//		Employee employee1 = new Employee();
//		employee1.setFirstName("Mishell");
//		employee1.setLastName("Ayala");
//		employee1.setEmailId("Mishell.Ayala@gmail.com");
//		employeeRepository.save(employee1);
	}
}
