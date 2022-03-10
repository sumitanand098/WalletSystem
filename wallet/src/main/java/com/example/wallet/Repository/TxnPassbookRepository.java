package com.example.wallet.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.db.dto.WalletTxnPassbookEntity;

@Repository
@Transactional( readOnly = false )
public interface TxnPassbookRepository
		extends JpaRepository<WalletTxnPassbookEntity, Integer> {

	@Query( value = "from WalletTxnPassbookEntity T where T.drWalletId = :userId OR T.crWalletId = :userId order by T.createdAt DESC" )
	List<WalletTxnPassbookEntity> getPassbookDetailsById( @Param( "userId" ) int userId );

	@Query( value = "from WalletTxnPassbookEntity T where T.drWalletId = :userId and (T.createdAt BETWEEN :startDate and :endDate)" )
	List<WalletTxnPassbookEntity> getPassbookRangeDetailsById( @Param( "userId" ) int userId,
		@Param( "startDate" ) Date startDate, @Param( "endDate" ) Date endDate );

	@Query( value = "from WalletTxnPassbookEntity T where T.txnType = :txntype AND T.txnId = :txnId" )
	List<WalletTxnPassbookEntity> getValidRebate( @Param( "txnId" ) String txnId,
		@Param( "txntype" ) int txntype );

}
