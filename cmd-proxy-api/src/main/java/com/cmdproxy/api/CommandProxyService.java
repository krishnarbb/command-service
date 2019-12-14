
package com.cmdproxy.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

import com.lightbend.lagom.javadsl.api.CircuitBreaker;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import domain.model.Job;
import domain.model.JobResult;

/**
 * The Command proxy service interface.
 * <p>
 * This is a proxy service which forwards all the calls it receives to {@link CommandService}.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the CommandProxyService.
 * Refer Lagom's {@link Service} and {@link ServiceCall} for more details.
 *
 */
public interface CommandProxyService extends Service {

    /**
     * Takes the request {@link Job}, and return the response as a CompletionStage of {@link JobResult}.
     *
     * Example: This cmd can be invoked as follows :
     * curl -d '{"task":"add", "payload":"1,2"}' -H "Content-Type: application/json"
     * -X POST http://localhost:9000/proxy/rest-cmd/
     *
     * The Result of the above command will be :
     * {"task":"add","payload":"1,2","result":"3"}
     */
    ServiceCall<Job, JobResult> cmdProxyViaHttp();

    /**
     * Describes the REST end-point for for this service.
     * Uses {@link CircuitBreaker} for fault tolerance of invoking dependent services.
     */
    @Override
    default Descriptor descriptor() {
        return named("cmd-proxy")
                .withCalls(
                        restCall(Method.POST, "/proxy/rest-cmd/", this::cmdProxyViaHttp)
                                .withCircuitBreaker(CircuitBreaker.identifiedBy("cmd-circuit-breaker")))
                .withAutoAcl(true);
    }
}
