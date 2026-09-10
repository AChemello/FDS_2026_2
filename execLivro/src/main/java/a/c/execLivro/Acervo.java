package a.c.execLivro;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public class Acervo{
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getAll() {
        return livros;
    }

    public List<String> getAutores() {
        return getAll().stream()
                .map(Livro::getAutor)
                .distinct()
                .toList();
    }

    public List<String> getLivrosAutorAno(String autor, int ano) {
        return getAll().stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor) && l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }

    public Optional<Livro> getLivroById(int id) {
        return getAll().stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }

    public Optional<Livro> getLivroByTitulo(String titulo) {
        return getAll().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public void removeLivro(int id) {
        getLivroById(id).ifPresent(livro -> livros.remove(livro));
    }

    public void updateLivro(int id, Livro livroAtualizado) {
        getLivroById(id).ifPresent(livro -> {
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setAno(livroAtualizado.getAno());
        });
    }

    public void addLivro(Livro livro) {
        livros.add(livro);
    }
}
