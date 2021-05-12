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

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping(value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}

	@PostMapping(value = "/salvarpessoa")
	public String salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		return "cadastro/cadastropessoa";

	}

	@GetMapping(value = "/listapessoas")
	public ModelAndView pessoas() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);

		return andView;
	}

}
