package a.c.execLivro;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LivrosRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Livro> livroRowMapper = new RowMapper<>() {
        @Override
        public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("ano")
            );
        }
    };

    private void criarTabelaSeNecessario(){
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS livros (
                    id INT PRIMARY KEY,
                    titulo VARCHAR(255) NOT NULL,
                    autor VARCHAR(255) NOT NULL,
                    ano INT NOT NULL)
                """);
    }

    public list<Livro> findAll() {
        return jdbcTemplate.query("SELECT id, titulo, autor, ano FROM livros",
                livroRowMapper);
    }

}
