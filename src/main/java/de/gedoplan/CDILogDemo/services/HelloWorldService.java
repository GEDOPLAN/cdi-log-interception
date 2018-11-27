package de.gedoplan.CDILogDemo.services;

import de.gedoplan.CDILogDemo.system.Debug;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.ApplicationScoped;
import de.gedoplan.CDILogDemo.system.LogContextOnError;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
@LogContextOnError
public class HelloWorldService implements Serializable{

    @Debug(message="Abruf formatiertes Datum")
    public String getFormattedDate(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    @Debug(message="Produziere einen Fehler")
    public String getSomeError(String msg) {
        return String.format("%s %d", msg, "World");
    }
}
