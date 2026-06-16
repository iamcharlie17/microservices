package com.example.employee_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
  @Id
  private String id;

  @Field
  private String name;

  @Field
  private String designation;

  @Field
  private String salary;
}
