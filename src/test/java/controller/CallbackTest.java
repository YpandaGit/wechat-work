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
import indi.wechat.work.service.ContactService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author youpan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatWorkApplication.class)
@Slf4j
public class CallbackTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	@Autowired
	private CallbackController callbackController;
	@Autowired
	private ContactService contactService;

	@Test
	@PerfTest(invocations = 500, threads = 200)
	@Required(max = 400, average = 150)
	public void testSendTextCard() {
		String corpId = "ww751198e5ade84f15";
		String secret = "s5bXGMaOEQkZUetpSx3B_FODtLJiBDRgORQlTn6e-q8";
		// callbackController.agentSelfCallback(null, null, null, null, null);
		long now =System.currentTimeMillis();
		contactService.getDepartments(corpId, secret).toString();
		log.info("{}",System.currentTimeMillis()- now);
	}
}
