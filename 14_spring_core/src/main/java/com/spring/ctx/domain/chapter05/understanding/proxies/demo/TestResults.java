package com.spring.ctx.domain.chapter05.understanding.proxies.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@Setter
@NoArgsConstructor
public class TestResults {

    long advisedMethodTime;
    long unadvisedMethodTime;
    long equalsTime;
    long hashCodeTime;
    long proxyTargetTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("advised", advisedMethodTime)
            .append("unadvised", unadvisedMethodTime)
            .append("equals ", equalsTime)
            .append("hashCode", hashCodeTime)
            .append("getProxyTargetClass ", proxyTargetTime)
            .toString();
    }
}
