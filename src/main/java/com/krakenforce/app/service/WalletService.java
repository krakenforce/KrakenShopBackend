package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.Wallet;
import com.krakenforce.app.repository.WalletRepository;

@Service
@Transactional
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	public Wallet add(Wallet wallet) {
		return walletRepository.save(wallet);
	}
	
	public Wallet getById(int walletId) {
		return walletRepository.findById(walletId).orElse(null);
	}
	
	public Wallet updateWallet(int walletId, float total) {
		Wallet wallet = walletRepository.findById(walletId).orElse(null);
		float newBalance = wallet.getBalance() - total;
		wallet.setBalance(newBalance);
		walletRepository.save(wallet);
		return wallet;
	}
}
