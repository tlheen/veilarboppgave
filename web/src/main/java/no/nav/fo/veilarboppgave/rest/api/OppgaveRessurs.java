package no.nav.fo.veilarboppgave.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/oppgave/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class OppgaveRessurs {

    @POST
    public OppgaveId opprettOppgave(OppgaveDTO oppgave) {
        //TODO: Sjekk tilgang!!
        return OppgaveId.of(1);
    }
}