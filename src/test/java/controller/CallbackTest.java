/**
 * 
 */
package controller;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import indi.wechat.work.WechatWorkApplication;
import indi.wechat.work.controller.callback.CallbackController;

/**
 * @author youpan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = WechatWorkApplication.class)
public class CallbackTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Autowired
    private CallbackController callbackController;

    @Test
    @PerfTest(
            invocations = 20000,
            threads = 10)
    @Required(
            max = 1200,
            average = 250)
    public void testSendTextCard() {
        callbackController.agentSelfCallback(null, null, null, null, null);
    }
}
