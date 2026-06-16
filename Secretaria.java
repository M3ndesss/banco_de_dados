import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

/**
 * Modelo que representa uma Secretária no sistema hospitalar.
 * Mapeada para a tabela "Secretárias" via ORMLite.
 */
@DatabaseTable(tableName = "Secretárias")
public class Secretaria extends Usuario {

    @DatabaseField
    private String senha;

    /** Construtor vazio obrigatório para o ORMLite */
    public Secretaria() {
        super(null, null, 0);
    }

    public Secretaria(String nome, String CPF, String senha) {
        super(nome, CPF, 0);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Secretaria{id=" + id + ", nome='" + nome + "', CPF='" + CPF + "'}";
    }
}
