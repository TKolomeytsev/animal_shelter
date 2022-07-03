package pro.sky.telegrambot;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.RestControllers.NsiAnimalKindController;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.repositories.INsiAnimalKindRepositiry;
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

@WebMvcTest(controllers = NsiAnimalKindController.class)
public class NsiAnimalKindControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private INsiAnimalKindRepositiry iNsiAnimalKindRepositiry;

    @SpyBean
    private NsiAnimalKindServices nsiAnimalKindServices;

    @InjectMocks
    private NsiAnimalKindController nsiAnimalKindController;


    @Test
    public void getAllKind() throws Exception {

        NsiAnimalKind nsiAnimalKind = new NsiAnimalKind();
        nsiAnimalKind.setId(ID_KIND);
        nsiAnimalKind.setName(ANIMAL_KIND);

        List<NsiAnimalKind> nsiAnimalKinds = new ArrayList<>();
        nsiAnimalKinds.add(nsiAnimalKind);

        when(iNsiAnimalKindRepositiry.findAll()).thenReturn(nsiAnimalKinds);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/nsiAnimalKind")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID_KIND))
                .andExpect(jsonPath("$[0].animal_kind").value(ANIMAL_KIND));
    }

    @Test
    public void addKind() throws Exception {

        JSONObject kindObject = new JSONObject();
        kindObject.put("id", ID_KIND);
        kindObject.put("kind_name", ANIMAL_KIND);

        NsiAnimalKind nsiAnimalKind = new NsiAnimalKind();
        nsiAnimalKind.setId(ID_KIND);
        nsiAnimalKind.setName(ANIMAL_KIND);

        when(iNsiAnimalKindRepositiry.save(any(NsiAnimalKind.class))).thenReturn(nsiAnimalKind);
        when(iNsiAnimalKindRepositiry.findById(any(String.class))).thenReturn(Optional.of(nsiAnimalKind));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/nsiAnimalKind/add")
                        .content(kindObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_KIND))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_KIND));
    }

    @Test
    public void editKind() throws Exception {

        JSONObject kindObject = new JSONObject();
        kindObject.put("id", ID_KIND);
        kindObject.put("kind_name", ANIMAL_KIND);

        NsiAnimalKind nsiAnimalKind = new NsiAnimalKind();
        nsiAnimalKind.setId(ID_KIND);
        nsiAnimalKind.setName(ANIMAL_KIND);

        when(iNsiAnimalKindRepositiry.save(any(NsiAnimalKind.class))).thenReturn(nsiAnimalKind);
        when(iNsiAnimalKindRepositiry.findById(any(String.class))).thenReturn(Optional.of(nsiAnimalKind));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/nsiAnimalKind/edit")
                        .content(kindObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_KIND))
                .andExpect(jsonPath("$.nickname").value(ANIMAL_KIND));
    }

    @Test
    public void delete() throws Exception {
        JSONObject kindObject = new JSONObject();
        kindObject.put("id", ID_KIND);
        kindObject.put("kind_name", ANIMAL_KIND);

        NsiAnimalKind nsiAnimalKind = new NsiAnimalKind();
        nsiAnimalKind.setId(ID_KIND);
        nsiAnimalKind.setName(ANIMAL_KIND);

        when(iNsiAnimalKindRepositiry.save(any(NsiAnimalKind.class))).thenReturn(nsiAnimalKind);
        doNothing().when(iNsiAnimalKindRepositiry).deleteById(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/nsiAnimalKind/delete/" + nsiAnimalKind.getId())
                        .content(kindObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
