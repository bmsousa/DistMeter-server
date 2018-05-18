package components.disttestserver;

import conf.ConfManager;
import conf.ConfObject;
import org.rapidoid.http.HttpClient;

/**
 * Class representint the client to connect to the test server REST API
 */
public class DistmeterClientRESTTestServer {
    private ConfManager confManager;
    private ConfObject confObject;
    private HttpClient httpClient;

    public DistmeterClientRESTTestServer(ConfManager iconf){
        this.confManager = iconf;
        this.confObject = confManager.readConf(ConfManager.DISTMETER_COMMON_CONF);

        //Create a HTTP CLIENT to connect to the REST API
        this.httpClient = new HttpClient();
    }

    /**
     * method to send a request to the server
     * @param args
     */
    public String sendRequestToServer(String url, String [] args){
        String aux="";

        aux = this.httpClient.get(confObject.getAddress_test_servers()+":"+confObject.getPort_REST_test_servers()+url).fetch();

        return aux;
    }
}
