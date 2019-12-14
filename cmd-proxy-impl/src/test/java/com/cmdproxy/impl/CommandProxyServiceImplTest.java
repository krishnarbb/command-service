
package com.cmdproxy.impl;

import static java.util.concurrent.TimeUnit.SECONDS;

import static org.junit.Assert.assertEquals;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.bind;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.startServer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cmd.api.CommandService;
import com.cmdproxy.api.CommandProxyService;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.testkit.ServiceTest;

import domain.model.Job;
import domain.model.JobResult;

/**
 * Unit tests for {@link CommandProxyService}.
 * Uses {@link StubCommandService} a mock implementation of {@link CommandService}.
 */
public class CommandProxyServiceImplTest {

    private static ServiceTest.TestServer server;
    private static CommandProxyService proxyServiceClient;

    @BeforeClass
    public static void setUp() {
        final ServiceTest.Setup setup = defaultSetup().withCluster(false).withSsl(false)
                .withCassandra(false)
                .configureBuilder(builder -> builder.overrides(bind(CommandService.class).to(StubCommandService.class)));

        server = startServer(setup);
        proxyServiceClient = server.client(CommandProxyService.class);
    }

    @AfterClass
    public static void tearDown() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    @Test
    public void cmdProxyShouldRoundtripHttpRequests()
            throws InterruptedException, ExecutionException, TimeoutException {
        final Job addJob = new Job("add", "1,2");
        final JobResult jobResult = proxyServiceClient.cmdProxyViaHttp().invoke(addJob)
                .toCompletableFuture().get(5, SECONDS);
        assertEquals("3", jobResult.result);
    }

    // ---------------------------------------------------------------------------------
    public static class StubCommandService implements CommandService {
        @Override
        public ServiceCall<Job, JobResult> cmd() {
            return job -> {
                final JobResult jobResult = new JobResult(job.task, job.payload, "3");
                return CompletableFuture.completedFuture(jobResult);
            };
        }
    }
}
