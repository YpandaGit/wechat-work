/**
 * 
 */
package indi.wechat.work.controller.callback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import indi.wechat.work.util.WXBizMsgCrypt;
import indi.wechat.work.util.WXBizMsgCryptConfig;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信回调处理接口
 * 
 * @author youpan
 *
 */
@RestController("callback")
@Slf4j
public class CallbackController {

    @Operation(description = "自建应用回调")
    @GetMapping(value = "/agent/self/{corpId}/{agentId}")
    public Object agentSelfCallback(@PathVariable("corpId") String corpId, @PathVariable("corpId") String agentId,
            @RequestParam(value = "msg_signature") String msgSignature, @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce, @RequestParam(value = "echostr", required = false) String echostr) {
        // TODO 获取对应的应用信息
    	log.info("签名验证");
        WXBizMsgCrypt extraWxBizMsgCrypt = WXBizMsgCryptConfig.build(agentId, "aesKey", corpId);
        return null;
    }
    @Operation(description = "自建应用回调")
    @PostMapping(value = "/agent/self/{corpId}/{agentId}")
    public Object postAgentSelfCallback(@PathVariable("corpId") String corpId, @PathVariable("corpId") String agentId,
            @RequestParam(value = "msg_signature") String msgSignature, @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce, @RequestParam(value = "echostr", required = false) String echostr) {
        // TODO 获取对应的应用信息

        WXBizMsgCrypt extraWxBizMsgCrypt = WXBizMsgCryptConfig.build(agentId, "aesKey", corpId);
        return null;
    }

    
    
}
