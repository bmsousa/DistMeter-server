package components.disttestserver;

import components.DistMeterComponent;
import conf.ConfManager;
import conf.ConfObjManager;
import entities.Slaves;
import entities.TestsConf;
import org.rapidoid.jpa.JPA;
import org.rapidoid.setup.App;
import org.rapidoid.setup.On;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Valid;

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


        // /server/conf/tests
        On.post("/server/conf/tests").json((@Valid TestsConf test) -> JPA.save(test));
        On.get("/server/conf/tests").json(() -> JPA.of(TestsConf.class).all()); // get all Tests

        // server/slaves/register
        On.post("/server/slaves/register").json((@Valid Slaves sl) -> JPA.save(sl));
        On.put("/server/slaves/register").json((@Valid Slaves sl) -> JPA.update(sl));


        // server/slaves/
        On.get("/server/slaves").json(() -> JPA.of(Slaves.class).all());
        On.get("/server/slaves/").json(() -> JPA.of(Slaves.class).all()); //TODO: check correct url it should be /slaves/{id}


    }

    @Override
    public boolean stop() {
        return false;
    }
}
