
package escola;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public abstract class AlunoDAO extends GenericDAO <Aluno, String>{

    /**
     *
     * @return
     */
	public List<Aluno> obterTodos() {
            List<Aluno> lista = new ArrayList<>();
            try {
                ResultSet r1 = getStatement().executeQuery("SELECT * FROM ALUNO");

                while(r1.next())
                    lista.add(new Aluno(r1.getString("MATRICULA"),
                    r1.getString("NOME"),r1.getInt("ENTRADA")));

                closeStatement(r1.getStatement());

            }catch(Exception e){}    

            return lista;        
	}
        
    public Aluno obter(String chave) {
        Aluno aluno = null;

        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM ALUNO WHERE MATRICULA = ?");
            ps.setString(1, chave);
            ResultSet r1 = ps.executeQuery();

            if (r1.next())
                aluno = new Aluno(r1.getString("MATRICULA"), r1.getString("NOME"), r1.getInt("ENTRADA"));
            closeStatement(ps);

        } catch (Exception e) {}

        return aluno;
    }
    
//    public void incluir(Aluno entidade) {
//        try {
//            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO ALUNO VALUES (?, ?, ?)");
//            ps.setString(1, entidade.matricula);
//            ps.setString(2, entidade.nome);
//            ps.setInt(3, entidade.ano);
//            
//            ps.executeUpdate();
//            closeStatement(ps);
//            
//        } catch (Exception e) {}
//    }
    
    public void incluir(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJavaDB01PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(aluno);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        } finally {
            em.close();
        }
    }
    
//    public void excluir(String chave) {
//        try {
//            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM ALUNO WHERE MATRICULA = ?");
//            ps.setString(1, chave);
//            
//            ps.executeUpdate();
//            closeStatement(ps);
//            
//        } catch (Exception e) {}
//    }
    
    public void excluir(String chave) {
        
        Connection c1 = null;
        
        try {
            c1 = getConnection();
            c1.setAutoCommit(false);
            
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM ALUNO WHERE MATRICULA = ?");
            ps.setString(1, chave);
            ps.executeUpdate();
            
            c1.commit();
            closeStatement(ps);
            
        } catch (Exception e) {

            if (c1 != null)
                            try {
                c1.rollback();
                c1.close();
            } catch (SQLException e2) {}
        }
    }
    
    public void alterar(Aluno entidade) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE ALUNO SET NOME = ?, ENTRADA = ? " + " WHERE MATRICULA = ?");
            ps.setString(1, entidade.nome);
            ps.setInt(2, entidade.ano);
            ps.setString(3, entidade.matricula);
            
            ps.executeUpdate();
            closeStatement(ps);
            
        } catch (Exception e) {}
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO() {};
        
        dao.obterTodos().forEach(aluno -> {
            System.out.println(aluno.nome);
        });
    }
}