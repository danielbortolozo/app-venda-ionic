package br.com.sisdb.vendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.security.UserSS;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Email :"+email);
		Cliente cli = repo.findByEmail(email);
		if (cli == null) {
			System.out.println("Entrei aqui");
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}








