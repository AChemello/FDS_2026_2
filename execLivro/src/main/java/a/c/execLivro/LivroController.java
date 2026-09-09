package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
public class LivroController {
    private final Acervo acervo;

    @Autowired
    public LivroController(Acervo acervo) {
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
        return acervo.getAll();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return acervo.getAutores();
    }

    @GetMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroById(@PathVariable int id) {
        Optional<Livro> livro = acervo.getAll().stream()
                .filter(l -> l.getId() == id)
                .findFirst();
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("livros/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosByAutorAndAno(@PathVariable String autor, @PathVariable int ano) {
        return acervo.getAll().stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor) && l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }
}
