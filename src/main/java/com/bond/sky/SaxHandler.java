package com.bond.sky;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class SaxHandler extends DefaultHandler {
    private String temp;
    private String name;
    private String start;
    private String end;

    //method used by the SaxParser to determine start tags
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        //empty method required for Sax parsing - DO NOT DELETE
    }

    //method used by the SaxParser to store strings between start and end tags
    public void characters(char[] ch, int start, int length) {
        temp = new String(ch,start,length).trim();
    }

    //method used by the SaxParser when an end tag is identified
    public void endElement(String uri, String localName,
                           String qName) {
        if(qName.endsWith("channel")) {
            Schedule.add(qName, new Programme(name, start, end));
        }else if (qName.equals("name")) {
            name = temp;
        }else if (qName.equals("start_time")) {
            start = temp;
        }else if (qName.equals("end_time")) {
            end = temp;
        }
    }

    /**
     * Processes the file and stores the contents to the
     * relevant Schedule as instances of the Programme class
     *
     * @Param File the xml file to be processed
     * @return A String for testing purposes
     */
    public String processFile(File file){
        //read the file and save the xml to string
        String doc = readFile(file);
        //format XML so that it can be parsed by SaxParser
        doc = formatXMLString(doc);
        //write the formatted xml string to file
        writeToFile(file, doc);
        //parse the file
        parseFile(file);
        return "File Upload Successful!";
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
     * Formats the XML String so that it can be parsed by the SaxParser
     *
     * @Param String the string to be formatted
     */
    public String formatXMLString(String string){
        int start = 15 + string.indexOf("<movie id");
        int fin = 13 + string.lastIndexOf("</movie_data>"); //add 13 to include </movie_data>
        return string.substring(start, fin);
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
            sp.parse(file, this);
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException pce) {
            System.out.println("Error parsing the file");
            pce.printStackTrace();
        }
    }
}
