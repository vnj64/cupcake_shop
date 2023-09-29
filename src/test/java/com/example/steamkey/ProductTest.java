package com.example.steamkey;

import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private final Validator validator;

    public ProductTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldHaveNoValidationErrors() {
        Product product = new Product();
        product.setTitle("Sample Title");
        product.setDescription("Sample Description");
        product.setPrice(100);
        product.setArea("Sample Area");
        User user = new User();
        product.setUser(user);

        assertEquals(0, validator.validate(product).size());
    }

    @Test
    public void shouldDetectTitleTooShort() {
        Product product = new Product();
        product.setTitle("S");
        product.setDescription("Sample Description");
        product.setPrice(100);
        product.setArea("Sample Area");
        User user = new User();
        product.setUser(user);

        assertEquals(1, validator.validate(product).size());
    }

    @Test
    public void shouldDetectTitleTooLong() {
        Product product = new Product();
        product.setTitle("Sample Title That Exceeds Maximum Length");
        product.setDescription("Sample Description");
        product.setPrice(100);
        product.setArea("Sample Area");
        User user = new User();
        product.setUser(user);

        assertEquals(1, validator.validate(product).size());
    }


}
