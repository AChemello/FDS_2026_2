package a.c.execLivro;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Controller {
    private Acervo acervo;

    @Autowired
    public Controller(Acervo acervo) {
        this.acervo = acervo;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public void adicionarLivro(@RequestBody Livro livro) {
        acervo.addLivro(livro);
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public List<Livro> getAll() {
        return acervo.getAll();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getAutores() {
        return acervo.getAutores();
    }

    @GetMapping("livros/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosAutorAno(@PathVariable String autor, @PathVariable
    int ano) {
            return acervo.getLivrosAutorAno(autor, ano);
        }

    @GetMapping ("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroById(@PathVariable int id) {
        Optional<Livro> livro = acervo.getLivroById(id);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping ("livros/titulo/{titulo}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroByTitulo(@PathVariable String titulo) {
        Optional<Livro> livro = acervo.getLivroByTitulo(titulo);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping ("livros/{id}")
    @CrossOrigin(origins = "*")
    public void removeLivro(@PathVariable int id) {
        acervo.removeLivro(id);
    }

    @PutMapping ("livros/{id}")
    @CrossOrigin(origins = "*")
    public void updateLivro(@PathVariable int id, @RequestBody Livro livroAtualizado) {
        acervo.updateLivro(id, livroAtualizado);
    }
}

// ./mvnw spring-boot:run
