package net.user_details.service;

import net.user_details.model.Product;
import net.user_details.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) { return productRepository.save(product); }

    public Optional<Product> findById(long id) { return productRepository.findById(id); }



    public void delete(Product product) { productRepository.delete(product); }

    public Object findAll() { return productRepository.findAll(); }

}
