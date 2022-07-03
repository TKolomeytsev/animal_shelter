package pro.sky.telegrambot;

import liquibase.pro.packaged.A;
import liquibase.pro.packaged.C;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.RestControllers.DataAnimalController;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.repositories.IDataAnimalRepository;
import pro.sky.telegrambot.services.DataAnimalServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.telegrambot.TestConstants.*;

@WebMvcTest(controllers = DataAnimalController.class)
public class DataAnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDataAnimalRepository iDataAnimalRepository;

    @SpyBean
    private DataAnimalServices dataAnimalServices;

    @InjectMocks
    private DataAnimalController dataAnimalController;

    @Test
    public void createAnimalTest() throws Exception {
        JSONObject animalObject = new JSONObject();
        animalObject.put("nickname", ANIMAL_NICKNAME);
        animalObject.put("age", AGE);
        animalObject.put("weight", WEIGHT);
        animalObject.put("growth", GROWTH);
        animalObject.put("color", COLOR);

        DataAnimal dataAnimal = new DataAnimal();
        dataAnimal.setId(ID_ANIMAL);
        dataAnimal.setNickname(ANIMAL_NICKNAME);
        dataAnimal.setAge(AGE);
        dataAnimal.setColor(COLOR);
        dataAnimal.setGrowth(GROWTH);
        dataAnimal.setWeight(WEIGHT);

        when(iDataAnimalRepository.save(any(DataAnimal.class))).thenReturn(dataAnimal);
        when(iDataAnimalRepository.findById(any(String.class))).thenReturn(Optional.of(dataAnimal));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/DataAnimal/add")
                        .content(animalObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_ANIMAL))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_NICKNAME))
                .andExpect(jsonPath("$.age").value(AGE))
                .andExpect(jsonPath("$.color").value(COLOR))
                .andExpect(jsonPath("$.growth").value(GROWTH))
                .andExpect(jsonPath("$.weight").value(WEIGHT));
    }

    @Test
    public void getAllTest() throws Exception {

        DataAnimal dataAnimal = new DataAnimal();
        dataAnimal.setId(ID_ANIMAL);
        dataAnimal.setNickname(ANIMAL_NICKNAME);
        dataAnimal.setAge(AGE);
        dataAnimal.setColor(COLOR);
        dataAnimal.setGrowth(GROWTH);
        dataAnimal.setWeight(WEIGHT);

        List<DataAnimal> dataAnimals = new ArrayList<>();
        dataAnimals.add(dataAnimal);

        when(iDataAnimalRepository.findAll()).thenReturn(dataAnimals);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/DataAnimal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID_ANIMAL))
                .andExpect(jsonPath("$[0].nickname").value(ANIMAL_NICKNAME))
                .andExpect(jsonPath("$[0].age").value(AGE))
                .andExpect(jsonPath("$[0].color").value(COLOR))
                .andExpect(jsonPath("$[0].growth").value(GROWTH))
                .andExpect(jsonPath("$[0].weight").value(WEIGHT));
    }

    @Test
    public void updateAnimalTest() throws Exception {
        JSONObject animalObject = new JSONObject();
        animalObject.put("nickname", ANIMAL_NICKNAME);
        animalObject.put("age", AGE);
        animalObject.put("weight", WEIGHT);
        animalObject.put("growth", GROWTH);
        animalObject.put("color", COLOR);

        DataAnimal dataAnimal = new DataAnimal();
        dataAnimal.setId(ID_ANIMAL);
        dataAnimal.setNickname(ANIMAL_NICKNAME);
        dataAnimal.setAge(AGE);
        dataAnimal.setColor(COLOR);
        dataAnimal.setGrowth(GROWTH);
        dataAnimal.setWeight(WEIGHT);

        when(iDataAnimalRepository.save(any(DataAnimal.class))).thenReturn(dataAnimal);
        when(iDataAnimalRepository.findById(any(String.class))).thenReturn(Optional.of(dataAnimal));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/DataAnimal/edit")
                        .content(animalObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_ANIMAL))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_NICKNAME))
                .andExpect(jsonPath("$.age").value(AGE))
                .andExpect(jsonPath("$.color").value(COLOR))
                .andExpect(jsonPath("$.growth").value(GROWTH))
                .andExpect(jsonPath("$.weight").value(WEIGHT));
    }

    @Test
    public void deleteAnimalIdTest() throws Exception {
        JSONObject animalObject = new JSONObject();
        animalObject.put("nickname", ANIMAL_NICKNAME);
        animalObject.put("age", AGE);
        animalObject.put("weight", WEIGHT);
        animalObject.put("growth", GROWTH);
        animalObject.put("color", COLOR);

        DataAnimal dataAnimal = new DataAnimal();
        dataAnimal.setId(ID_ANIMAL);
        dataAnimal.setNickname(ANIMAL_NICKNAME);
        dataAnimal.setAge(AGE);
        dataAnimal.setColor(COLOR);
        dataAnimal.setGrowth(GROWTH);
        dataAnimal.setWeight(WEIGHT);

        when(iDataAnimalRepository.save(any(DataAnimal.class))).thenReturn(dataAnimal);
        doNothing().when(iDataAnimalRepository).deleteById(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/DataAnimal/delete/" + dataAnimal.getId())
                        .content(animalObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
