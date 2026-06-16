import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável pelas operações CRUD da entidade Secretaria.
 *
 * Operações implementadas:
 *  - CREATE : create(Secretaria)
 *  - RETRIEVE: loadFromId(int) | loadAll()
 *  - UPDATE : update(Secretaria)
 *  - DELETE : delete(Secretaria) | deleteById(int)
 */
public class SecretariaRepository {

    private static Database database;
    private static Dao<Secretaria, Integer> dao;

    private List<Secretaria> loadedSecretarias;
    private Secretaria loadedSecretaria;

    public SecretariaRepository(Database database) {
        SecretariaRepository.setDatabase(database);
        this.loadedSecretarias = new ArrayList<>();
    }

    public static void setDatabase(Database database) {
        SecretariaRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Secretaria.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Secretaria.class);
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco de Secretárias: " + e);
        }
    }

    // =========================================================================
    // CREATE
    // =========================================================================

    /**
     * Persiste uma nova Secretaria no banco de dados.
     *
     * @param secretaria objeto a ser salvo
     * @return o próprio objeto salvo (com id preenchido pelo banco)
     */
    public Secretaria create(Secretaria secretaria) {
        try {
            int nrows = dao.create(secretaria);
            if (nrows == 0) {
                throw new SQLException("Erro: A tabela recusou a gravação da secretária.");
            }
            this.loadedSecretaria = secretaria;
            this.loadedSecretarias.add(secretaria);
            System.out.println("[CREATE] Secretária salva: " + secretaria);
        } catch (SQLException e) {
            System.out.println("Erro ao salvar secretária: " + e);
        }
        return secretaria;
    }

    // =========================================================================
    // RETRIEVE
    // =========================================================================

    /**
     * Busca uma Secretaria pelo seu ID.
     *
     * @param id identificador único gerado pelo banco
     * @return objeto Secretaria ou null se não encontrado
     */
    public Secretaria loadFromId(int id) {
        try {
            this.loadedSecretaria = dao.queryForId(id);
            if (this.loadedSecretaria != null) {
                System.out.println("[RETRIEVE] Secretária encontrada: " + this.loadedSecretaria);
            } else {
                System.out.println("[RETRIEVE] Nenhuma secretária com id=" + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar id " + id + ": " + e);
        }
        return this.loadedSecretaria;
    }

    /**
     * Retorna todas as Secretárias cadastradas no banco.
     *
     * @return lista de objetos Secretaria
     */
    public List<Secretaria> loadAll() {
        try {
            this.loadedSecretarias = dao.queryForAll();
            System.out.println("[RETRIEVE] Total de secretárias: " + this.loadedSecretarias.size());
            for (Secretaria s : this.loadedSecretarias) {
                System.out.println("  -> " + s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar secretárias: " + e);
        }
        return this.loadedSecretarias;
    }

    // =========================================================================
    // UPDATE
    // =========================================================================

    /**
     * Atualiza os dados de uma Secretaria já existente no banco.
     * O objeto deve ter um id válido (já persistido via create).
     *
     * @param secretaria objeto com os dados atualizados
     * @return true se a atualização foi bem-sucedida
     */
    public boolean update(Secretaria secretaria) {
        try {
            int nrows = dao.update(secretaria);
            if (nrows > 0) {
                System.out.println("[UPDATE] Secretária atualizada: " + secretaria);
                return true;
            } else {
                System.out.println("[UPDATE] Nenhuma linha afetada para id=" + secretaria.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar secretária: " + e);
        }
        return false;
    }

    // =========================================================================
    // DELETE
    // =========================================================================

    /**
     * Remove uma Secretaria do banco de dados pelo objeto.
     *
     * @param secretaria objeto a ser removido
     * @return true se a remoção foi bem-sucedida
     */
    public boolean delete(Secretaria secretaria) {
        try {
            int nrows = dao.delete(secretaria);
            if (nrows > 0) {
                System.out.println("[DELETE] Secretária removida: " + secretaria);
                this.loadedSecretarias.remove(secretaria);
                return true;
            } else {
                System.out.println("[DELETE] Nenhuma linha removida para id=" + secretaria.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar secretária: " + e);
        }
        return false;
    }

    /**
     * Remove uma Secretaria do banco de dados pelo ID.
     *
     * @param id identificador da secretária a ser removida
     * @return true se a remoção foi bem-sucedida
     */
    public boolean deleteById(int id) {
        try {
            int nrows = dao.deleteById(id);
            if (nrows > 0) {
                System.out.println("[DELETE] Secretária com id=" + id + " removida.");
                return true;
            } else {
                System.out.println("[DELETE] Nenhuma secretária com id=" + id + " encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar secretária por id: " + e);
        }
        return false;
    }
}
