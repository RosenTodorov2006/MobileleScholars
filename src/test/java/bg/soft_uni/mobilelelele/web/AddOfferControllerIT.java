package bg.soft_uni.mobilelelele.web;

import bg.soft_uni.mobilelelele.models.entities.Model;
import bg.soft_uni.mobilelelele.models.entities.Offer;
import bg.soft_uni.mobilelelele.repositories.ModelRepository;
import bg.soft_uni.mobilelelele.repositories.OfferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddOfferControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Test
    @WithMockUser(username = "name", roles = "USER")
    public void newOfferTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer-add"))
                .andExpect(model().attributeExists("addOfferDto"));
    }
    @AfterEach
    public void clean(){
        this.offerRepository.deleteAll();
        this.modelRepository.deleteAll();
    }
    @BeforeEach
    public void setUp(){
        this.offerRepository.deleteAll();
        this.modelRepository.deleteAll();
    }
    @Test
    @WithMockUser(username = "name", roles = "ADMIN")
    public void addOfferTest() throws Exception {
        Model model = new Model();
        model.setName("BMW");

        modelRepository.save(model);
        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add")
                .with(csrf())
                .param("model", "BMW")
                .param("price", "1000 ")
                .param("engine", "DIESEL")
                .param("transmission", "TR")
                .param("year", "1968")
                .param("mileage", "1234")
                .param("description", "description")
                .param("url", "/xcvcvx"))
                .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/"));
        Assertions.assertEquals(1, offerRepository.count());
        Optional<Offer> optionalOffer = offerRepository.findById(1L);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            Assertions.assertEquals(offer.getImageUrl(), "/xcvcvx");
            Assertions.assertEquals(offer.getModel().getName(), "BMW");
            Assertions.assertEquals(offer.getPrice(), 1000);
            Assertions.assertEquals(offer.getTransmission(), "TR");
            Assertions.assertEquals(offer.getMileage(), 1234);
            Assertions.assertEquals(offer.getDescription(), "description");
            Assertions.assertEquals(offer.getYear(), 1968);
            Assertions.assertEquals(offer.getEngine(), "DIESEL");
        }
    }
    @Test
    @WithMockUser(username = "name", roles = "ADMIN")
    public void addOfferAndThrowExcTest() throws Exception {
        Model model = new Model();
        model.setName("BMW");

        modelRepository.save(model);
        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add")
                        .with(csrf())
                        .param("model", "BMW")
                        .param("price", "10")
                        .param("engine", "DIESEL")
                        .param("transmission", "TR")
                        .param("year", "1968")
                        .param("mileage", "1234")
                        .param("description", "description")
                        .param("url", "/xcvcvx"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/add"));
    }
    @Test
    @WithMockUser(username = "name", roles = "ADMIN")
    public void viewOffersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/offers/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currencyNames", "offers"))
                .andExpect(view().name("offers"));
    }
    @Test
    @WithMockUser(username = "name", roles = "ADMIN")
    public void viewOffersWithIdTest() throws Exception {
        Model model = new Model();
        model.setName("BMW");
        modelRepository.save(model);

        Offer offer = new Offer();
        offer.setModel(model);
        offer.setPrice(10000);
        offer.setEngine("DIESEL");
        offer.setTransmission("TR");
        offer.setYear(1968);
        offer.setMileage(1234);
        offer.setDescription("description");
        offer.setImageUrl( "/xcvcvx");

        this.offerRepository.save(offer);

        mockMvc.perform(MockMvcRequestBuilders.get("/offers/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("offer"))
                .andExpect(view().name("details"));
    }

}
