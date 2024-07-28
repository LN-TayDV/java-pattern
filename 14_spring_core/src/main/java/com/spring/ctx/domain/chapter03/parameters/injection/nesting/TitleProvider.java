package com.spring.ctx.domain.chapter03.parameters.injection.nesting;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TitleProvider {

    @Getter
    @Setter
    private String title = "Gravity";

    // builder method
    public static TitleProvider instance(final String title){
        var childProvider = new TitleProvider();

        if(StringUtils.isNotBlank(title)) {
            childProvider.setTitle(title);
        }

        return childProvider;
    }

}
