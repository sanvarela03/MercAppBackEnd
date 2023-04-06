package mercado.controller.PedidoApiRest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CrearPedidoTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CrearPedido crearPedido;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(crearPedido).build();
    }

    @Test
    public void testCrearPedido() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));

        String URI = "/";
    }

    @Test
    public void testHolaMundo() throws Exception {

        String URI = "/mercapp/compradores/crearPedido/holaMundo";

        mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hola mundo test"));
    }

    @Test
    public void testHolaMundoJSON() throws Exception, JsonParseException, JsonMappingException, IOException {
        String URI = "/mercapp/compradores/crearPedido/jsonHola";
        mockMvc.perform(
                get(URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", Matchers.is("Saludos")))
                .andExpect(jsonPath("$.valor", Matchers.is("Hola mundo test!")));

//        var mvcResult = mockMvc.perform(get(URI).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        var response = mvcResult.getResponse();
//
//        System.out.println("MENSAJE DE ERROR: "+response.getErrorMessage());
//        System.out.println("CONTENIDO: "+response.getContentAsString());
//
//        assertEquals(200, response.getStatus());

    }

    @Test
    public void testHolaMundoPOST() throws Exception {
        String URI = "/mercapp/compradores/crearPedido/holaMundoPOST";

        Hola hola = new Hola("Saludos_Post", "Hola mundo post");

        String jsonEntrada = mapearAJson(hola);

//        mockMvc.perform(
//                post(URI)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(jsonEntrada)
//        )
//                .andReturn();


//        var mvcResult = mockMvc.perform(post(URI)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonEntrada)).andReturn();

        System.out.println("JSON ENTRADA: " + jsonEntrada);

        mockMvc.perform(
                post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntrada)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        )
                .andDo(print());


    }


    private String mapearAJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    private <T> T mapearDesdeJson(String json, Class<T> myClase) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, myClase);
    }
}