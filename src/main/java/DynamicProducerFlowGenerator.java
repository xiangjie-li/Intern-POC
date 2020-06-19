
import java.io.ByteArrayInputStream;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationBuilder;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.config.ConfigResource;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class DynamicProducerFlowGenerator {
	
	private HashMap<String,MuleContext> initialisedContexts;
	private String httpNamespace = "http://www.mulesoft.org/schema/mule/http";
	
	public DynamicProducerFlowGenerator() {
		initialisedContexts = new HashMap<String,MuleContext>();
	}
	
	

	public void initialise() {
		int i = 0;
		while(i < 1){
			i++;
			
			try {
				ArrayList<String> config = new ArrayList<String>();
				
//				String flowName = System.getProperty("partner-"+i+".flow-name");
				
				String flowName = "producerAPIFlow";
				
//				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//				docFactory.setNamespaceAware(true);
//				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
//				Document dynamicProducerFlowTemplate = docBuilder.parse(this.getClass().getClassLoader().getResourceAsStream("dynamic-producer-flow-template.xml"));
	
//				NodeList flow = dynamicProducerFlowTemplate.getElementsByTagName("flow");
//				NodeList httpRequester = dynamicProducerFlowTemplate.getElementsByTagNameNS(httpNamespace, "request");
//	
//				// change flow name 
//				flow.item(0).getAttributes().getNamedItem("name").setNodeValue( flowName );
//				
//				// change http Request configuration
				
//				config.add(getStringFromDoc(dynamicProducerFlowTemplate));
//				addFlow(config, flowName);				
				
				MuleContext muleContext = new DefaultMuleContextFactory().createMuleContext();

				ConfigurationBuilder builder = new SpringXmlConfigurationBuilder("dynamic-producer-flow-template.xml");
				builder.configure(muleContext);
				muleContext.start();

        
			}catch(Exception e) {
				e.printStackTrace(System.out);
			}
		}// end of while
	}
	
	
	public void dispose() {
		
	}
	
	private String getStringFromDoc(Document doc) throws TransformerException {

		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		writer.flush();
		return writer.toString();
	}

//	private boolean addFlow(ArrayList<String> configs, String flowName) {
//		boolean flag = false;
//
//		try {
//			// add to mule context
//			MuleContext muleContext = new DefaultMuleContextFactory().createMuleContext();
//			ConfigResource configResource[] = new ConfigResource[1];
//
//			int i = 0 ;
//			
//			for (String config : configs) {
//				configResource[i] = new ConfigResource(flowName, new ByteArrayInputStream(config.getBytes()) );
//				i++;
//			}
//			ConfigurationBuilder builder = new SpringXmlConfigurationBuilder(configResource);
//			builder.configure(muleContext);			
//			muleContext.start();
//
//			initialisedContexts.put(flowName, muleContext);
//
//			flag = true;
//
//		}  catch (MuleException me) {
//			me.printStackTrace(System.out);
//		} 
//		return flag;
//	}

}

