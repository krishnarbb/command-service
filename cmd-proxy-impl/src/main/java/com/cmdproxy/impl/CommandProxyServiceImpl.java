
package com.cmdproxy.impl;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmd.api.CommandService;
import com.cmdproxy.api.CommandProxyService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import domain.model.Job;
import domain.model.JobResult;

/**
 * A proxy service which forwards all calls it receives to {@link CommandService}.
 */
@Singleton
public class CommandProxyServiceImpl implements CommandProxyService {

    private final CommandService commandService;
    private final Logger log = LoggerFactory.getLogger(CommandProxyServiceImpl.class);

    @Inject
    public CommandProxyServiceImpl(final CommandService helloService) {
        commandService = helloService;
    }

    @Override
    public ServiceCall<Job, JobResult> cmdProxyViaHttp() {
        return job -> {
            final CompletionStage<JobResult> jobResult = commandService.cmd().invoke(job);
            jobResult.thenAccept(result -> log.info("{} : {}", job, result));
            return jobResult;
        };
    }

}
