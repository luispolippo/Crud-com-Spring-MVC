package curso.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;



@Controller
public class PessoaController {
	
	private static final String PAGINACADASTRO = "cadastro/cadastropessoa";

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicio() {
		return listaPessoas();
	}

	@PostMapping(value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		
		return listaPessoas();

	}

	@GetMapping(value = "**/listapessoas")
	public ModelAndView listaPessoas() {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());

		return andView;
	}
	
	@GetMapping(value = "/editarpessoa/{idpessoa}")
	public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		andView.addObject("pessoaobj", pessoa.get());
		
		return andView;
	}
	
	@GetMapping(value = "/excluirpessoa/{idpessoa}")
	public ModelAndView excluirPessoa(@PathVariable("idpessoa") Long idpessoa) {
		
		pessoaRepository.deleteById(idpessoa);
		
		return listaPessoas();
		
	}
	
	@PostMapping(value = "**/pesquisarpessoa")
	public ModelAndView pesquisarNome(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		andView.addObject("pessoas", pessoaRepository.findByName(nomepesquisa));
		andView.addObject("pessoaobj", new Pessoa());
		
		return andView;
		
	}
	
	@GetMapping(value = "/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/telefones");
		andView.addObject("pessoaobj", pessoa.get());
		return andView;
		
	}
		
}
