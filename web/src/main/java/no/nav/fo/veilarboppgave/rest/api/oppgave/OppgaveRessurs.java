package no.nav.fo.veilarboppgave.rest.api.oppgave;

import no.nav.fo.veilarboppgave.domene.OppgaveId;
import no.nav.fo.veilarboppgave.rest.api.Valider;
import no.nav.fo.veilarboppgave.security.abac.PepClient;
import no.nav.fo.veilarboppgave.ws.consumer.gsak.OppgaveService;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static java.util.Optional.ofNullable;

@Path("/oppgave")
public class OppgaveRessurs {

    private final OppgaveService oppgaveService;
    private final PepClient pepClient;

    @Inject
    public OppgaveRessurs(OppgaveService oppgaveService, PepClient pepClient) {
        this.oppgaveService = oppgaveService;
        this.pepClient = pepClient;
    }

    @POST
    public OppgaveId opprettOppgave(OppgaveDTO dto) {

        ofNullable(dto.getFnr())
                .map(Valider::fnr)
                .map(pepClient::sjekkTilgangTilFnr)
                .orElseThrow(RuntimeException::new);

        Valider.fraTilDato(dto);
        Valider.obligatoriskeFelter(dto);

        return oppgaveService
                .opprettOppgave(dto)
                .orElseThrow(NotFoundException::new);
    }
}
