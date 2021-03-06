package no.nav.fo.veilarboppgave.rest.api.oppgave;

import no.nav.dialogarena.aktor.AktorService;
import no.nav.fo.veilarboppgave.db.OppgaveRepository;
import no.nav.fo.veilarboppgave.db.OppgavehistorikkDTO;
import no.nav.fo.veilarboppgave.domene.Aktoerid;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/oppgavehistorikk")
public class OppgavehistorikkRessurs {

    private AktorService aktorService;
    private OppgaveRepository oppgaveRepository;

    public OppgavehistorikkRessurs(AktorService aktorService, OppgaveRepository oppgaveRepository) {
        this.aktorService = aktorService;
        this.oppgaveRepository = oppgaveRepository;
    }

    @GET
    public List<Oppgavehistorikk> getOppgavehistorikk(@QueryParam("fnr") String fnr) {
        Aktoerid aktoerid = Aktoerid.of(aktorService.getAktorId(fnr).orElseThrow(() -> new NotFoundException()));
        List<OppgavehistorikkDTO> oppgavehistorikk = oppgaveRepository.hentOppgavehistorikkForBruker(aktoerid);
        return oppgavehistorikk.stream().map(Oppgavehistorikk::of).collect(toList());
    }
}
