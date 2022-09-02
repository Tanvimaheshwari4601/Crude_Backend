package net.user_details;

import net.user_details.model.Product;
import net.user_details.model.User;
import net.user_details.repository.ProductRepository;
import net.user_details.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudOperationsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsApplication.class, args);
	}
    @Autowired
	private UserRepository userrepository;

	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... args) throws Exception {
//		User user=new User();
//		user.setFirstname("Tanvi");
//		user.setLastname("Maheshwari");
//		user.setPassword("abcdefgh");
//		user.setEmailid("tanvimaheshwari67@gmail.com");
//		user.setPhonenumber("1234567890");
//		user.setCity("Udaipur");
//		user.setRole("Admin");
//		userrepository.save(user);

	}

//	@Override
//	public  void run(String... args) throws Exception{
////		Product product=new Product();
////		product.setProductTitle("Microwave");
////		product.setCategory("Electronic");
////		product.setPrice("45698.0");
////		product.setImgURL("hggihh");
////		product.setSellerId("ET-156");
////		productRepository.save(product);
//	}

}
