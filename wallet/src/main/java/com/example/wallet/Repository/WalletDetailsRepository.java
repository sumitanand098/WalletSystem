package com.example.wallet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.db.dto.WalletPassbookEntity;

@Repository
@Transactional( readOnly = false )
public interface WalletDetailsRepository extends JpaRepository<WalletPassbookEntity, Integer> {

	@Query( value = "from WalletPassbookEntity T where T.userId = :userId" )
	List<WalletPassbookEntity> getPassbookDetailsById( @Param( "userId" ) int userId );

	@Query( value = "select totAccAmt from WalletPassbookEntity T where T.userId = :userId and status = true order by id DESC" )
	List<Double> getTotalAmountById( @Param( "userId" ) int userId );

	@Query( value = "from WalletPassbookEntity T where T.userId = :userId and T.createdAt >= :startDate and T.createdAt <= :endDate" )
	List<WalletPassbookEntity> getPassbookRangeDetailsById( @Param( "userId" ) int userId, @Param( "startDate" ) String startDate, @Param( "endDate" ) String endDate );

}
