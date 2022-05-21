package at.fhv.teamc.jazzers.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Download extends PanacheEntity {
    @Id
    private Long downloadIdInternal;

    private String product;

    private String link;

    protected Download() {

    }

    public Download(String product, String link) {
        this.product = product;
        this.link = link;
    }

    public String getProduct() {
        return product;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Download download = (Download) o;
        return product.equals(download.product) && link.equals(download.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, link);
    }
}
