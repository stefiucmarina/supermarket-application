package org.marina.services;

import org.marina.exceptions.ProductDoesNotExist;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import org.marina.exceptions.ProductAlreadyExistsException;
import org.marina.model.Product;

import java.util.List;
import java.util.Objects;

import static org.marina.services.FileSystemService.getPathToFile;

public class ProductService {

    public static ObjectRepository<Product> productRepository;

    public static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("Products.db").toFile())
                .openOrCreate("test1", "test1");

        productRepository = database.getRepository(Product.class);
    }

    public static Nitrite getDatabase(){
        return database;
    }

    public static List<Product> getAllProducts(){
        return productRepository.find().toList();
    }

    public static void addProduct(String name, String category, String code, Integer quantity, Integer price) throws ProductAlreadyExistsException {
        checkProductDoesNotAlreadyExist(name);
        productRepository.insert(new Product(name, category, code, quantity, price));
    }

    public static void removeProduct(String name, String code) throws ProductDoesNotExist {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName()) && Objects.equals(code, product.getCode())){
                productRepository.remove(product);
                return;
            }
        }
        throw new ProductDoesNotExist(name);
    }

    public static void modifyProduct(String name ,Integer newValue) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName())){
                product.setQuantity(newValue);
                productRepository.update(product);
            }
        }
    }

    public static int getPrice(String name ,Integer quantity) {
        int price = 1;
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName())){
                price = product.getPrice();
            }
        }
        return price * quantity;
    }

    private static void checkProductDoesNotAlreadyExist(String name) throws ProductAlreadyExistsException {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName()))
                throw new ProductAlreadyExistsException(name);
        }
    }
}
