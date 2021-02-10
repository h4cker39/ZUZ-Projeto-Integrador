package com.zeroum.zuz.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeroum.zuz.model.User;
import com.zeroum.zuz.model.UsuarioLogin;
import com.zeroum.zuz.repository.UsuarioRepository;
import import org.owasp.encoder.Encode;
/**
 Import via pom.xml OWASP URI Encoder current one usage may present vulnerabilities.
 https://www.codota.com/code/java/classes/org.owasp.encoder.Encode
 
 <!-- https://mvnrepository.com/artifact/org.owasp.encoder/encoder -->
<dependency>
    <groupId>org.owasp.encoder</groupId>
    <artifactId>encoder</artifactId>
    <version>1.2.3</version>
</dependency>
 
**/

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public User CadastrarUsuario(User usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//Owasp is more reliable for encoding to prevent vulnerabilities.
		String senhaEncoder = Encoder.forUriComponent(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		
		return repository.save(usuario);
	}
	
	public Optional<UsuarioLogin> Logar (Optional<UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<User> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[]  encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setUsuario(usuario.get().getNome()); 
				
				return user;
				
			}
		}
	return null;
	
	}
	
}
