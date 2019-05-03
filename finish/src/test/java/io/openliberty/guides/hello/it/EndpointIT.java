// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.hello.it;
// tag::import[]
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
// end::import[]
//tag::endpointit[]
public class EndpointIT {
    private static String URL;

    @BeforeClass
    // tag::init[]
    public static void init() {
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        URL = "http://localhost:" + port + "/" + war + "/" + "servlet";
    }
    // end::init[]
    // tag::test[]
    @Test
    // end::test[]
    public void testServlet() throws Exception {
        HttpClient httpClient = new HttpClient();
        GetMethod httpGetMethod = new GetMethod(URL);
        // tag::try[]
        try {
            int actualStatusCode = httpClient.executeMethod(httpGetMethod);
            int expectedStatusCode = HttpStatus.SC_OK;
            assertEquals("HTTP GET failed", expectedStatusCode, actualStatusCode);
            String response = httpGetMethod.getResponseBodyAsString(1000);
            assertTrue("Unexpected response body", 
                response.contains("Hello! Is Gradle working for you?"));
        } finally {
            httpGetMethod.releaseConnection();
        }
        // end::try[]
    }
}
//end::endpointit[]
