
package com.cmd.impl;

import com.cmd.api.CommandService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Used for Dependency Injection (Guice).
 */
public class CommandModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(CommandService.class, CommandServiceImpl.class);
    }
}
