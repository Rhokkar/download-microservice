package at.fhv.teamc.jazzers.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Download extends PanacheEntity {
    public String productName;
    public String link;

    public Download() {
    }

    public static Optional<Download> findByProductName(String productName) {
        return find("lower(productName) like lower(?1)", productName).firstResultOptional();
    }
}
