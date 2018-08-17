package components.disttestserver;

import components.DistMeterComponent;
import conf.ConfManager;
import conf.ConfObjManager;
import entities.Slaves;
import entities.TestsConf;
import org.rapidoid.annotation.Page;
import org.rapidoid.gui.GUI;
import org.rapidoid.gui.Grid;
import org.rapidoid.gui.HtmlPage;
import org.rapidoid.http.Req;
import org.rapidoid.jpa.JPA;
import org.rapidoid.setup.App;
import org.rapidoid.setup.On;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Valid;

import org.rapidoid.log.Log;
import org.rapidoid.u.U;
import org.rapidoid.web.Screen;

import java.util.List;

import static org.rapidoid.gui.GUI.grid;
import static org.rapidoid.html.HTML.h2;

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

        App.bootstrap(args, "profiles=mysql,prod", "on.address=0.0.0.0", "on.port=8886", "setup.port=8886", "setup.address=0.0.0.0", "setup.server=0.0.0.0", "rapidoid.port=8886", "rapidoid.address=0.0.0.0" ).jpa();
        JPA.setEmf(emf);

        On.get("/").mvc((Req req) -> {
            List<TestsConf> testsConfs = JPA.of(TestsConf.class).all();
            List<Slaves> slaves = JPA.of(Slaves.class).all();
            Grid g1 = GUI.grid(testsConfs);
            Grid g2 = GUI.grid(slaves);

            HtmlPage p = GUI.page("Tests",g1, "Slaves", g2);
            p.navbar(true);
            return p;
        });


        // To handle data from form to add tests.
        On.get("/server/conf/addtest").json((Req req) -> {
            req.async();
            Log.debug("N. Params received " + req.params().size());
            for (String pa : req.params().values()){
                Log.debug("params" + pa);
            }
            TestsConf testa;
            testa = new TestsConf( req.param("Name"),  Integer.parseInt(req.param("n_threads")),
                    Integer.parseInt(req.param("ramp_up")), Integer.parseInt(req.param("runs")),
                    Integer.parseInt(req.param("n_clients")),   Integer.parseInt(req.param("duration")),
                    req.param("protocol"),  req.param("server_name"),   Integer.parseInt(req.param("server_port")),
                    req.param("test_file"),  req.param("date_conf"));
            JPA.save(testa);
            req.response().result("Teste inserido com sucesso").done();
            //TODO: Add a timer to perform the redirect...
            //req.response().redirect("/");
            return req;
        });


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
