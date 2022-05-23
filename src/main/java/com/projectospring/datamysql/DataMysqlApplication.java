package com.projectospring.datamysql;

import com.projectospring.datamysql.entities.Appointment;
import com.projectospring.datamysql.entities.Customer;
import com.projectospring.datamysql.repositories.AppointmentRepository;
import com.projectospring.datamysql.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class DataMysqlApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DataMysqlApplication.class, args);
		AppointmentRepository appRepository = context.getBean(AppointmentRepository.class);

		Appointment cita1 = new Appointment(null, LocalDateTime.now(), 60);

		appRepository.save(cita1);


		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		Customer customer1 = new Customer(null,
				"customer1",
				"customerlast",
				"example@mail.com",
				LocalDate.of(1992,12,25));

		customer1.getAppointments().add(cita1);

		customerRepository.save(customer1);

		Optional<Customer> customerOptional = customerRepository.findById(1L);

		if (customerOptional.isPresent()){
			customer1 = customerOptional.get();
			System.out.println(customer1.getAppointments());
		}

	}
}
