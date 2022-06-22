package at.fhv.teamc.jazzers.communication;

import at.fhv.teamc.jazzers.domain.Download;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("downloads")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class DownloadResource {
    private static final String SERVICE_URI = "http://10.0.40.165:8080/jazzers-backend-1.0-SNAPSHOT/api/v1";

    @GET
    public Response byProductName(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password, @QueryParam("productName") @DefaultValue("") String productName) {
        if (isNotAuthorized(username, password) || isNotOwner(username, password, productName)) {

            return Response.status(Response.Status.UNAUTHORIZED).build();

        }

        Optional<Download> download = Download.findByProductName(productName);

        if (download.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(download.get().link).build();
    }

    private boolean isNotAuthorized(String username, String password) {
        Client client = ClientBuilder.newClient();

        Response.Status loginStatus = client
                .target(SERVICE_URI + "/login/customer?username=" + username + "&password=" + password)
                .request(MediaType.APPLICATION_JSON).get()
                .getStatusInfo().toEnum();

        client.close();


        if(!loginStatus.equals(Response.Status.OK)) {

            System.out.println("Not authorized!");
        } else {
            System.out.println("AUTHORIZED!");
        }

        return !loginStatus.equals(Response.Status.OK);
    }

    private boolean isNotOwner(String username,  String password, String productName) {
        Client client = ClientBuilder.newClient();

        Response.Status ownerStatus = client
                .target(SERVICE_URI + "/playlist/collection/ownership?username=" + username + "&password=" + password + "&productName=" + productName)
                .request(MediaType.APPLICATION_JSON).get()
                .getStatusInfo().toEnum();

        client.close();



        if(!ownerStatus.equals(Response.Status.OK)) {
            System.out.println("Not owner!!!");
            System.out.println(ownerStatus);
        }
        return !ownerStatus.equals(Response.Status.OK);
    }
}
