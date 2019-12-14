
package com.cmd.impl;

import static java.util.concurrent.TimeUnit.SECONDS;

import static org.junit.Assert.assertEquals;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;

import org.junit.Test;

import com.cmd.api.CommandService;

import domain.model.Job;
import domain.model.JobResult;

/**
 * Unit tests for {@link CommandService}.
 */
public class CommandServiceTest {

    @Test
    public void shouldInvokeCmdInComamndService() throws Exception {
        withServer(defaultSetup(), server -> {
            final CommandService service = server.client(CommandService.class);

            final Job addJob = new Job("add", "1,2");

            final JobResult jobResult = service.cmd().invoke(addJob)
                    .toCompletableFuture().get(5, SECONDS);
            assertEquals("3", jobResult.result);
        });
    }

}
