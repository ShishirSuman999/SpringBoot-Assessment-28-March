package com.cg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Applicant name cannot be empty")
    @Column(name = "applicant_name")
    private String applicantName;

    @Positive(message = "Loan amount must be positive")
    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "status")
    private String status;

    public Loan() {
    	
    }

    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

    public String getApplicantName() {
    	return applicantName;
    }
    
    public void setApplicantName(String applicantName) {
    	this.applicantName = applicantName;
    }

    public double getLoanAmount() {
    	return loanAmount;
    }
    
    public void setLoanAmount(double loanAmount) {
    	this.loanAmount = loanAmount;
    }

    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
}