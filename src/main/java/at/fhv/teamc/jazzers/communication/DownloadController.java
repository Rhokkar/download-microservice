package at.fhv.teamc.jazzers.communication;

import at.fhv.teamc.jazzers.ServiceRegistry;
import at.fhv.teamc.jazzers.application.api.DownloadService;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/download")
public class DownloadController {
    private final DownloadService downloadService = ServiceRegistry.downloadService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response download(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password, @QueryParam("product") @DefaultValue("") String product) {
        String link = downloadService.download(username, password, product);
        return Response.status(Response.Status.OK).entity(link).build();
    }
}