package com.cg.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Loan;
import com.cg.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
@Validated
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    public Loan createLoan(@Valid @RequestBody Loan loan) {
        return service.createLoan(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return service.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return service.getLoanById(id);
    }

    @PutMapping("/{id}/status")
    public Loan updateStatus(@PathVariable Long id, @RequestBody Loan request) {
        return service.updateStatus(id, request.getStatus());
    }
}