package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities;

import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAnyElement;

/**
 * This class is used as an helper class to un-/marshal
 * Collections (Lists) of REST resources.
 * It should be provided to the marshaller / unmarshaller 
 * in addition to the corresponding entity class.
 * Ex.: unmarshall List&lt;Tbluser&gt;
 * JAXBContext.newInstance(ListWrapper.class, Tbluser.class);
 * 
 * @author Christian Ries
 *
 * @param <T> Generic Type
 */
public class ListWrapper<T> {
 
    private List<T> myItems;
 
    public ListWrapper() {
        myItems = new ArrayList<T>();
    }
 
    public ListWrapper(List<T> items) {
        this.myItems = items;
    }
 
    // Map the XML Elements (List items)
    // to the corresponding class
    // In addition to the wrapper class, the root class is provided
    // XmlAnyElement binds the items to the XmlRootElement
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return myItems;
    }
 
}
