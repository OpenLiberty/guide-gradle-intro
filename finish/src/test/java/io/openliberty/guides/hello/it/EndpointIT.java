// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.hello.it;
// tag::import[]

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
// end::import[]

// tag::endpointit[]
public class EndpointIT {
    private static String webURL;

    @BeforeAll
    // tag::init[]
    public static void init() {
        String port = System.getProperty("http.port");
        String context = System.getProperty("context.root");
        webURL = "http://localhost:" + port + "/" + context + "/" + "servlet";
        System.out.println("URL: " + webURL);
    }
    // end::init[]

    // tag::test[]
    @Test
    // end::test[]
    public void testServlet() throws Exception {
        HttpClient httpClient = new HttpClient();
        GetMethod httpGetMethod = new GetMethod(webURL);
        // tag::try[]
        try {
            int actualStatusCode = httpClient.executeMethod(httpGetMethod);
            int expectedStatusCode = HttpStatus.SC_OK;
            assertEquals(expectedStatusCode, actualStatusCode, "HTTP GET failed");
            String response = httpGetMethod.getResponseBodyAsString(1000);
            assertTrue(response.contains("Hello! Is Gradle working for you?"),
                    "Unexpected response body");
        } finally {
            httpGetMethod.releaseConnection();
        }
        // end::try[]
    }
}
//end::endpointit[]
