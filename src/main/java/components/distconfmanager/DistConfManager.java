package components.distconfmanager;

import components.DistMeterComponent;
import conf.ConfManager;
import conf.ConfObjManager;
import org.rapidoid.http.HTTP;
import org.rapidoid.setup.App;
import org.rapidoid.setup.Setup;

/**
 * Class to represent the DistMeter configuration manager component
 *
 * Includes a HTTP Server and a REST client
 */
public class DistConfManager implements DistMeterComponent{
    private ConfManager confManager;
    private ConfObjManager confObjManager;


    public DistConfManager(ConfManager iconfManager){
        confManager = iconfManager;
        confObjManager = (ConfObjManager)confManager.readConf(ConfManager.DISTMETER_CONFIGURATION_MANAGER);
    }

    @Override
    public boolean init(String [] args) {
        return true;
    }

    @Override
    public void run(String [] args) {
        // Server process
        App.run(args);
        Setup setup1 = Setup.create("DistmeterConfManager");
        setup1.scan(DistConfManagerHTTP.class.getPackage().getName());

        //Client side


    }

    @Override
    public boolean stop() {
        return false;
    }


}
