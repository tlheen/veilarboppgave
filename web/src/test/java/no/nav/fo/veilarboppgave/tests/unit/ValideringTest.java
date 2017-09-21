package no.nav.fo.veilarboppgave.tests.unit;

import no.nav.apiapp.feil.UgyldigRequest;
import no.nav.fo.veilarboppgave.TestData;
import no.nav.fo.veilarboppgave.domene.Fnr;
import no.nav.fo.veilarboppgave.domene.Tema;
import no.nav.fo.veilarboppgave.rest.api.Valider;
import no.nav.fo.veilarboppgave.rest.api.oppgave.OppgaveDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static no.nav.fo.veilarboppgave.Util.lagStringAvLengde;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValideringTest {

    @ParameterizedTest
    @EnumSource(Tema.class)
    void skal_validere_alle_gyldige_input_for_tema(Tema tema) throws Exception {
        assertNotNull(Valider.tema(tema.name()));
    }

    @Test
    void skal_ikke_validere_ugyldig_tema() {
        assertThrows(UgyldigRequest.class, () -> Valider.tema("ugyldig_tema"));
    }

    @ParameterizedTest
    @MethodSource("tilfeldigFnrStream")
    void skal_validere_gyldige_fnr(String fnr) throws Exception {
        Assert.assertNotNull(Valider.fnr(fnr));
    }

    @Test
    void skal_kaste_exception_om_fra_dato_er_etter_til_dato() throws Exception {
        String fraDato = "2000-09-18";
        String tilDato = "1900-09-18";
        OppgaveDTO testData = TestData.oppgaveDTO(fraDato, tilDato);
        assertThrows(UgyldigRequest.class, () -> Valider.fraDatoErFoerTilDato(testData));
    }

    @Test
    void skal_ikke_godta_beskrivelse_over_500_tegn() {
        String akkuratForLangBeskrivelse = lagStringAvLengde(501);
        String akkuratPasseLangBeskrivelse = lagStringAvLengde(500);
        assertThrows(UgyldigRequest.class, () -> Valider.beskrivelse(akkuratForLangBeskrivelse));
        assertNotNull(Valider.beskrivelse(akkuratPasseLangBeskrivelse));
    }


    private static Stream<String> tilfeldigFnrStream() {
        return Stream.generate(TestData::genererTilfeldigFnr)
                .map(Fnr::getFnr)
                .limit(100);
    }
}