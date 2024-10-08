package syksy24;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import syksy24.domain.Category;
import syksy24.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    
    @Test
    @Transactional
    @Rollback
    public void testCreateCategory() {
        Category category = new Category(null);
        category.setName("Science Fiction");

        Category savedCategory = categoryRepository.save(category);

        assertThat(savedCategory).isNotNull();
        
    }

    // Test for finding a Category by name
    @Test
    public void testFindByName() {
        
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        
        List<Category> foundCategories = categoryRepository.findByName("Fantasy");

        assertThat(foundCategories).isNotEmpty();
        assertThat(foundCategories.get(0).getName()).isEqualTo("Fantasy");
    }
}
