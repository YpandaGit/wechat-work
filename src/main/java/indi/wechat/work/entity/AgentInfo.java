/**
 * 
 */
package indi.wechat.work.entity;

import javax.persistence.Entity;

import lombok.Data;

/**
 * @author youpan
 *
 */
@Data
@Entity
public class AgentInfo {
    private Long id;
    private String corpId;
    private Integer agentId;
    private String secret;
    private String encodingAesKey;
    private String token;
}
