import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author eyvind
 */
public class ManyToOneTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        Query q=em.createQuery("select o from Family o");
        int size=q.getResultList().size();
        if(size<1)
        {

            Family f = new Family();
            f.setDescription("testers");
            List<Person> members = new ArrayList();
            for (int i = 0; i < 40; i++) {
                Person person = new Person();
                person.setFname("Jim " + i);
                person.setLname("Jones " + i);
                person.setFamily(f);
                members.add(person);
            }
            f.setPersons(members);
            try {
                em.persist(f);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            } finally {
                em.close();
                emf.close();
            }
        }


    }



}