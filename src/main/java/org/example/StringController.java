package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


// for demo 400:
// application/javascript


/**
 * http://localhost:9090/api/myresource
 */
@Path("strings")
public class StringController {

    private StringService stringService;

    public StringController() {
        this.stringService = StringService.getInstance();
        this.stringService.populate();
    }

    @GET
    @Path("/json")
    public Response getJson() {
        return Response.ok(this.stringService.getAll())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/xml")
    public Response getXML() {

        StringItems list = new StringItems(this.stringService.getAll());

        return Response.ok(list)
                .type(MediaType.TEXT_XML)
                .build();
    }

    @GET
    @Path("/text")
    public Response getText() {

        StringBuilder b = new StringBuilder();

        for (StringItem item : this.stringService.getAll()) {
            b.append(item.getValue());
            b.append(',');
        }

        return Response.ok(b.toString())
                .type(MediaType.TEXT_PLAIN)
                .build();

    }


    @POST
    public Response create(@QueryParam("v") String v) {

        if (v == null || v.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).build();

        if (this.stringService.add(v)) return Response.ok("String added!").build();

        return Response.status(Response.Status.CONFLICT).entity("String already exists").build();

    }

    @DELETE
    public Response remove(@QueryParam("v") String v) {

        if (v == null || v.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).build();


        if (this.stringService.remove(v)) return Response.ok("String deleted").build();


        return Response.status(Response.Status.NOT_FOUND).entity("String not found").build();

    }


    @GET
    @Produces("*/*")
    @Consumes("*/*")
    public Response getAll(@HeaderParam("Accept") String acceptHeader) {


        if (acceptHeader == null || acceptHeader.isEmpty()) return this.getText();

        switch (acceptHeader) {
            case "application/json":
                return this.getJson();
            case "text/xml":
                return this.getXML();

            case "text/plain":
                return this.getText();

            default:

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Please use appropriate content type header")
                        .build();

        }

    }


}
