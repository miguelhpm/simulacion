package org.miguelhpm.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

public class WiremockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);



    @Test
    public void exampleTest() {
        stubFor(post("/my/resource")
                .withHeader("Content-Type", containing("xml"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")));

        //Result result = myHttpServiceCallingObject.doSomething();
        //assertTrue(result.wasSuccessful());

        verify(postRequestedFor(urlPathEqualTo("/my/resource"))
                .withRequestBody(matching(".*message-1234.*"))
                .withHeader("Content-Type", equalTo("text/xml")));
    }

}
