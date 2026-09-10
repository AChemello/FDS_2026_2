package a.c.execLivro;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
public class AutorController {
    private final IAcervo acervo;
    @Autowired
    public AutorController(IAcervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return acervo.listaAutores();
    }

    @GetMapping("livros/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosByAutorAndAno(String autor, int ano) {
        return acervo.LivrosAutorAno(autor, ano);
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public void adicionarAutor(@RequestBody String autor) {
        // Implementação para adicionar um autor
    }

}
