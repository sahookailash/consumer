package spring.cloud.contract.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SpringCloudContractConsumerApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = {"contractproducer:spring-cloud-contract-producer:+:stubs:8090"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class SpringCloudContractConsumerApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/calculate?number=2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Even"));
	}

	@Test
	public void given_WhenPassOddNumberInQueryParam_ThenReturnOdd() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/calculate?number=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Odd"));
	}

	@Test
	void contextLoads() {
	}

}
