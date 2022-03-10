package com.example.wallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.db.dto.TransactionDetailsEntity;

@Repository
@Transactional( readOnly = false )
public interface TransactionDetailsRepository
		extends JpaRepository<TransactionDetailsEntity, Integer> {

}
