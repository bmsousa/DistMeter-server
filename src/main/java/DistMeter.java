import components.distconfmanager.DistConfManager;
import components.disttestserver.DistmeterTestServer;
import conf.ConfManager;
import org.rapidoid.config.Config;
import org.rapidoid.jpa.JPA;

import javax.persistence.EntityManagerFactory;


public class DistMeter {

    public static void main(String [] args){

        ConfManager confManager = new ConfManager(); // Configuration object

        //System.out.println("Iniciar Configuration Manager");
        //DistConfManager distmeterConfManager = new DistConfManager(confManager);
        //distmeterConfManager.run(args);

        EntityManagerFactory emf = JPA.getEmf();

        //System.out.println("Iniciar Server");
        DistmeterTestServer distmeterTestServer = new DistmeterTestServer(confManager);
        distmeterTestServer.run(args);

    }
}
