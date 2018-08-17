import components.disttestserver.DistmeterTestServer;
import conf.ConfManager;


public class DistMeter {

    public static void main(String [] args){

        ConfManager confManager = new ConfManager(); // Configuration object

       // EntityManagerFactory emf = JPA.getEmf();

        //System.out.println("Iniciar Server");
        DistmeterTestServer distmeterTestServer = new DistmeterTestServer(confManager);
        distmeterTestServer.run(args);

    }
}
