package escola;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
/**
 *
 * @author victo
 */
public class Aluno {
    
    public String matricula, nome;
    public int ano;
    
    public Aluno(){}
    
    public Aluno(String matricula, String nome, int ano){
        this.matricula = matricula;
        this.nome = nome;
        this.ano = ano;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Aluno> lista = new ArrayList<>();
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection c1 = DriverManager.getConnection("jdbc:derby://localhost:1527/ESCOLA", "escola", "escola");
        
        Statement st = c1.createStatement();
        ResultSet r1 = st.executeQuery("SELECT * FROM ALUNO");
        
        while(r1.next())
            lista.add(new Aluno(r1.getString("MATRICULA"),
            r1.getString("NOME"),
            r1.getInt("ENTRADA")));
        
        r1.close();
        st.close();
        c1.close();
        
        for (Aluno aluno : lista) {
            System.out.println("Aluno: " + aluno.nome + "( " + aluno.matricula + ") - " + aluno.ano);
        }
    }
}
