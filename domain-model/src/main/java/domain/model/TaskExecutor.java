
package domain.model;

/**
 * Executes a task in a {@link Job}.
 */
public interface TaskExecutor {

    /**
     * Execute a task in a job
     *
     * @param job
     * @return
     *         result of job execution
     */
    JobResult execute(Job job);

}
