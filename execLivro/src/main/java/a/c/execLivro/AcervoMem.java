package a.c.execLivro;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class AcervoMem implements IAcervo {
    private List<Livro> livros = new ArrayList<>();

    @Override
    public List<Livro> listarLivros() {
        return livros;
    }

    @Override
    public List<String> listaAutores() {
        return livros.stream()
                .map(Livro::getAutor)
                .distinct()
                .toList();
    }

    @Override
    public List<String> LivrosAutorAno(String autor, int ano) {
        return livros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor) && l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }

    @Override
    public void adicionar(Livro livro) {
        livros.add(livro);
    }
}
