package pl.sda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.dao.ProductDAO;
import pl.sda.model.Product;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController {
    @Autowired
    ProductDAO productDAO;

    //    @ResponseStatus(value = HttpStatus.OK)    ----> obadać
//       @GetMapping("/products")
//       public ResponseEntity<List<Product>> getProduts() {
//       return new ResponseEntity<>(productsService.getProducts(), HttpStatus.OK);
//}
    /*@GetMapping("/products") // wyświetl wszystkie produkty z listy
    public List<Product> getAllProducts() {
        return productDAO.getAllProduct();
    }*/
    @GetMapping("/products") // wyświetl wszystkie produkty z listy
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productDAO.getAllProducts(), HttpStatus.OK);
    }

    // Get /products/{id} ---- wyszukiwanie produktu po id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product byId = productDAO.getById(id);
        if (byId != null) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PostMapping /products --- dodanie nowego produktu
    @PostMapping("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        productDAO.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // PutMapping /products/{id} ----- aktualizacja produktu
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductDetails(@RequestBody Product product) {
        int id = product.getId();
        Product byId = productDAO.getById(id);
        if (byId != null) { // zadanie - DAO powinno mieć metode update
            byId.setName(product.getName());
            byId.setPrice(product.getPrice());
            byId.setDescription(product.getDescription());
            return new ResponseEntity<>(productDAO.getById(id), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DeleteMapping /products/{id} ------- usuwanie produktu
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable int id) {
        boolean deleteProduct = productDAO.deleteProduct(id);
        if (deleteProduct != false) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}