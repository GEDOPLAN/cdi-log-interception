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
@LogContextOnError
public class LogContextOnErrorInterceptor extends LogHelper implements Serializable {

    @AroundInvoke
    public Object debug(InvocationContext ctx) throws Exception {

        Object proceed;
        try {
            proceed = ctx.proceed();
        } catch (Exception ex) {
            super.logRuntimeInformation(ctx, Level.SEVERE);
            throw ex;
        }
        return proceed;
    }

}
