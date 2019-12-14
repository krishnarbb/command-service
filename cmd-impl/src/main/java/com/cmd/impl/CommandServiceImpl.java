
package com.cmd.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmd.api.CommandService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import domain.model.Job;
import domain.model.JobResult;
import domain.model.Task;
import domain.model.Validator;

/**
 * An implementation of {@link CommandService}.
 */
public class CommandServiceImpl implements CommandService {

    private final Logger log = LoggerFactory.getLogger(CommandServiceImpl.class);

    @Inject
    public CommandServiceImpl() {}

    @Override
    public ServiceCall<Job, JobResult> cmd() {

        return job -> {
            log.debug("Received Job : {}", job);

            // validate the job input for errors.
            final Optional<String> error = Validator.defaultValidator(job);
            if (error.isPresent()) {
                log.error("{} : {}", job, error.get());
                final JobResult jobResult = new JobResult(job.task, job.payload, error.get());
                return CompletableFuture.completedFuture(jobResult);
            }

            // execute the job
            final JobResult jobResult = Task.get(job.task.toLowerCase()).taskExecutor.execute(job);
            log.info("{} : {}", job, jobResult);
            return CompletableFuture.completedFuture(jobResult);
        };
    }
}
