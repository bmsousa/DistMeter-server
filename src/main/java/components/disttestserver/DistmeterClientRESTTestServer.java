package components.disttestserver;

import conf.ConfManager;
import conf.ConfObject;

/**
 * Class representint the client to connect to the test server REST API
 */
public class DistmeterClientRESTTestServer {
    private ConfManager confManager;
    private ConfObject confObject;

    DistmeterClientRESTTestServer(ConfManager iconf){
        confManager = iconf;
        confObject = confManager.readConf(ConfManager.DISTMETER_COMMON_CONF);

        //TODO: Create a HTTP CLIENT to connect to the REST API
    }
}
