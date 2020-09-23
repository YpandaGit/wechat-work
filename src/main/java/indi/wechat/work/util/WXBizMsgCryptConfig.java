/**
 * Project Name:qywechat-action
 * File Name:WXBizMsgCryptConfig.java
 * Package Name:org.qywechat.action.config
 * Date:2020年3月20日下午4:15:54
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:WXBizMsgCryptConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年3月20日 下午4:15:54 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
@Slf4j
public class WXBizMsgCryptConfig {
    
    /**
     * 单例动态构建解密器
     * 
     * @author youpan
     * @param appToken
     * @param encodingAesKey
     * @param corpId
     * @return
     */
    public static WXBizMsgCrypt build(String appToken, String encodingAesKey, String corpId) {
        try {
            return WechatWorkUtils.createWxBizMsgCrypt(appToken, encodingAesKey, corpId);
        } catch (AesException e) {
            log.error("create Corp Wx BizMsg Crypt error!check param appToken:{},appEncodingAesKey:{},appCorpId:{}", appToken, encodingAesKey, corpId);
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
