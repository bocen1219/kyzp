package com.xiaohuo.kyzp.framework.translate.config;

import com.xiaohuo.kyzp.framework.translate.core.TranslateUtils;
import org.dromara.trans.service.impl.TransService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class KyzpTranslateAutoConfiguration {

    @Bean
    @ConditionalOnBean(TransService.class)
    @SuppressWarnings("InstantiationOfUtilityClass")
    public TranslateUtils translateUtils(TransService transService) {
        TranslateUtils.init(transService);
        return new TranslateUtils();
    }

}
