package com.xiaohuo.kyzp.framework.signature.config;

import com.xiaohuo.kyzp.framework.redis.config.KyzpRedisAutoConfiguration;
import com.xiaohuo.kyzp.framework.signature.core.aop.ApiSignatureAspect;
import com.xiaohuo.kyzp.framework.signature.core.redis.ApiSignatureRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * HTTP API 签名的自动配置类
 *
 * @author Zhougang
 */
@AutoConfiguration(after = KyzpRedisAutoConfiguration.class)
public class KyzpApiSignatureAutoConfiguration {

    @Bean
    public ApiSignatureAspect signatureAspect(ApiSignatureRedisDAO signatureRedisDAO) {
        return new ApiSignatureAspect(signatureRedisDAO);
    }

    @Bean
    public ApiSignatureRedisDAO signatureRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new ApiSignatureRedisDAO(stringRedisTemplate);
    }

}
