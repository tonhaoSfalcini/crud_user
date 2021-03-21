package com.crud.service;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.exception.FileReadException;
import com.crud.exception.FileValidationException;
import com.crud.model.Usuario;
import com.crud.model.UsuarioFoto;

@Service
public class UsuarioService {

	/**
	 * Valida se o arquivo possui extensão <br>
	 * Valida se o arquivo está com a extensão dentro das permitidas <br>
	 * Define no model o nome e extensão do arquivo
	 * @param multipartFile
	 * @param usuarioFoto
	 * @throws FileValidationException 
	 * @throws Exception
	 */
	public void getNameAndExtension(MultipartFile multipartFile, UsuarioFoto usuarioFoto) throws FileValidationException {
		String fileName = null;
		String extension = null;
		
		fileName = multipartFile.getOriginalFilename();
		int indexOf = fileName.lastIndexOf('.');
		if (indexOf < 0) throw new FileValidationException("O arquivo não possui uma extensão, por favor nomeie-o corretamente.");
		
		extension = fileName.substring(++indexOf);
		if((extension.equalsIgnoreCase("jpeg") 
				||extension.equalsIgnoreCase("jpg") 
				|| extension.equalsIgnoreCase("png")) == false) throw new FileValidationException("A extensão informada não é aceita, por favor selecione um arquivo nas extensões: jpg, jpeg, png.");		
		
		usuarioFoto.setNome(fileName);
		usuarioFoto.setExtensao(extension);
	}

	public Byte[] getBynaryFromFile(MultipartFile foto) throws FileReadException {
		byte[] tmpFileContent =  new byte[0];
		try {
			tmpFileContent = foto.getInputStream().readAllBytes();
		}catch(IOException ioe) {
			throw new FileReadException(ioe.getMessage());
		}
		Byte[] byteFileContent = new Byte[tmpFileContent.length];
		
		for(int i = 0; i< tmpFileContent.length; i++) {
			byteFileContent[i] = tmpFileContent[i];
		}
		
		return byteFileContent;
	}

	public void fillDataUsuarioFoto(MultipartFile foto, UsuarioFoto usuarioFoto) throws FileValidationException, FileReadException {
		getNameAndExtension(foto, usuarioFoto);
		
		usuarioFoto.setDataCadastro(LocalDate.now());
		usuarioFoto.setContentType(foto.getContentType());
		usuarioFoto.setFile(getBynaryFromFile(foto));

	}
	
	public void fillDataUsuario(Usuario oldData, Usuario newData) {
		oldData.setCodigo(newData.getCodigo());
		oldData.setNome(newData.getNome());
		oldData.setDataNascimento(newData.getDataNascimento());
	}
}
