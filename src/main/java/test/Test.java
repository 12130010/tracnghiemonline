package test;

import java.io.IOException;
import java.util.Stack;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.client.RestTemplate;

import model.Account;

public class Test {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		RestTemplate res = new RestTemplate();
		Account ac = res.getForObject("http://127.0.0.1:8080/tracnghiem/login?username=username&password=password", Account.class);
		System.out.println(ac);
	}
}
