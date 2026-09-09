package a.c.execLivro;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
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
}
