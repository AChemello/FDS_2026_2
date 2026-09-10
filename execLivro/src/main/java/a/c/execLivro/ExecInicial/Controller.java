package a.c.execLivro.ExecInicial;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {
    private List<Livro> livros = new LinkedList<>();

    public Controller() {
        livros.add(new Livro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro(2, "1984", "George Orwell", 1949));
        livros.add(new Livro(3, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943));
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return livros;
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return livros.stream()
                .map(l -> l.getAutor())
                .distinct()
                .toList();
    }

    @GetMapping("titulos")
    @CrossOrigin(origins = "*")
    public List<String> getListaTitulos() {
        return livros.stream()
                .map(l -> l.getTitulo())
                .toList();
    }

    @GetMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroById(@PathVariable int id) {
        Optional<Livro> livro = livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("livros")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> addLivro(@RequestBody Livro livro) {
        livros.add(livro);
        return ResponseEntity.status(201).body(livro);
    }

    @PutMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> updateLivro(@PathVariable int id, @RequestBody Livro livro) {
        Optional<Livro> livroExistente = livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst();

        if (livroExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Livro livroAtualizado = livroExistente.get();
        livroAtualizado.setTitulo(livro.getTitulo());
        livroAtualizado.setAutor(livro.getAutor());
        livroAtualizado.setAno(livro.getAno());
        return ResponseEntity.ok(livroAtualizado);
    }
}

// ./mvnw spring-boot:run
