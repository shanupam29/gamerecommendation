package com.assignment.game.recommendation.controller;

import com.assignment.game.recommendation.entity.Customer;
import com.assignment.game.recommendation.mapper.GamesMapper;
import com.assignment.game.recommendation.model.GameModel;
import com.assignment.game.recommendation.service.CustomerService;
import com.assignment.game.recommendation.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

@RestController
public class CustomerController {
    private static final GamesMapper GAMES_MAPPER = GamesMapper.INSTANCE;
    private static final String FILE_EXTN = ".csv";

    @Autowired
    private CustomerService customerService;

    /**
     * The endpoint to retrieve the list of games recommendations for
     * a given customer by the customer id and count(no of games to be returned).
     *
     * Given - if count is not provided, returns all 10 games recommendations.
     *
     * @param id
     * @param count
     * @return List<GameModel>
     */
    @GetMapping("/customers/{id}/games/recommendations")
    public ResponseEntity<List<GameModel>> fetchGamesById(@PathVariable("id") final Long id,
                                                          @RequestParam(value = "count", required = false) final Long count) {
        if (validateParam(id)) {
            Customer customer = customerService.findGamesById(id);
            if (validateEntity(customer)) {
                return ResponseEntity.ok(GAMES_MAPPER.entityToDto(customer).toList(count));
            } else {
                return ResponseEntity.status(404).body(new ArrayList<>());
            }
        } else {
            return ResponseEntity.status(400).body(new ArrayList<>());
        }
    }

    /**
     * The endpoint to facilitate uploading of a csv file with
     * games recommendation for given customer id. This persists the file
     * to the mysql database using a persistence layer via JPA.
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/customers/upload-customers")
    public ResponseEntity<List<Customer>> uploadGamesRecommendationFile(@RequestParam("csvfile") final MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().endsWith(FILE_EXTN)) {
           ResponseEntity.badRequest().body(new ArrayList<>());
        }
        List<Customer> customers = ExcelReader.parseContents(file);
        return ResponseEntity.ok(customerService.saveCustomers(customers));
    }

    private Boolean validateParam(final Long id) {
        if (null == id || id < 0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean validateEntity(final Customer customer) {
        if (null == customer || null == customer.getId()) {
            return Boolean.FALSE;
        }
        if (customer.getRecommendationedationStatus() == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
