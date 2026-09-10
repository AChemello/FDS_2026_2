package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LivroController {
    private final IAcervo  acervo;

    @Autowired
    public LivroController(IAcervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return acervo.listarLivros();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return acervo.listaAutores();
    }

    @GetMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroById(@PathVariable int id) {
        Optional<Livro> livro = acervo.listarLivros().stream()
                .filter(l -> l.getId() == id)
                .findFirst();
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("livros/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosByAutorAndAno(@PathVariable String autor, @PathVariable int ano) {
        return acervo.LivrosAutorAno(autor, ano);
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public void adicionarLivro(@RequestBody Livro livro) {
        // Implementação para adicionar um livro
    }
}
