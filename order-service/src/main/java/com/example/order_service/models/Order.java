package com.example.order_service.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    @Field
    private String customerId;

    @Field
    private String productId;

    @Field
    private Integer quantity;

    @Field
    private String employeeId;

    @Field
    private String date;
}
