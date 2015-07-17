package com.bond.sky;

import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;

/**
 * Root resource (exposed at "programmes" path)
 */
@Path("bond")
public class MyResource {
    SaxHandler handler;

    /**
     * Method handling HTTP GET requests for the  programme schedule.
     * The returned Schedule will be sent to the client as JSON.
     *
     * @return String that will be returned as a JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Schedule getSchedule() {
        return Schedule.getSchedule();
    }

    /**
     * Method handling HTTP POST requests to upload an XML file.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void acceptFile(@FormDataParam("xmlFile") File file, @Context HttpServletResponse servletResponse) {
        //sanitize file
        //store file on server
        handler = new SaxHandler();
        handler.processFile(file);
        try {
            servletResponse.sendRedirect("../success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
