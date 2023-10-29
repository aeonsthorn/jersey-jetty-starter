package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotAcceptableException;


@WebService(endpointInterface = "org.example.SoapController")
@SOAPBinding(style = Style.RPC)
public class SoapController {

    private NameService nameService;

    public SoapController() {
        this.nameService = NameService.getInstance();
        this.nameService.populate();
    }

    @WebMethod
    public NameItems getList() {
        NameItems items = new NameItems(this.nameService.getItems());

        return items;
    }


    @WebMethod
    public String add(
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName

    ) {


        if(firstName == null || firstName.isEmpty()) throw new NotAcceptableException("Please provide a first name");

        if(lastName == null || lastName.isEmpty()) throw new NotAcceptableException("Please provide a last name");


        if (this.nameService.add(firstName, lastName)) {
            return "Item added to the list";
        }

        throw new NotAcceptableException("Item already in the list");

    }

    @WebMethod
    public String remove(
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName
    ) {

        if(firstName == null || firstName.isEmpty()) throw new NotAcceptableException("Please provide a first name");

        if(lastName == null || lastName.isEmpty()) throw new NotAcceptableException("Please provide a last name");

        if (this.nameService.remove(firstName, lastName)) {
            return "Item removed from the list";
        }

        throw new NotFoundException("Item is not part of the list");
    }


}
