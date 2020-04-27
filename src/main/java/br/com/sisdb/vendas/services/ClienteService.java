package br.com.sisdb.vendas.services;



import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.sisdb.vendas.domains.Cidade;
import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.domains.Endereco;
import br.com.sisdb.vendas.domains.enums.Perfil;
import br.com.sisdb.vendas.domains.enums.TipoCliente;
import br.com.sisdb.vendas.dto.ClienteDTO;
import br.com.sisdb.vendas.dto.ClienteNewDTO;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.repositories.EnderecoRepository;
import br.com.sisdb.vendas.security.UserSS;
import br.com.sisdb.vendas.services.exception.AuthorizationException;
import br.com.sisdb.vendas.services.exception.DataIntegrityException;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;


@Service
public class ClienteService {

	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired 
	private EnderecoRepository endRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3service;
	
	@Autowired
	private ImageService imgService;
	
	@Value("${img.prefix.client.profile}")
	private String prefixo;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente find(Long id) {
		
		UserSS user = UserService.authenticated();
		
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Cliente não encontrado Id: "+id + ", Tipos: "+ Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		obj = repository.save(obj);
		
		endRepository.saveAll(obj.getEnderecos());
		
	   return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());		
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

	public void delete(Long id) {		
		find(id);
		try {
		repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao excluir um Cliente!!!. Error: "+e.getMessage());
		}
	}

	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		System.out.println("Verificar email User: "+user.getUsername());
		System.out.println("Verificar email User: "+email);
		
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente obj = repository.findByEmail(email);
		if (obj == null) {
			throw new ObjctNotFoundException("Objeto não encontrado! Id: "+ user.getId()
			+ ", Tipo: "+Cliente.class.getName());
		}
		return obj;
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPages, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, 
				Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	
	public Cliente fromDTO(ClienteDTO objDTO) {		
		return new Cliente(objDTO.getId(), objDTO.getNome(), null, objDTO.getEmail(), null, null);
	}
	
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {		
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getCpfCnpj(), objDTO.getEmail(), TipoCliente.toEnum(objDTO.getTipoCli()), pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(objDTO.getTelefone1());
		
		
		if (objDTO.getTelefone2() !=  null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() !=  null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
    public URI uploadProfilePicture(MultipartFile multipartFile) {
    	
    	UserSS user = UserService.authenticated();
    	
    	if (user == null) {
    		throw new AuthorizationException("Acesso Negado !!!");
    	}
    	BufferedImage jpgImage = imgService.getJpgImageFromFile(multipartFile);
    	
    	jpgImage = imgService.corpSquare(jpgImage);
    	jpgImage = imgService.resize(jpgImage, size);
    	String fileName = prefixo + user.getId() + ".jpg";
    	
    	return s3service.uploadFile(imgService.getInputStream(jpgImage, "jpg"), fileName, "image");    		 	
    }

	
	
}







