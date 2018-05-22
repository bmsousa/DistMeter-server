package components.disttestserver;

import components.DistMeterComponent;
import conf.ConfManager;
import conf.ConfObjManager;
import org.rapidoid.u.U;
import org.rapidoid.setup.On;

public class DistmeterTestServer implements DistMeterComponent {
    private ConfManager confManager;
    private ConfObjManager confObjManager;

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
        On.get("/testserver/register").json(() -> U.map("msg", "registo bem feito"));

    }

    @Override
    public boolean stop() {
        return false;
    }
}
