package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Project;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	protected UserRepository userRepository;

	@Transactional
	public User getUser(Long id) {
		Optional<User> result = this.userRepository.findById(id);
		return result.orElse(null);
	}


	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}


	@Transactional
	public List<User> getAllUsers(){
		List<User> result = new ArrayList<>();
		Iterable<User> lista = this.userRepository.findAll();
		for(User iteratore : lista) {
			result.add(iteratore);
		}
		return result;
	}


	@Transactional
	public List<User> getMembers(Project project) {
		List<User> lista = new ArrayList<>();
		for(User iteratore : this.userRepository.findByVisibleProjects(project)) {
			lista.add(iteratore);
		}
		return lista;
	}



}
