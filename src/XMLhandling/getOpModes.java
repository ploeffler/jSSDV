/*
 * Copyright (C) 2017 User
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package XMLhandling;

import imageing.TXStockImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.text.Element;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import modelist.Opmode;
import org.apache.commons.io.input.XmlStreamReader;

/**
 *
 * @author User
 */
public class getOpModes {

    private enum Element {
        MODE, MODEM, NAME,ENCODE, SHORTNAME, CENTER, HEADERREPEATE, ID, DESCRIPTION, EDITABLE,DATASTRUCTURE
    }
    private final XMLInputFactory factory;
    private final Map<String, Element> nameToTypeMapping = new HashMap<>();

    private StringBuilder currentText;
    private Element currentElement;
    private Opmode myItem;
    private final List<Opmode> myList = new LinkedList<>();
    /**
     * Create the stream reader class and put in the mappings between element
     * names and the Element type (currentElement). This mapping is used to work
     * out how to handle each data type.
     */
    public getOpModes() {
        factory = XMLInputFactory.newFactory();
        nameToTypeMapping.put("mode", Element.MODE);
        nameToTypeMapping.put("name", Element.NAME);
        nameToTypeMapping.put("description", Element.DESCRIPTION);
        nameToTypeMapping.put("editable", Element.EDITABLE);
        nameToTypeMapping.put("id", Element.ID);
        nameToTypeMapping.put("short", Element.SHORTNAME);
        nameToTypeMapping.put("headerrepeate", Element.HEADERREPEATE);
        nameToTypeMapping.put("datastructure", Element.DATASTRUCTURE);
        nameToTypeMapping.put("modem",Element.MODEM);
        nameToTypeMapping.put("center", Element.CENTER);
        nameToTypeMapping.put("encode", Element.ENCODE);
    }

    /**
     * Actually performs the read operation on the XML file by repeatedly
     * calling hasNext() and next() until the document ends.
     *
     * @param input the XML stream to read.
     * @return
     * @throws XMLStreamException if the parsing fails.
     */
    public List readXml(InputStream input) throws XMLStreamException {
       
        // get an XML reader instance.
        XMLStreamReader xmlReader = factory.createXMLStreamReader(input);

        // before calling next() we can find out key things about the
        // document, because we would now be in XMLEvent.START_DOCUMENT
        // state.
        assert (xmlReader.getEventType() == XMLEvent.START_DOCUMENT);

        // iterate by calling hasNext in a loop until there are no more
        // elements left to process.
        while (xmlReader.hasNext()) {

            // get the next event and process it.
            int eventType = xmlReader.next();
            switch (eventType) {
                case XMLEvent.CDATA:
                case XMLEvent.SPACE:
                case XMLEvent.CHARACTERS:
                    processText(xmlReader.getText());
                    break;
                case XMLEvent.END_ELEMENT:
                    ended(xmlReader.getLocalName());
                    break;
                case XMLEvent.START_ELEMENT:
                    startElement(xmlReader.getLocalName());
                    int attributes = xmlReader.getAttributeCount();
                    for (int i = 0; i < attributes; ++i) {
                        attribute(xmlReader.getAttributeLocalName(i),
                                xmlReader.getAttributeValue(i));
                    }
                    break;
            }

        }
        return myList;
    }

    /**
     * Handles the start of a new XML element, so we can prepare for the new
     * element. In our case we clear down the text storage and set the element
     * type field.
     *
     * @param localName the name of the element without namespace
     */
    private void startElement(String localName) {
        currentElement = nameToTypeMapping.get(localName);
        currentText = new StringBuilder(256);
        if (currentElement == Element.MODE) {
            myItem = new Opmode();
        }
    }

    /**
     * Called when text is found within the element, this may be whitespace,
     * text or CDATA.
     *
     * @param text the text to be added to the current elements data.
     */
    private void processText(String text) {
        if (currentElement != null && currentText != null) {
            currentText.append(text);
        }
    }

    /**
     * Called for each attribute in the start element call. With the name and
     * value.
     *
     * @param localName the name of the attribute
     * @param value the value of the attribute
     */
    private void attribute(String localName, String value) {
        if (currentElement == Element.MODE && localName.equals("id")) {
            myItem.setID(Integer.valueOf(value));
        }
        if (currentElement == Element.MODE && localName.equals("name")) {
            myItem.setName(value);
        }
    }

    /**
     * Called each time an element ends, with the name of the element that has
     * just ended.
     *
     * @param localName the element name that has ended
     */
    private void ended(String localName) {
        // find the element type, and see if we can process it.
        currentElement = nameToTypeMapping.get(localName);
        if (currentElement != null) {

            // We can process the element, so perform the right function.
            // In a real world example, the "currentElement" type may be
            // more complex and have functionality to perform the action.
            switch (currentElement) {
                case SHORTNAME:
                    myItem.setShortName(currentText.toString());
                    break;
                case MODE:
                    myList.add(myItem);
                    break;
                     case MODEM:
                    myItem.setModems(currentText.toString());
                    break;
                    case ENCODE:
                    myItem.setEncode(currentText.toString());
                    break;
                case NAME:
                    //         String name = currentText.toString();
                    //         System.out.println("Name: "+name);
                    myItem.setName(currentText.toString());
                    break;
                case DESCRIPTION:

                    myItem.setDescription(currentText.toString());
                    break;
               

            }
            currentElement = null;
            currentText = null;
        }
    }

    /**
     * Renders an Animal instance onto the console.
     */
    private void renderImage() {
        System.out.println("Image-object created: id=" + myItem.getID());
        System.out.println("  name=" + myItem.getName());
        System.out.println("  Description=" + myItem.getDescription());

    }

}
