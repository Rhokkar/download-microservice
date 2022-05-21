package at.fhv.teamc.jazzers;

import at.fhv.teamc.jazzers.domain.model.Download;
import at.fhv.teamc.jazzers.infrastructure.HibernateDownloadRepository;
import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Startup
@ApplicationScoped
public class DataGenerator {
    private final HibernateDownloadRepository downloadRepository = ServiceRegistry.downloadRepository();
    private final List<Download> downloads = new ArrayList<>();

    @PostConstruct
    public void init() {
        generateData();
        persistData();
    }

    private void generateData() {
        Download d1  = new Download("So What",            "x");
        Download d2  = new Download("Freddie Freeloader", "x");
        Download d3  = new Download("Blue in Green",      "x");
        Download d4  = new Download("All Blues",          "x");
        Download d5  = new Download("Flamenco Sketches",  "x");

        Download d6  = new Download("Don't Stay",         "x");
        Download d7  = new Download("Somewhere I belong", "x");
        Download d8  = new Download("Lying from You",     "x");
        Download d9  = new Download("Hit the Floor",      "x");
        Download d10 = new Download("Easier to Run",      "x");

        Download d11 = new Download("The Awakening",      "x");
        Download d12 = new Download("Damnation",          "x");
        Download d13 = new Download("Messiah",            "x");
        Download d14 = new Download("The Fall of Man",    "x");
        Download d15 = new Download("Monster",            "x");

        downloads.addAll(List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15));
    }

    @Transactional
    public void persistData() {
        downloadRepository.persist(downloads);
        // Download.persist(downloads);
    }
}