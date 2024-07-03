package com.morningempire.models;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private String description;

    private BigDecimal price;
    
    private Integer quantityAvailable;
    
    @Column(updatable=false)
    private Date createdAt;

    private Date updatedAt;
    
//    @ManyToMany(mappedBy = "products")
//    private Set<Cart> carts;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "carts_products", 
        joinColumns = @JoinColumn(name = "product_id"), 
        inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private Set<Cart> carts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_products", 
            joinColumns = @JoinColumn(name = "product_id",insertable = false, updatable = false), 
            inverseJoinColumns = @JoinColumn(name = "product_id")
        )
    private List<Order> orders;

    // Constructors, getters, and setters

    public Product() {
        // Default constructor
    }

    public Product(Long productId, String name, String description, BigDecimal price, Date createdAt, Date updatedAt) {
        this.productId = productId;
    	this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
		this.updatedAt = updatedAt;        
    }

    // Getters and setters for all fields

    public Long getProductId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }    
    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
//    // Override toString() for debugging or logging purposes
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", quantityAvailable=" + quantityAvailable +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
}
