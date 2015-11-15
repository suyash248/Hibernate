package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter @Setter
@Entity  
@Table(name="STUDENTS_T")  
public class Student {  
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="STUDENT_ID")
    private int id;  
    
    @Column(name="STUDENT_NAME")
    private String studentName;  
    
    @OneToMany(mappedBy="student", cascade=CascadeType.ALL)   
    @BatchSize(size = 3) 
    private List<Address> address = new ArrayList<Address>();   
}  