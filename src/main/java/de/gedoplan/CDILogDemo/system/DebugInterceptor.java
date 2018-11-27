package de.gedoplan.CDILogDemo.system;

import java.io.Serializable;
import java.util.logging.Level;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Interceptor
@Debug
public class DebugInterceptor extends LogHelper implements Serializable {

    @AroundInvoke
    public Object debug(InvocationContext ctx) throws Exception {
        String debugMessage = ctx.getMethod().getAnnotation(Debug.class).message();
        super.logRuntimeInformation(ctx, Level.FINE, debugMessage);
        return ctx.proceed();
    }

}
