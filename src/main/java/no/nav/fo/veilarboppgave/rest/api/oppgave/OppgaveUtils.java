package no.nav.fo.veilarboppgave.rest.api.oppgave;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class OppgaveUtils {
    public static <T> List<T> mergeAndDeleteDuplicate(List<T> list1, List<T> list2) {
        Set<T> set = new LinkedHashSet<>(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }
}
