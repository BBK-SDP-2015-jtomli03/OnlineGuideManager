package com.bond.sky;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * Root resource (exposed at "programmes" path)
 */
@Path("bond")
public class MyResource {

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
     *
     * @return String that will be returned as plain text.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN) //MediaType.TEXT_PLAINMediaType.APPLICATION_JSON
    public String acceptFile(@FormDataParam("xmlFile") File file) {
        //sanitize file
        //store file on server
        int start;
        int fin;
        //read the file and save the xml to string
        String doc = readFile(file);
        //format XML so that it can be parsed by SaxParser
        start = 15 + doc.indexOf("<movie id");
        fin = 13 + doc.lastIndexOf("</movie_data>"); //add 13 to include </movie_data>
        doc = doc.substring(start, fin);
        //write the formatted xml string to file
        writeToFile(file, doc);
        //parse the file
        parseFile(file);
        return "File Upload Successful!"; //Response.status(Response.Status.OK).build();
    }

    /**
     * Reads a File and returns a String of the content
     */
    private String readFile(File file){
        BufferedReader in = null;
        String doc = "";
        try {
            in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                doc += line;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File" + file + " Does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeReader(in);
        }
        return doc;
    }

    /**
     *Closes the data source
     *
     * @Param Reader the reader used to read the txt file
     */
    private void closeReader(Reader reader){
        try
        {
            if (reader != null)
            {
                reader.close();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Formats the XML string and writes it to File
     */
    private void writeToFile(File file, String string){
        PrintWriter out = null;
        try{
            out = new PrintWriter(new FileWriter(file, false));
            out.write("<?xml version = \"1.0\" ?><movie_data><title>movie data</title><movie id=\"1\">" + string);
            out.flush();
        }catch (FileNotFoundException ex){
            System.out.println("Cannot write to file " + file);
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Parses an XML File and stores the data as Java classes
     */
    private void parseFile(File file) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser sp = spf.newSAXParser();
            SaxHandler handler = new SaxHandler();
            sp.parse(file, handler);
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException pce) {
            System.out.println("Error parsing the file");
            pce.printStackTrace();
        }
    }
}
