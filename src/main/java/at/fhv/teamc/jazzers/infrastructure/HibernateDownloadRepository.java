package at.fhv.teamc.jazzers.infrastructure;

import at.fhv.teamc.jazzers.domain.model.Download;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class HibernateDownloadRepository implements PanacheRepository<Download> {
    public Optional<Download> byProduct(String product) {
        return Optional.ofNullable(find("product", product).firstResult());
    }
}