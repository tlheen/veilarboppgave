package no.nav.fo.veilarboppgave.ws.consumer.norg.arbeidsfordeling;

import no.nav.fo.veilarboppgave.domene.GeografiskTilknytning;
import no.nav.fo.veilarboppgave.domene.OppfolgingEnhet;
import no.nav.fo.veilarboppgave.domene.Tema;

import java.util.List;

public interface ArbeidsfordelingService {
    List<OppfolgingEnhet> hentBehandlendeEnheter(GeografiskTilknytning geografiskTilknytning, Tema gyldigTema);
}