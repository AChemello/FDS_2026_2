package a.c.execLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface IAcervo{
    List<Livro> listarLivros();
    List<String> listarAutores();
    List<String> LivrosAutorAno(String autor, int ano);
}
