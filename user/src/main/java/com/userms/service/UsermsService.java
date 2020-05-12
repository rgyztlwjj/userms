package com.userms.service;

import com.userms.models.*;

public interface UsermsService {
	
	/**
	 * Login validate by username and password
	 * @param user UserModel
	 * @return true:login sucessful  false:login unsucessful
	 */
	public int getUserId(UserModel user);
	
	public boolean signinBuyer(BuyerModel buyer);
	
	public boolean signinSeller(SellerModel seller);
}
