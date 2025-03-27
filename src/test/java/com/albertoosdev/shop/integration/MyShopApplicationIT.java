package com.albertoosdev.shop.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyShopApplicationIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsPriceList_1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-14T10:00:00Z")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1));
	}

	@Test
	@DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsPriceList_2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-14T16:00:00Z")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(2));
	}

	@Test
	@DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsPriceList_3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-14T21:00:00Z")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1));
	}

	@Test
	@DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsPriceList_4() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-15T10:00:00Z")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(3));
	}

	@Test
	@DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsPriceList_5() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-16T21:00:00Z")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(4));
	}

	@Test
	@DisplayName("Test 6: petición a las 21:00 del día 16 del producto 99999 para la brand 1")
	void givenValidData_whenRequestGet_thenReturnsNotFoundError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-14T10:00:00Z")
						.queryParam("productId", "9999")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("NOT_FOUND"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").isNotEmpty());
	}

	@Test
	@DisplayName("Test 7: petición con fecha invalida")
	void givenInvalidDate_whenRequestGet_thenReturnsBadRequestError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
						.queryParam("applicationDate", "2020-06-14T10:00:00")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Test 8: petición sin parametros obligatorios")
	void givenInvalidDate_whenRequestGetWithoutRequiredParam_thenReturnsBadRequestError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/price")
//						.queryParam("applicationDate", "2020-06-14T10:00:00")
						.queryParam("productId", "35455")
						.queryParam("brandId", "1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

}
