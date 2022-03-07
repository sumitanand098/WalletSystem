package com.example.wallet.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "wallet_users" )
public class UserWalletEntity {

	@Id @SequenceGenerator( name = "seq_user_wallet", sequenceName = "seq_user_wallet",
			allocationSize = 1 ) @GeneratedValue( strategy = GenerationType.SEQUENCE,
					generator = "seq_user_wallet" ) @Column( name = "id" ) private int id;
	@Column( name = "first_name" ) private String firstName;
	@Column( name = "last_name" ) private String lastName;
	@Column( name = "user_name" ) private String username;
	@Column( name = "email" ) private String email;
	@Column( name = "user_pwd" ) private String userPwd;
	@Column( name = "created_at", insertable = false,
			updatable = false ) private Date createdAt;
	@Column( name = "is_active" ) private int isActive;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd( String userPwd ) {
		this.userPwd = userPwd;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( Date createdAt ) {
		this.createdAt = createdAt;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive( int isActive ) {
		this.isActive = isActive;
	}

}