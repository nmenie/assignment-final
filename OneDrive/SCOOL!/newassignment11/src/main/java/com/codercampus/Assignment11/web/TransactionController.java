package com.codercampus.Assignment11.web;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;

@Controller
public class TransactionController {

	private TransactionRepository transactionRepository;

	public TransactionController(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@GetMapping("/transactions")
	public String getAllTransactions(Model model) {
		List<Transaction> transactions = transactionRepository.findAll();

		transactions.sort(Comparator.comparing(Transaction::getDate));
		model.addAttribute("transactions", transactions);
		return "transactions";
	}

	@GetMapping("/transactions/{id}")
	public String getTransactionDetails(@PathVariable("id") Long id, Model model) {

		Transaction transaction = transactionRepository.findById(id);
		model.addAttribute("transaction", transaction);
		return "transaction-details";
	}

}
