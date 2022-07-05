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
import pro.sky.telegrambot.RestControllers.DataAnimalController;
import pro.sky.telegrambot.RestControllers.DataAnimalOwnerController;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.models.DataAnimalOwner;
import pro.sky.telegrambot.repositories.IDataAnimalOwnerRepository;
import pro.sky.telegrambot.repositories.IDataAnimalRepository;
import pro.sky.telegrambot.services.DataAnimalOwnerServices;
import pro.sky.telegrambot.services.DataAnimalServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.telegrambot.TestConstants.*;

@WebMvcTest(controllers = DataAnimalOwnerController.class)
public class DataAnimalOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDataAnimalOwnerRepository iDataAnimalOwnerRepository;

    @SpyBean
    private DataAnimalOwnerServices dataAnimalOwnerServices;

    @InjectMocks
    private DataAnimalOwnerController dataAnimalOwnerController;

    @Test
    public void findAll() throws Exception {

        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        List<DataAnimalOwner> dataAnimalOwners = new ArrayList<>();
        dataAnimalOwners.add(dataAnimalOwner);

        when(iDataAnimalOwnerRepository.findAll()).thenReturn(dataAnimalOwners);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/DataAnimalOwner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID_OWNER))
                .andExpect(jsonPath("$[0].name").value(OWNER_NAME))
                .andExpect(jsonPath("$[0].idAnimal").value(ID_ANIMAL))
                .andExpect(jsonPath("$[0].idChat").value(ID_CHAT));
    }

    @Test
    public void findByIdAnimal() throws Exception {
        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.findByIdAnimal(any(String.class))).thenReturn(dataAnimalOwner);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/DataAnimalOwner/getDataAnimalOwnerByAnimalId/" + dataAnimalOwner.getIdAnimal())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_OWNER))
                .andExpect(jsonPath("$.name").value(OWNER_NAME))
                .andExpect(jsonPath("$.idAnimal").value(ID_ANIMAL))
                .andExpect(jsonPath("$.idChat").value(ID_CHAT));
    }

    @Test
    public void saveOwner() throws Exception {
        JSONObject ownerObject = new JSONObject();
        ownerObject.put("NAME", OWNER_NAME);
        ownerObject.put("idAnimal", ID_ANIMAL);
        ownerObject.put("idChat", ID_CHAT);

        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.save(any(DataAnimalOwner.class))).thenReturn(dataAnimalOwner);
        when(iDataAnimalOwnerRepository.findById(any(String.class))).thenReturn(Optional.of(dataAnimalOwner));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/DataAnimalOwner/add")
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_OWNER))
                .andExpect(jsonPath("$.name").value(OWNER_NAME))
                .andExpect(jsonPath("$.idAnimal").value(ID_ANIMAL))
                .andExpect(jsonPath("$.idChat").value(ID_CHAT));
    }

    @Test
    public void findByIdOwner() throws Exception {
        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.findByIdOwner(any(String.class))).thenReturn(dataAnimalOwner);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/DataAnimalOwner/getDataAnimalOwnerByOwnerId/" + dataAnimalOwner.getIdOwner())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_OWNER))
                .andExpect(jsonPath("$.name").value(OWNER_NAME))
                .andExpect(jsonPath("$.idAnimal").value(ID_ANIMAL))
                .andExpect(jsonPath("$.idChat").value(ID_CHAT));
    }

    @Test
    public void updateOwner() throws Exception {
        JSONObject ownerObject = new JSONObject();
        ownerObject.put("NAME", OWNER_NAME);
        ownerObject.put("idAnimal", ID_ANIMAL);
        ownerObject.put("idChat", ID_CHAT);

        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.save(any(DataAnimalOwner.class))).thenReturn(dataAnimalOwner);
        when(iDataAnimalOwnerRepository.findById(any(String.class))).thenReturn(Optional.of(dataAnimalOwner));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/DataAnimalOwner/edit")
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_OWNER))
                .andExpect(jsonPath("$.name").value(OWNER_NAME))
                .andExpect(jsonPath("$.idAnimal").value(ID_ANIMAL))
                .andExpect(jsonPath("$.idChat").value(ID_CHAT));
    }

    @Test
    public void deleteByIDOwner() throws Exception {
        JSONObject ownerObject = new JSONObject();
        ownerObject.put("NAME", OWNER_NAME);
        ownerObject.put("idAnimal", ID_ANIMAL);
        ownerObject.put("idChat", ID_CHAT);

        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.save(any(DataAnimalOwner.class))).thenReturn(dataAnimalOwner);
        doNothing().when(iDataAnimalOwnerRepository).deleteByIdOwner(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/DataAnimalOwner/deleteByIdOwner/" + dataAnimalOwner.getIdOwner())
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdAnimal() throws Exception {
        JSONObject ownerObject = new JSONObject();
        ownerObject.put("NAME", OWNER_NAME);
        ownerObject.put("idAnimal", ID_ANIMAL);
        ownerObject.put("idChat", ID_CHAT);

        DataAnimalOwner dataAnimalOwner = new DataAnimalOwner();
        dataAnimalOwner.setIdOwner(ID_OWNER);
        dataAnimalOwner.setOwnerName(OWNER_NAME);
        dataAnimalOwner.setIdAnimal(ID_ANIMAL);
        dataAnimalOwner.setChatId(ID_CHAT);

        when(iDataAnimalOwnerRepository.save(any(DataAnimalOwner.class))).thenReturn(dataAnimalOwner);
        doNothing().when(iDataAnimalOwnerRepository).deleteByIdAnimal(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/DataAnimalOwner/deleteByIdAnimal/" + dataAnimalOwner.getIdAnimal())
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
