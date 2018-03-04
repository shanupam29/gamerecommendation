package com.assignment.game.recommendation.controller;

import com.assignment.game.recommendation.entity.Customer;
import com.assignment.game.recommendation.mapper.GamesMapper;
import com.assignment.game.recommendation.model.GameModel;
import com.assignment.game.recommendation.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    private ObjectMapper objectMapper;
    private static final GamesMapper GAMES_MAPPER = GamesMapper.INSTANCE;


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;



    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * - When :  Valid customer id and count. (requested to return 5 games recommendation from the service for a given customer id.)
     * - Given : Valid customer id 1111 and count = 5
     * - Expected :  Return Success response with List<GameModel> JSON object with 5 games.
     *
     * @throws Exception
     */
    @Test
    public void test_fetchGamesById_ReturnSuccess() throws Exception {
        Long id = 11111L;
        Customer expectedCustomer = buildValidCustomer(id);
        given(customerService.findGamesById(id)).willReturn(expectedCustomer);
        List<GameModel> expectedResponse =  GAMES_MAPPER.entityToDto(expectedCustomer) != null ?   GAMES_MAPPER.entityToDto(expectedCustomer).toList(5L) : null;
        this.mvc.perform(get("/customers/{id}/games/recommendations?count=5", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
        verify(customerService, times(1)).findGamesById(id);
    }

    /**
     * - When : Invalid customer id with negative value and count of 5.
     * - Given : Invalid customer id < 0.
     * - Expected :  Bad Request with response status 400

     * @throws Exception
     */
    @Test
    public void test_fetchGamesById_nullCustomerId_ReturnBadRequest() throws Exception {
        Long id = -1L;
        this.mvc.perform(get("/customers/{id}/games/recommendations?count=5", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * - When : Valid customer id with Rec status active flag false and count of 5.
     * - Given: Customer id with rec status active as false.
     * - Then :  Resource not Found with response status 404 not found

     * @throws Exception
     */
    @Test
    public void test_fetchGamesById_validCustomerIdWithRecActiveFalse_ReturnNotFound() throws Exception {
        Long id = 11111L;
        Customer expectedCustomer = buildValidCustomerWithRecStatusFalse(id);
        given(customerService.findGamesById(id)).willReturn(expectedCustomer);
        List<GameModel> expectedResponse =  GAMES_MAPPER.entityToDto(expectedCustomer) != null ?   GAMES_MAPPER.entityToDto(expectedCustomer).toList(5L) : null;
        this.mvc.perform(get("/customers/{id}/games/recommendations?count=5", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * - When : Valid customer id not found in the database.
     * - Given : valid customer id and count of 5. service returned null customer as not found any customer id with id in database.
     * - Expected :  Resource not Found with response status 404 not found

     * @throws Exception
     */
    @Test
    public void test_fetchGamesById_inValidCustomerIDNotFound_ReturnNotFound() throws Exception {
        Long id = null;
        Customer expectedCustomer = buildValidCustomer(id);
        given(customerService.findGamesById(id)).willReturn(expectedCustomer);
        List<GameModel> expectedResponse =  GAMES_MAPPER.entityToDto(expectedCustomer) != null ?   GAMES_MAPPER.entityToDto(expectedCustomer).toList(5L) : null;
        this.mvc.perform(get("/customers/{id}/games/recommendations?count=5", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     *
     * - When : Valid csv file with games for customer id's.
     * - Given :  Sample csv file to be uploaded
     * - Expected :  Sucess Response
     *
     * @throws Exception
     */
    @Test
    public void test_uploadGamesRecommendationFile_Success() throws Exception {
        File file  = new File("customer_data.csv");
        MockMultipartFile multipartFile = new MockMultipartFile("csvfile", "customer_data.csv", "text/csv", Files.readAllBytes(file.toPath()));
        List<Customer> expectedCustomerList = buildCustomerLst();
        given(customerService.saveCustomers(expectedCustomerList)).willReturn(expectedCustomerList);
        this.mvc.perform(multipart("/customers/upload-customers").file(multipartFile)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    private Customer buildValidCustomer(Long id) {
        return id != null ? new Customer(id,true,"rummy",
                "poker","sevenwins","russian-roulette",
                "bingo","blackjack","slots","NeedforSpeed",
                "Mario","GTA") : null;
    }

    private Customer buildValidCustomerWithRecStatusFalse(Long id) {
        return id != null ? new Customer(id,false,"rummy",
                "poker","sevenwins","russian-roulette",
                "bingo","blackjack","slots","NeedforSpeed",
                "Mario","GTA") : null;
    }

    private List<Customer> buildCustomerLst(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(11113L,true,"rummy",
                "poker","sevenwins","russian-roulette",
                "bingo","blackjack","slots","NeedforSpeed",
                "Mario","GTA"));
        list.add(new Customer(11114L,true,"rummy","AgeOfEmpire",
                "GamesOfThrones","Java","bingo 2","blackjack",
                "slots","NeedforSpeed","Mario","GTA"));
        return list;

    }

}
