package de.gedoplan.CDILogDemo.system;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
public abstract class LogHelper {

    @Inject
    Logger logger;

    @Inject
    HttpServletRequest httpRequest;

    protected void logRuntimeInformation(InvocationContext ctx, Level level) {
        this.logRuntimeInformation(ctx, level, "");
    }

    protected void logRuntimeInformation(InvocationContext ctx, Level level, String message) {
        if (logger.getLevel().equals(level)){
            String targetClass = ctx.getTarget().getClass().getSuperclass().getName();
            String targetMethod = ctx.getMethod().getName();
            String parameters = Stream.of(ctx.getParameters()).map(Object::toString).collect(Collectors.joining(", "));

            String remoteAddr;
            String headers;
            if (httpRequest != null) {
                remoteAddr = httpRequest.getRemoteAddr();
                headers = Collections.list(httpRequest.getHeaderNames()).stream().map((key) -> key + ":" + httpRequest.getHeader(key)).collect(Collectors.joining(", "));
            } else {
                remoteAddr = "unknown";
                headers = "undefined";
            }

            this.logger.log(level, String.format("Call of: %s#%s (%s) with parameters: [%s] from client %s (Headers: [%s])", targetClass, targetMethod, message, parameters, remoteAddr, headers));
        }
    }
}
