
package com.cmdproxy.impl;

import com.cmd.api.CommandService;
import com.cmdproxy.api.CommandProxyService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Used for dependency injection (Guice).
 */
public class CommandProxyModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindClient(CommandService.class);
        bindService(CommandProxyService.class, CommandProxyServiceImpl.class);
    }

}
