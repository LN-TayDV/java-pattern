package com.spring.ctx.domain.chapter03.methods.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Lookup Method Injection is intended for use when you want to work with two beans
 * of different life cycles.
 *
 * Avoid the temptation to use Lookup Method Injection when the beans share the same life cycle,
 * especially if they are singletons.
 */

@Component("standardLockOpener")
public class StandardLockOpener implements LockOpener{

    private KeyHelper keyHelper;

    @Autowired
    @Qualifier("keyHelper")
    public void setKeyHelper(KeyHelper keyHelper) {
        this.keyHelper = keyHelper;
    }

    @Override
    public KeyHelper getMyKeyOpener() {
        return keyHelper;
    }

    @Override
    public void openLock() {
        keyHelper.open();
    }
}
