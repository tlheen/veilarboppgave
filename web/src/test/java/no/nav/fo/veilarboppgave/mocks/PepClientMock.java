package no.nav.fo.veilarboppgave.mocks;

import no.nav.apiapp.feil.IngenTilgang;
import no.nav.fo.veilarboppgave.security.abac.PepClient;

import static no.nav.fo.veilarboppgave.TestData.IKKE_AUTORISERT_FNR;

public class PepClientMock implements PepClient{
    @Override
    public String sjekkTilgangTilFnr(String fnr) {
        if (fnr.equals(IKKE_AUTORISERT_FNR)) {
            return fnr;
        }
        throw new IngenTilgang();
    }
}
