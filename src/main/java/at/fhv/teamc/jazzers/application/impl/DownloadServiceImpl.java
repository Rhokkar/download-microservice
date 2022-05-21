package at.fhv.teamc.jazzers.application.impl;

import at.fhv.jazzers.shared.dto.CustomerAccountDTO;
import at.fhv.teamc.jazzers.ServiceRegistry;
import at.fhv.teamc.jazzers.application.api.DownloadService;
import at.fhv.teamc.jazzers.domain.model.Download;
import at.fhv.teamc.jazzers.infrastructure.HibernateDownloadRepository;

import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DownloadServiceImpl implements DownloadService {
    private final HibernateDownloadRepository downloadRepository = ServiceRegistry.downloadRepository();

    @Override
    @Transactional
    public String download(String username, String password, String product) {
        // 1. Call to Jazzers-Backend with USERNAME and PASSWORD. Check if login is successful
        Client client = ClientBuilder.newClient();

        Response response = client
                .target("http://127.0.0.1:8080/jazzers-backend-1.0-SNAPSHOT/api/v1/login/customer?username=cpe2877&password=password")
                // .target("http://localhost:8080/jazzers-backend-1.0-SNAPSHOT/api/v1/login/customer?username=" + username + "&password=" + password)
                //.target("http://10.0.40.165:8080/jazzers-backend-1.0-SNAPSHOT/api/v1/login/customer?username=" + username + "&password=" + password)
                .request(MediaType.APPLICATION_JSON)
                .get();

        var d = response.readEntity(CustomerAccountDTO.class);



        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY" + d.toString());
        client.close();

        // 2. Call to Collection-Microservice with USERNAME. Check if user owns the product



        return downloadRepository.byProduct(product).map(Download::getLink).orElseThrow();
    }
}