
package panelpackage;

import java.awt.image.BufferedImage;


public class Product {
    
    public String sellerEmail, productName, productPrice, productQuantity,
            productDescription,sold;

    public BufferedImage image;
    
        public Product(){
            
        }
    
        public Product(String sellerEmail,String productName,
            String productPrice, String productQuantity,
            String productDescription, BufferedImage image, String sold) {
        
        this.sellerEmail=sellerEmail;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
        this.productDescription=productDescription;
        this.image=image;
        this.sold=sold;
    }   
}
