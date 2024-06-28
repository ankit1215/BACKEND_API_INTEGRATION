package com.crypto.controller;



import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.entity.CoinData;
import com.crypto.entity.User;
import com.crypto.service.CoinService;
import com.crypto.service.UserService;

@RestController
@RequestMapping("/api/coins")
public class CoinController {
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{coinName}")
	public String getCoinData(@PathVariable String coinName, Principal principal) {
		Long userId = getUserIdFromPrincipal(principal);
		return coinService.getCoinData(coinName, userId);
	}
	
	@GetMapping("/user")
	public List<CoinData> getUserCoinData(Principal principal){
		Long userId = getUserIdFromPrincipal(principal);
		return coinService.getUserCoinData(userId);
	}
	
	private Long getUserIdFromPrincipal(Principal principal) {
		Optional<User> user = userService.findbyUsername(principal.getName());
        return user.map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
