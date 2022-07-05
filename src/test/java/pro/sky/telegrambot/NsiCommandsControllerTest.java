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
import pro.sky.telegrambot.RestControllers.NsiCommandsController;
import pro.sky.telegrambot.models.NsiCommands;
import pro.sky.telegrambot.repositories.INsiCommandsRepository;
import pro.sky.telegrambot.services.NsiCommandServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.telegrambot.TestConstants.*;

@WebMvcTest(controllers = NsiCommandsController.class)
public class NsiCommandsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private INsiCommandsRepository iNsiCommandsRepository;

    @SpyBean
    private NsiCommandServices nsiCommandServices;

    @InjectMocks
    private NsiCommandsController nsiCommandsController;

    @Test
    public void getAll() throws Exception {

        NsiCommands nsiCommands = new NsiCommands();
        nsiCommands.setId(ID_COMMAND);
        nsiCommands.setCommand(COMMAND);
        nsiCommands.setLevel(LEVEL);
        nsiCommands.setDescription(DESCRIPTION);

        List<NsiCommands> nsiCommandsList = new ArrayList<>();
        nsiCommandsList.add(nsiCommands);

        when(iNsiCommandsRepository.findAll()).thenReturn(nsiCommandsList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/nsiCommands")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID_COMMAND))
                .andExpect(jsonPath("$[0].command").value(COMMAND))
                .andExpect(jsonPath("$[0].level").value(LEVEL))
                .andExpect(jsonPath("$[0].description").value(DESCRIPTION));
    }

    @Test
    public void save() throws Exception {

        JSONObject nsiObject = new JSONObject();
        nsiObject.put("command", COMMAND);
        nsiObject.put("level", LEVEL);
        nsiObject.put("description", DESCRIPTION);

        NsiCommands nsiCommands = new NsiCommands();
        nsiCommands.setId(ID_COMMAND);
        nsiCommands.setCommand(COMMAND);
        nsiCommands.setLevel(LEVEL);
        nsiCommands.setDescription(DESCRIPTION);

        when(iNsiCommandsRepository.save(any(NsiCommands.class))).thenReturn(nsiCommands);
        when(iNsiCommandsRepository.findById(any(String.class))).thenReturn(Optional.of(nsiCommands));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/nsiCommands/add")
                        .content(nsiObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_COMMAND))
                .andExpect(jsonPath("$.command").value(COMMAND))
                .andExpect(jsonPath("$.level").value(LEVEL))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    public void editTest() throws Exception {
        JSONObject nsiObject = new JSONObject();
        nsiObject.put("command", COMMAND);
        nsiObject.put("level", LEVEL);
        nsiObject.put("description", DESCRIPTION);

        NsiCommands nsiCommands = new NsiCommands();
        nsiCommands.setId(ID_COMMAND);
        nsiCommands.setCommand(COMMAND);
        nsiCommands.setLevel(LEVEL);
        nsiCommands.setDescription(DESCRIPTION);

        when(iNsiCommandsRepository.save(any(NsiCommands.class))).thenReturn(nsiCommands);
        when(iNsiCommandsRepository.findById(any(String.class))).thenReturn(Optional.of(nsiCommands));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/nsiCommands/edit")
                        .content(nsiObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_COMMAND))
                .andExpect(jsonPath("$.command").value(COMMAND))
                .andExpect(jsonPath("$.level").value(LEVEL))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    public void deleteByID() throws Exception {
        JSONObject nsiObject = new JSONObject();
        nsiObject.put("command", COMMAND);
        nsiObject.put("level", LEVEL);
        nsiObject.put("description", DESCRIPTION);

        NsiCommands nsiCommands = new NsiCommands();
        nsiCommands.setId(ID_COMMAND);
        nsiCommands.setCommand(COMMAND);
        nsiCommands.setLevel(LEVEL);
        nsiCommands.setDescription(DESCRIPTION);

        when(iNsiCommandsRepository.save(any(NsiCommands.class))).thenReturn(nsiCommands);
        doNothing().when(iNsiCommandsRepository).deleteById(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/nsiCommands/delete/" + nsiCommands.getId())
                        .content(nsiObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
