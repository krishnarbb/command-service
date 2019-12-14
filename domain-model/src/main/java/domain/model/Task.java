
package domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tasks supported in this domain.
 */
public enum Task {
    ADD("add", Functions.executeAddition),
    MULTIPLY("multiply", Functions.executeMultiplication);

    public final String name;
    public final TaskExecutor taskExecutor;

    private Task(final String val, final TaskExecutor executor) {
        name = val;
        taskExecutor = executor;
    }

    // For getting enum type from String, build an immutable map of String name to enum pairs.
    private static final Map<String, Task> ENUM_MAP;
    static {
        final Map<String, Task> map = new ConcurrentHashMap<String, Task>();
        for (final Task instance : Task.values()) {
            map.put(instance.name, instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Task get(final String name) {
        return ENUM_MAP.get(name);
    }
}
