package ug.co.sampletracker.app.models.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 9/10/2018.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "Products", strict = false)
public class SmsResponse {
    @ElementList(inline = true)
    public List<Product> products;

}

@Root(name = "productsdetails")
class Product {

    //  private int id;
    @Element(name = "productid")
    private String id;
    @Element(name = "productname")
    private String name;
    @Element(name = "price")
    private String price;
    @Element(name = "instock")
    private int inStock;
    @Element(name = "offer")
    private String offer;
    @Element(name = "color")
    private String color;
    @Element(name = "imageurl")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}