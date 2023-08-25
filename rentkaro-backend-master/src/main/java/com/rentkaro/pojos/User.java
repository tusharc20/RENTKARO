package com.rentkaro.pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "user_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userAddress")
public class User extends UserEntity implements UserDetails{

	@Column(name = "first_name", length = 50)
	private String firstName;
	@Column(name = "last_name", length = 50)
	private String lastName;
	@Column(name = "img_path")
	private String imgPath;
	@Column(name = "email_id", length = 50, unique = true)
	private String userEmail;
	@Column(name = "user_password")
	private String userPassword;
//	@Transient
//	private String coinfirmPassword;
	@Column(name = "user_mobile_no", unique = true)
	private Long userMobileNo;
	@Column(name = "is_ban",columnDefinition = "tinyint(1) default 0")
	private boolean isBan;
	@Column(name = "auth_key")
	private String authKey;
	@Column(name = "reports")
	private byte reports;
	@Column(name = "is_subscribe",columnDefinition = "tinyint(1) default 0")
	private boolean isSub;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_address_id")
	private UserAddress userAddress;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> ownedProductList = new ArrayList<Product>();
	@OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> rentedProduct = new ArrayList<Product>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cart")
	private WishList cart;
	@OneToMany
	@JoinColumn(name = "orderList")
	List<OrderHistory> orderList = new ArrayList<OrderHistory>();
	
	public void addToOrderList(OrderHistory obj) {
		this.orderList.add(obj);
	}
	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
