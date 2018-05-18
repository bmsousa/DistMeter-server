package components.distconfmanager;

import components.DistMeterComponent;
import components.disttestserver.DistmeterClientRESTTestServer;
import conf.ConfManager;
import conf.ConfObjManager;
import org.rapidoid.setup.App;
import org.rapidoid.setup.On;
import org.rapidoid.setup.Setup;

/**
 * Class to represent the DistMeter configuration manager component
 *
 * Includes a HTTP Server and a REST client
 */
public class DistConfManager implements DistMeterComponent{
    private ConfManager confManager;
    private ConfObjManager confObjManager;
    private DistmeterClientRESTTestServer clientRESTTestServer;


    public DistConfManager(ConfManager iconfManager){
        confManager = iconfManager;
        confObjManager = (ConfObjManager)confManager.readConf(ConfManager.DISTMETER_CONFIGURATION_MANAGER);
        //
    }

    @Override
    public boolean init(String [] args) {
        return true;
    }

    @Override
    public void run(String [] args) {
        // Server process
        App.run(args);

        clientRESTTestServer = new DistmeterClientRESTTestServer(this.confManager);

        Setup setup1 = Setup.create("DistmeterConfManager");
        setup1.scan(DistConfManagerHTTP.class.getPackage().getName());






    }

    @Override
    public boolean stop() {
        return false;
    }


}
