package br.com.iperonprev.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriptografiaSenha {
	
	public static String criptografa(String senha) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
	}

}
