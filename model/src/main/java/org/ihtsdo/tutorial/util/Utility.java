package org.ihtsdo.tutorial.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * Utility class for object/string conversions.
 */
public class Utility {

  /**
   * Returns the graph for json.
   *
   * @param <T> the generic type
   * @param json the json
   * @param graphClass the graph class
   * @return the graph for json
   * @throws Exception the exception
   */
  public static <T> T getGraphForJson(String json, Class<T> graphClass)
    throws Exception {
    InputStream in =
        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector introspector =
        new JaxbAnnotationIntrospector(mapper.getTypeFactory());
    mapper.setAnnotationIntrospector(introspector);
    return mapper.readValue(in, graphClass);

  }

  /**
   * Returns the graph for json. sample usage:
   * 
   * <pre>
   *   List&lt;ConceptJpa&gt; list = ConfigUtility.getGraphForJson(str, new TypeReference&lt;List&lt;ConceptJpa&gt;&gt;{});
   * </pre>
   * @param <T> the
   * @param json the json
   * @param typeRef the type ref
   * @return the graph for json
   * @throws Exception the exception
   */
  public static <T> T getGraphForJson(String json, TypeReference<T> typeRef)
    throws Exception {
    InputStream in =
        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector introspector =
        new JaxbAnnotationIntrospector(mapper.getTypeFactory());
    mapper.setAnnotationIntrospector(introspector);
    return mapper.readValue(in, typeRef);

  }

  /**
   * Returns the json for graph.
   *
   * @param object the object
   * @return the json for graph
   * @throws Exception the exception
   */
  public static String getJsonForGraph(Object object) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector introspector =
        new JaxbAnnotationIntrospector(mapper.getTypeFactory());
    mapper.setAnnotationIntrospector(introspector);
    return mapper.writeValueAsString(object);
  }

  /**
   * Returns the XML string for for graph object.
   *
   * @param object the object
   * @return the string for for graph
   * @throws JAXBException the JAXB exception
   */
  public static String getStringForGraph(Object object) throws JAXBException {
    StringWriter writer = new StringWriter();
    JAXBContext jaxbContext = null;
    jaxbContext = JAXBContext.newInstance(object.getClass());
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.marshal(object, writer);
    return writer.toString();
  }

  /**
   * Returns the graph for string.
   *
   * @param <T> the generic type
   * @param xml the xml
   * @param graphClass the graph class
   * @return the graph for string
   * @throws JAXBException the JAXB exception
   */
  @SuppressWarnings("unchecked")
  public static <T> T getGraphForString(String xml, Class<T> graphClass)
    throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(graphClass);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    return (T) unmarshaller.unmarshal(new StreamSource(new StringReader(xml)));
  }
}
