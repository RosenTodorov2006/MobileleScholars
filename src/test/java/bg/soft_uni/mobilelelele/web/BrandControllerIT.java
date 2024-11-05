package bg.soft_uni.mobilelelele.web;

import bg.soft_uni.mobilelelele.models.entities.Brand;
import bg.soft_uni.mobilelelele.models.entities.Model;
import bg.soft_uni.mobilelelele.repositories.BrandRepository;
import bg.soft_uni.mobilelelele.repositories.ModelRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerIT {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private MockMvc mockMvc;
    @AfterEach
    public void clean(){
        this.modelRepository.deleteAll();;
        this.brandRepository.deleteAll();
    }
    @Test
    @Transactional
    @WithMockUser(username = "Ivan", roles = "USER")
    public void testGetAll() throws Exception {
        Model model = new Model();
        model.setName("model");
        model.setCategory(1);
        model.setImageUrl("/");
        this.modelRepository.save(model);
        Assertions.assertEquals(modelRepository.findByName(model.getName()).getName(), model.getName());
        Assertions.assertEquals(model.getCategory(), modelRepository.findByName(model.getName()).getCategory());
        Assertions.assertEquals(model.getImageUrl(), modelRepository.findByName(model.getName()).getImageUrl());

        Brand brand = new Brand();
        brand.setName("BMW");
        brand.setModels(List.of(model));
        this.brandRepository.save(
                  brand
        );
        List<Brand> all = brandRepository.findAll();
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(all.get(0).getName(), "BMW");
        Assertions.assertEquals(all.get(0).getModels().size(), 1);
        Assertions.assertEquals(all.get(0).getModels().get(0).getName(), model.getName());

        mockMvc.perform(MockMvcRequestBuilders.get("/brands/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("brands"))
                .andExpect(model().attributeExists("brands"))
                .andExpect(model().attribute("brands", hasSize(greaterThan(0))));
    }
}
