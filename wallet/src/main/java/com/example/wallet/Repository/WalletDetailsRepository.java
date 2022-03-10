package com.example.wallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.db.dto.WalletDetailsEntity;

@Repository
@Transactional( readOnly = false )
public interface WalletDetailsRepository
		extends JpaRepository<WalletDetailsEntity, Integer> {

	@Query( value = "from WalletDetailsEntity T where T.id = :walletId" )
	WalletDetailsEntity getBalanceByWalletId( @Param( "walletId" ) int walletId );

}
