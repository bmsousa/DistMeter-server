package components.disttestserver;

import components.DistMeterComponent;
import conf.ConfManager;
import conf.ConfObjManager;
import entities.Book;
import entities.TestsConf;
import org.rapidoid.jpa.JPA;
import org.rapidoid.setup.App;
import org.rapidoid.u.U;
import org.rapidoid.setup.On;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DistmeterTestServer implements DistMeterComponent {
    private ConfManager confManager;
    private ConfObjManager confObjManager;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DistmeterServer");
    private static EntityManager em = emf.createEntityManager();

    public DistmeterTestServer(ConfManager iconfManager) {
        confManager = iconfManager;
        confObjManager = (ConfObjManager)confManager.readConf(ConfManager.DISTMETER_TEST_SERVER);
        //
    }

    @Override
    public boolean init(String[] args) {
        return false;
    }

    @Override
    public void run(String[] args) {


        App.bootstrap(args, "profiles=mysql").jpa();
        JPA.setEmf(emf);

        On.get("/testserver/register").json(() -> U.map("msg", "registo bem feito"));

        On.get("/testsconf").json(() -> JPA.of(Book.class).all()); // get all Tests

    }

    @Override
    public boolean stop() {
        return false;
    }
}
