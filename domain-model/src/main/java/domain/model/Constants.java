
package domain.model;

/**
 * Used for specifying Error messages and other constants.
 */
public class Constants {

    /**
     * Separator used in the payload value of {@link Job}.
     */
    public static final String PAYLOAD_SEPARATOR = ",";

    /*
     * Error messages.
     */
    public static final String ERROR_PAYLOAD_MORE_THAN_2_NUMBERS =
            "Error : payload parameters should contain 2 numbers. for eg: '1,2'";

    public static final String ERROR_PAYLOAD_NOT_COMMA_SEPARATED =
            "Error : payload parameters should be comma separated. for eg: '1,2'";

    public static final String ERROR_EMPTY_PAYLOAD = "Error : payload parameter cannot be empty";

    public static final String ERROR_UNSUPPORTED_TASK = "Error : unsupported task. Valid tasks are add and multiply";

    public static final String ERROR_EMPTY_TASK = "Error : task parameter cannot be empty";
}
