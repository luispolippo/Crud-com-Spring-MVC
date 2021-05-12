package curso.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String inicio() {
		return PAGINACADASTRO;
	}

	@PostMapping(value = "/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		
		return listapessoas();

	}

	@GetMapping(value = "/listapessoas")
	public ModelAndView listapessoas() {
		
		ModelAndView andView = new ModelAndView(PAGINACADASTRO);
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);

		return andView;
	}

}
