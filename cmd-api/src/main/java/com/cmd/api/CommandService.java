
package com.cmd.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import domain.model.Job;
import domain.model.JobResult;

/**
 * The Command service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the CommandService.
 * <p>
 *  Refer Lagom's {@link Service} and {@link ServiceCall} for more details.
 */
public interface CommandService extends Service {

    /**
     * It will take the request {@link Job}, and return the response as a CompletionStage of {@link JobResult}.
     *
     *
     * Example: This cmd can be invoked as follows :
     * curl -d '{"task":"add", "payload":"1,2"}' -H "Content-Type: application/json" -X POST
     * http://localhost:9000/api/cmd/
     *
     * The Result of the above command will be :
     * {"task":"add","payload":"1,2","result":"3"}
     */
    public ServiceCall<Job, JobResult> cmd();

    /**
     * REST end-point descriptor for this service.
     */
    @Override
    default Descriptor descriptor() {
        return named("cmd")
                .withCalls(
                        pathCall("/api/cmd/", this::cmd))
                .withAutoAcl(true);
    }
}
