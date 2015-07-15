package com.bond.sky;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    private String temp;
    private String name;
    private String start;
    private String end;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        //required method for Sax parsing - DO NOT DELETE
    }

    public void characters(char[] ch, int start, int length) {
        temp = new String(ch,start,length).trim();
    }

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
}
