package pro.sky.telegrambot;

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
import pro.sky.telegrambot.RestControllers.NsiAnimalKindController;
import pro.sky.telegrambot.RestControllers.NsiBreedAnimalController;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.models.NsiBreedAnimal;
import pro.sky.telegrambot.repositories.INsiAnimalKindRepositiry;
import pro.sky.telegrambot.repositories.INsiBreedAnimalRepository;
import pro.sky.telegrambot.services.NsiAnimalKindServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.telegrambot.TestConstants.*;
import static pro.sky.telegrambot.TestConstants.ANIMAL_KIND;

@WebMvcTest(controllers = NsiBreedAnimalController.class)
public class NsiBreedAnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private INsiBreedAnimalRepository iNsiBreedAnimalRepository;

    @SpyBean
    private NsiAnimalKindServices nsiAnimalKindServices;

    @InjectMocks
    private NsiBreedAnimalController nsiBreedAnimalController;


    @Test
    public void getAllBreed() throws Exception {

        NsiBreedAnimal nsiBreedAnimal = new NsiBreedAnimal();
        nsiBreedAnimal.setId(ID_BREED);
        nsiBreedAnimal.setName(ANIMAL_BREED);

        List<NsiBreedAnimal> nsiBreedAnimals = new ArrayList<>();
        nsiBreedAnimals.add(nsiBreedAnimal);

        when(iNsiBreedAnimalRepository.findAll()).thenReturn(nsiBreedAnimals);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/nsiBreedAnimal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID_KIND))
                .andExpect(jsonPath("$[0].animal_kind").value(ANIMAL_BREED));
    }

    @Test
    public void addBreed() throws Exception {

        JSONObject breedObject = new JSONObject();
        breedObject.put("id", ID_BREED);
        breedObject.put("breed_name", ANIMAL_BREED);

        NsiBreedAnimal nsiBreedAnimal = new NsiBreedAnimal();
        nsiBreedAnimal.setId(ID_BREED);
        nsiBreedAnimal.setName(ANIMAL_BREED);

        when(iNsiBreedAnimalRepository.save(any(NsiBreedAnimal.class))).thenReturn(nsiBreedAnimal);
        when(iNsiBreedAnimalRepository.findById(any(String.class))).thenReturn(Optional.of(nsiBreedAnimal));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/nsiBreedAnimal/add")
                        .content(breedObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_BREED))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_BREED));
    }

    @Test
    public void editKind() throws Exception {

        JSONObject breedObject = new JSONObject();
        breedObject.put("id", ID_BREED);
        breedObject.put("breed_name", ANIMAL_BREED);

        NsiBreedAnimal nsiBreedAnimal = new NsiBreedAnimal();
        nsiBreedAnimal.setId(ID_BREED);
        nsiBreedAnimal.setName(ANIMAL_BREED);

        when(iNsiBreedAnimalRepository.save(any(NsiBreedAnimal.class))).thenReturn(nsiBreedAnimal);
        when(iNsiBreedAnimalRepository.findById(any(String.class))).thenReturn(Optional.of(nsiBreedAnimal));


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/nsiBreedAnimal/edit")
                        .content(breedObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_BREED))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_BREED));
    }

    @Test
    public void delete() throws Exception {

        JSONObject breedObject = new JSONObject();
        breedObject.put("id", ID_BREED);
        breedObject.put("breed_name", ANIMAL_BREED);

        NsiBreedAnimal nsiBreedAnimal = new NsiBreedAnimal();
        nsiBreedAnimal.setId(ID_BREED);
        nsiBreedAnimal.setName(ANIMAL_BREED);

        when(iNsiBreedAnimalRepository.save(any(NsiBreedAnimal.class))).thenReturn(nsiBreedAnimal);
        doNothing().when(iNsiBreedAnimalRepository).deleteById(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/nsiBreedAnimal/delete/" + nsiBreedAnimal.getId())
                        .content(breedObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
