/**
 * 
 */
package indi.wechat.work.controller.callback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(
            description = "自建应用回调")
    @GetMapping(
            value = "/agent/self/{corpId}")
    public Object agentSelfCallback(@PathVariable("corpId") String corpId, @RequestParam(
            value = "msg_signature") String msgSignature,
            @RequestParam(
                    value = "timestamp") String timestamp,
            @RequestParam(
                    value = "nonce") String nonce,
            @RequestParam(
                    value = "echostr",
                    required = false) String echostr) {
        return null;
    }

}
