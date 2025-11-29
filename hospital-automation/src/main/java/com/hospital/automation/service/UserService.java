package com.hospital.automation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.automation.dto.LoginDto;
import com.hospital.automation.model.User;
import com.hospital.automation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	
	public User login(LoginDto loginDto) {
		
		User user = userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
		
		if(!user.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Şifre Hatalı");
		}
		
		return user;
	}
}
