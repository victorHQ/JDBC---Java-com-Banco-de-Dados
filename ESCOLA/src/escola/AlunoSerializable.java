
package escola;

import java.io.Serializable;

@Entity
@Table(name = "ALUNO")
@NamedQueries({ @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")})

public class AlunoSerializable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MATRICULA")
    private String matricula;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ENTRADA")
    private Integer ano;

    public Aluno() {}

    public Aluno(String matricula) {
        this.matricula = matricula;
    }
    // getters e setters para os atributos internos
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJavaDB01PU");
    EntityManager em = emf.createEntityManager();
    Query query = em.createNamedQuery("Aluno.findAll", Aluno.class);
    List<Aluno> lista = query.getResultList();

    lista.forEach (aluno ->{
        System.out.println(aluno.getNome());
    });
}
