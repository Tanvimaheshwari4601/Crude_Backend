package net.user_details.controller;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate;
import net.user_details.exception.ResourceNotFoundException;
import net.user_details.model.Product;
import net.user_details.model.User;
import net.user_details.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct() {
        return (List<Product>) productService.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){

        return productService.save(product);

    }



    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Product product=productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with such id didn't exist" + id));

        return ResponseEntity.ok(product);

    }


    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product user_details) {
        Product updateProduct=productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product with such id exist: " + id));

        updateProduct.setProductTitle((user_details.getProductTitle()));
        updateProduct.setCategory((user_details.getCategory()));
        updateProduct.setPrice((user_details.getPrice()));
        updateProduct.setImgURL((user_details.getImgURL()));
        updateProduct.setSellerId((user_details.getSellerId()));

        productService.save(updateProduct);

        return ResponseEntity.ok(updateProduct);

    }


    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        Product product=productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Product with such id exist: " + id));

        productService.delete(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
