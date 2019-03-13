package rental.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rental.model.Customer;
import rental.persistence.CustomerRepository;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getHotels(){
        List<Customer> customers = this.customerRepository.findAll();

        return customers;
    }
}
