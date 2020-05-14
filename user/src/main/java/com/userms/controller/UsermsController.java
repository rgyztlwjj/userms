package com.userms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userms.service.UsermsService;
import com.userms.util.JwtUtil;
import com.userms.models.BuyerModel;
import com.userms.models.SellerModel;
import com.userms.models.UserModel;

@RestController
@RequestMapping(value = "/user")
public class UsermsController {
	@Autowired
	private UsermsService userService;
	
	@Autowired
	private JwtUtil jwt;
    
	@PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel user) {
		
		int userId = userService.getUserId(user);
		
		if (userId==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		}
		
		//create token
		user.setId(userId);
		user.setToken(jwt.generateToken(userId));
		return ResponseEntity.ok(user);
		
    }
	
	/**
	 * 
	 * @param buyer
	 * @return
	 */
	@PostMapping("/signinbuyer")
	public ResponseEntity<BuyerModel> signinAsBuyer(@RequestBody BuyerModel buyer) {
		if(userService.signinBuyer(buyer)) {

			return ResponseEntity.status(HttpStatus.CREATED).body(buyer);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	
	/**
	 * 
	 * @param seller
	 * @return
	 */
	@PostMapping("/signinseller")
	public ResponseEntity<SellerModel> signinAsBuyer(@RequestBody SellerModel seller) {
		if(userService.signinSeller(seller)) {

			return ResponseEntity.status(HttpStatus.CREATED).body(seller);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}