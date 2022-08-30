package net.user_details.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name="product_title",nullable = false)
    @NotEmpty
    private String productTitle;

    @Column(name = "category",nullable = false)
    @NotEmpty
    private String category;

    @Column(name = "image_URL",nullable = false)
    private String imgURL;

    @Column(name = "price",nullable = false)
    @NotEmpty
    private String price;

    @NotEmpty
    @Column(name = "seller_id",nullable = false)
    private String sellerId;




}
