package com.learnSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entities.Users;
import com.learnSphere.repository.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService{

	@Autowired
	UsersRepository repo;
	
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User added !";
	}
		@Override
		public Users findUserByEmail(String email) {
		return repo.findByEmail(email);   // you have to provide signatures
		
	}
		@Override
		public boolean checkEmail(String email) {
			
			return repo.existsByEmail(email);
		}
		@Override
		public String saveUsers(Users user) {
			repo.save(user);
			return "user updated";
		}
}
