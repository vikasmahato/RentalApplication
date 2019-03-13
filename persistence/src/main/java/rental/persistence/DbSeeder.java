package rental.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rental.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private CustomerRepository customerRepository;

    public DbSeeder(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Customer marriot = new Customer();
        marriot.setName("Marriot");
        marriot.setClassification(5);



        List<Customer> customers = new ArrayList<>();
        customers.add(marriot);

        this.customerRepository.save(customers);
    }
}
