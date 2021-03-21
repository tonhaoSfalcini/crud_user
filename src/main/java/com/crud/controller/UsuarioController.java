package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.exception.FileReadException;
import com.crud.exception.FileValidationException;
import com.crud.exception.ResourceNotFoundException;
import com.crud.model.Usuario;
import com.crud.model.UsuarioFoto;
import com.crud.repository.UsuarioFotoRepository;
import com.crud.repository.UsuarioRepository;
import com.crud.service.UsuarioService;

@RestController
@RequestMapping("/api/v2")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioFotoRepository usuarioFotoRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "usuario/insert")
	public Usuario insertUsuario(@RequestBody Usuario usuario) {
		
		return this.usuarioRepository.save(usuario);
	}

	@PutMapping(value = "usuario/update/foto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Usuario insertUsuarioFoto(@PathVariable("id") Long id,@RequestParam("foto") MultipartFile foto) throws ResourceNotFoundException, FileValidationException, FileReadException {
		
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado no id"+id));
		
		UsuarioFoto usuarioFoto = new UsuarioFoto();
		usuarioService.fillDataUsuarioFoto(foto, usuarioFoto);
		
		usuarioFotoRepository.save(usuarioFoto);
		
		usuario.setFoto(usuarioFoto);

		return this.usuarioRepository.save(usuario);
	}

	@PutMapping("usuario/update/{id}")
	public ResponseEntity<Usuario> updateUsuario(
			@PathVariable(value="id") Long usuarioId, 
			@RequestBody Usuario newData) throws ResourceNotFoundException {
		
		Usuario usuario = this.usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado nesse id"));
		
		usuarioService.fillDataUsuario(usuario, newData);
		
		return ResponseEntity.ok(this.usuarioRepository.save(usuario));
	}

	@GetMapping("usuario")
	public List<Usuario> getAllUsuario(){
		return this.usuarioRepository.findAll();
	}

	@GetMapping("usuario/search/id/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value="id") Long usuarioId) throws ResourceNotFoundException{
		Usuario usuario = this.usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado nesse id"));
		
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("usuario/search/codigo/{codigo}")
	public ResponseEntity<Usuario> getUsuarioByCodigo(@PathVariable(value="codigo") Integer usuarioCodigo) throws ResourceNotFoundException{
		Usuario usuario = this.usuarioRepository.findByCodigo(usuarioCodigo)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado nesse codigo"));
		
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("usuario/search/nome/{nome}")
	public List<Usuario> getUsuarioLikeNome(@PathVariable(value="nome") String usuarioNome){
		return this.usuarioRepository.findByNomeLike("%"+usuarioNome+"%");
	}

	@DeleteMapping("usuario/delete/{id}")
	public Boolean	deleteUsuario(@PathVariable(value="id") Long usuarioId) throws ResourceNotFoundException {
		Usuario usuario = this.usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado nesse id"));
		
		this.usuarioRepository.delete(usuario);
		return true;
	}

	
}
