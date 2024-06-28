package com.crypto.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crypto.entity.CoinData;
import com.crypto.repository.CoinDataRepository;

@Service
public class CoinService {
	
	@Autowired
	private CoinDataRepository coinDataRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${coinmarketcap.api.url}")
	private String apiUrl;
	
	@Value("${coinmarketcap.api.key}")
	private String apiKey;
	
	public String getCoinData(String coinName, Long userId) {
		String url = apiUrl + "?symbol=" + coinName;
		String response = restTemplate.getForObject(url, String.class);
		
		CoinData coinData = new CoinData();
		coinData.setUserId(userId);
		coinData.setCoidName(coinName);
		coinData.setCoidData(response);
		coinData.setTimestamp(LocalDateTime.now());
		coinDataRepository.save(coinData);
		
		return response;
	}
	
	public List<CoinData> getUserCoinData(Long userId){
		return coinDataRepository.findByUserId(userId);
	}
	
	

}
