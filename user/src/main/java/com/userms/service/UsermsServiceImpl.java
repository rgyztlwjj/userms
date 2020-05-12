package com.userms.service;

import java.util.Calendar;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.userms.entity.BuyerEntity;
import com.userms.entity.SellerEntity;
import com.userms.repository.BuyerRepository;
import com.userms.repository.SellerRepository;
import com.userms.models.*;;

@Service
public class UsermsServiceImpl implements UsermsService {

	@Autowired
	private BuyerRepository buyerRepositor;
	
	@Autowired
	private SellerRepository sellerRepository;

	
	/**
	 * Login validate by username and password
	 * @param user UserModel
	 * @return user id
	 */
	@Override
	public int getUserId(UserModel user) {
		int userId;
		
		if ("1".equals(user.getRole())) {
			BuyerEntity buyer = buyerRepositor.findByUsername(user.getUsername());
			if (buyer == null) {
				return 0;
			}
			userId =buyer.getId();
			
		} else {
			SellerEntity seller = sellerRepository.findByUsername(user.getUsername());
			
			if (seller == null) {
				return 0;
			}
			userId =seller.getId();
			
		}
		
		return userId;
	}

	@Override
	public boolean signinBuyer(BuyerModel buyer) {
		
		BuyerEntity buyerentity = new BuyerEntity();
		BeanUtils.copyProperties(buyer, buyerentity);
		buyerentity.setCreateTime(Calendar.getInstance().getTime());
		try {

			buyerRepositor.save(buyerentity);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean signinSeller(SellerModel seller) {
		
		SellerEntity sellerentity = new SellerEntity();
		BeanUtils.copyProperties(seller, sellerentity);
		sellerentity.setCreateTime(Calendar.getInstance().getTime());
		try {

			sellerRepository.save(sellerentity);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
