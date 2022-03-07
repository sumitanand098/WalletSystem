package com.example.wallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.wallet.db.dto.UserWalletEntity;

public interface WalletUserRepository extends JpaRepository<UserWalletEntity, Integer> {

	@Query( value = "select u from UserWalletEntity u where u.username = ?1" )
	UserWalletEntity findByUsername( String email );

}
