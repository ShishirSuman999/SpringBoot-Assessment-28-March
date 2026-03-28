package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.Loan;
import com.cg.exception.*;
import com.cg.repo.LoanRepo;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepo repo;

    public LoanServiceImpl(LoanRepo repo) {
        this.repo = repo;
    }

    @Override
    public Loan createLoan(Loan loan) {
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }
        repo.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING")
            .ifPresent(l -> {
                throw new DuplicateLoanApplicationException("User already has a pending loan");
            });
        loan.setStatus("PENDING");
        return repo.save(loan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getAllLoans() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Loan getLoanById(Long id) {
        return repo.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public Loan updateStatus(Long id, String status) {
        Loan loan = getLoanById(id);
        if (!status.equalsIgnoreCase("APPROVED") && !status.equalsIgnoreCase("REJECTED")) {
            throw new RuntimeException("Invalid status value");
        }
        loan.setStatus(status.toUpperCase());
        return repo.save(loan);
    }
}