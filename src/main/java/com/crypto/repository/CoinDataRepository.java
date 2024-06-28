package com.crypto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.entity.CoinData;

public interface CoinDataRepository extends JpaRepository<CoinData, Long> {
	
	List<CoinData> findByUserId(Long id);

}
