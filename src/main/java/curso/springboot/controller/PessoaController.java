package curso.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}

	@PostMapping(value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		
		return listapessoas();

	}

	@GetMapping(value = "**/listapessoas")
	public ModelAndView listapessoas() {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());

		return andView;
	}
	
	@GetMapping(value = "/editarpessoa/{idpessoa}")
	public ModelAndView editarpessoa(@PathVariable("idpessoa") Long idpessoa) {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		andView.addObject("pessoaobj", pessoa.get());
		
		return andView;
	}
	
	@GetMapping(value = "/excluirpessoa/{idpessoa}")
	public ModelAndView excluirpessoa(@PathVariable("idpessoa") Long idpessoa) {
		
		pessoaRepository.deleteById(idpessoa);
		
		return listapessoas();
		
	}
		
}
