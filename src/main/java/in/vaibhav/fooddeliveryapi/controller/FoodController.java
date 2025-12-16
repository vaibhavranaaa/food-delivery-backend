package in.vaibhav.fooddeliveryapi.controller;

import in.vaibhav.fooddeliveryapi.io.FoodRequest;
import in.vaibhav.fooddeliveryapi.io.FoodResponse;
import in.vaibhav.fooddeliveryapi.service.FoodService;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


import java.util.List;


@RequestMapping("/api/foods")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class FoodController {

    private final FoodService foodService;
    @PostMapping
    public FoodResponse addFood(@RequestPart("food")String foodString, @RequestPart("file")MultipartFile file) {
        ObjectMapper objectMapper=new ObjectMapper();
        FoodRequest request=null;
        request=objectMapper.readValue(foodString,FoodRequest.class);
        FoodResponse response=foodService.addFood(request,file);
        return response;



    }
    @GetMapping
    public List<FoodResponse> readFoods(){
        return foodService.readfoods();

    }
    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id){
        return foodService.readFood(id);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);


    }
}
