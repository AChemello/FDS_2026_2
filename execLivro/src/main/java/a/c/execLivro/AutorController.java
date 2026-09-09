package a.c.execLivro;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class AutorController {
    private final Acervo acervo;
    @Autowired
    public AutorController(Acervo acervo) {
        this.acervo = acervo;
    }
}
