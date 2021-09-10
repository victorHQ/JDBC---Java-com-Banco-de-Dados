/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class ESCOLA {
    
    public void getDelete() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection c1 = DriverManager.getConnection("jdbc:derby://localhost:1527/ESCOLA", "escola", "escola");
        PreparedStatement ps = c1.prepareStatement( "DELETE FROM ALUNO WHERE ENTRADA = ?");
        ps.setInt(1,2018);
        ps.executeUpdate();
        ps.close();
        c1.close();
    }
    
    public void getResult() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection c1 = DriverManager.getConnection("jdbc:derby://localhost:1527/ESCOLA", "escola", "escola");
        Statement st = c1.createStatement();
        ResultSet r1 = st.executeQuery("SELECT * FROM ALUNO");
        
        while(r1.next())
            System.out.println("Aluno: " + r1.getString("NOME")+ " - " + r1.getInt("ENTRADA"));
        
        r1.close();
        st.close();
        c1.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // passo 1
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        // passo 2
        Connection c1 = DriverManager.getConnection("jdbc:derby://localhost:1527/ESCOLA", "escola", "escola");
        // passo 3
        Statement st = c1.createStatement();
        // passo 4
        st.executeUpdate("INSERT INTO ALUNO VALUES('E2018.5004','DENIS',2018)");
        st.close();
        c1.close();
    }
}
