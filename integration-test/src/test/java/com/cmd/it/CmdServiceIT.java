
package com.cmd.it;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cmdproxy.api.CommandProxyService;
import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import domain.model.Job;
import domain.model.JobResult;

/**
 * End to end integration test of all components involved .
 */
public class CmdServiceIT {

    private static final String SERVICE_LOCATOR_URI = "http://localhost:9008";

    private static LagomClientFactory clientFactory;
    private static CommandProxyService cmdProxyService;
    private static ActorSystem system;
    private static Materializer mat;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
        mat = ActorMaterializer.create(system);
        clientFactory = LagomClientFactory.create("integration-test", CmdServiceIT.class.getClassLoader());
        cmdProxyService = clientFactory.createDevClient(CommandProxyService.class, URI.create(SERVICE_LOCATOR_URI));
    }

    @Test
    public void givenValidInputs_addCommand_validOutput() throws Exception {
        // given
        final Job addJob = new Job("add", "1,2");

        // when
        final JobResult jobResult = await(cmdProxyService.cmdProxyViaHttp().invoke(addJob));

        // then
        assertEquals("3", jobResult.result);
    }

    private <T> T await(final CompletionStage<T> future) throws Exception {
        return future.toCompletableFuture().get(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        if (clientFactory != null) {
            clientFactory.close();
        }
        if (system != null) {
            system.terminate();
        }
    }
}
