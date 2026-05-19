package com.adobe.aem.guides.demo.core.servlets; 
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse; 
import org.apache.sling.api.servlets.SlingAllMethodsServlet; 
import org.osgi.service.component.annotations.Component; 
import org.osgi.framework.Constants; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonObject; 
import javax.jcr.Node; 
import javax.jcr.NodeIterator; 
import javax.jcr.RepositoryException; 
import javax.jcr.Session; 
import org.apache.sling.jcr.api.SlingRepository; 
import org.apache.sling.api.resource.ResourceResolver; 
import org.apache.sling.api.resource.ResourceResolverFactory; 
import org.apache.sling.api.resource.LoginException; 
import org.apache.sling.api.resource.Resource; 
import javax.servlet.Servlet; 
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.Map; 
@Component( 
		service = { Servlet.class }, 
		property = { 
				Constants.SERVICE_DESCRIPTION + "=Example Servlet", 
				"sling.servlet.paths=/bin/myServlet", 
				"sling.servlet.methods={GET, POST, DELETE}" 
		} 
		) 
public class MyServlet extends SlingAllMethodsServlet { 
	@org.osgi.service.component.annotations.Reference 
	private SlingRepository repository; 
	@org.osgi.service.component.annotations.Reference 
	private ResourceResolverFactory resolverFactory; 
	@Override 
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws IOException { 
		String componentPath = request.getParameter("path"); 
		ResourceResolver resourceResolver = null; 

		try { 
			// Get the resource resolver 
			Map<String, Object> param = new HashMap<>(); 
			param.put(ResourceResolverFactory.SUBSERVICE, "Dinesh"); 
			resourceResolver = resolverFactory.getServiceResourceResolver(param); 

			// Get the resource at the specified path 
			Resource resource = resourceResolver.getResource(componentPath + "/data"); 

			JsonArray dataArray = new JsonArray(); 

			if (resource != null) {  
				Iterator<Resource> children = resource.listChildren(); 
				while (children.hasNext()) { 
					Resource child = children.next(); 
					Node childNode = child.adaptTo(Node.class); 
					if (childNode != null) { 
						JsonObject dataObject = new JsonObject(); 
						dataObject.addProperty("name", childNode.getProperty("name").getString()); 
						dataObject.addProperty("email", childNode.getProperty("email").getString()); 
						dataObject.addProperty("subject", childNode.getProperty("subject").getString()); 
						dataObject.addProperty("message", 
								childNode.getProperty("message").getString()); 
						dataArray.add(dataObject); 
					} 
				} 
			} 

			JsonObject jsonResponse = new JsonObject(); 
			jsonResponse.add("data", dataArray); 

			response.setContentType("application/json"); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write(jsonResponse.toString()); 

		} catch (LoginException | RepositoryException e) { 
			logError(response, e); 
		} finally { 
			if (resourceResolver != null) { 
				resourceResolver.close(); 
			} 
		} 
	} 

	@Override 
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws IOException { 
		String rowIndex = request.getParameter("rowIndex"); 
		String delete = request.getParameter("delete"); 
		String componentPath = request.getParameter("componentPath"); 

		ResourceResolver resourceResolver = null; 
		Session session = null; 

		try { 
			Map<String, Object> param = new HashMap<>(); 
			param.put(ResourceResolverFactory.SUBSERVICE, "Dinesh"); 
			resourceResolver = resolverFactory.getServiceResourceResolver(param); 
			session = resourceResolver.adaptTo(Session.class); 

			Resource componentResource = resourceResolver.getResource(componentPath); 
			Node componentNode = componentResource.adaptTo(Node.class); 

			if (!componentNode.hasNode("data")) { 
				componentNode.addNode("data", "nt:unstructured"); 
			} 
			Node dataNode = componentNode.getNode("data"); 

			if (delete != null && delete.equals("true")) { 
				// Handle delete 
				if (dataNode.hasNode("item" + rowIndex)) { 
					Node itemNode = dataNode.getNode("item" + rowIndex); 
					itemNode.remove(); 
					session.save(); 
					reorderNodes(dataNode, session); 
					session.save(); 

					sendJsonResponse(response, "success", "Data deleted and nodes reordered successfully."); 
				} else { 
					sendJsonResponse(response, "error", "Node not found."); 
				} 
			} else { 
				// Handle create/update 
				String param1 = request.getParameter("param1"); 
				String param2 = request.getParameter("param2"); 
				String param3 = request.getParameter("param3"); 
				String param4 = request.getParameter("param4"); 

				Node itemNode; 
				if (rowIndex == null || rowIndex.isEmpty()) { 
					int itemIndex = 0; 
					while (dataNode.hasNode("item" + itemIndex)) { 
						itemIndex++; 
					} 
					itemNode = dataNode.addNode("item" + itemIndex, "nt:unstructured"); 
				} else { 
					itemNode = dataNode.getNode("item" + rowIndex); 
				} 

				itemNode.setProperty("name", param1); 
				itemNode.setProperty("email", param2); 
				itemNode.setProperty("subject", param3); 
				itemNode.setProperty("message", param4); 
				session.save(); 

				sendJsonResponse(response, "success", "Data stored successfully."); 
			} 
		} catch (LoginException | RepositoryException e) { 
			logError(response, e); 
		} finally { 
			if (session != null) { 
				session.logout(); 
			} 
			if (resourceResolver != null) { 
				resourceResolver.close(); 
			} 
		} 
	} 

	private void reorderNodes(Node dataNode, Session session) throws RepositoryException { 
		int itemIndex = 0; 
		for (NodeIterator nodeIterator = dataNode.getNodes(); nodeIterator.hasNext(); ) { 
			Node node = nodeIterator.nextNode(); 
			String newName = "item" + itemIndex; 
			if (!node.getName().equals(newName)) { 
				session.move(node.getPath(), dataNode.getPath() + "/" + newName); 
			} 
			itemIndex++; 
		} 
	} 

	private void sendJsonResponse(SlingHttpServletResponse response, String status, String 
			message) throws IOException { 
		JsonObject jsonResponse = new JsonObject(); 
		jsonResponse.addProperty("status", status); 
		jsonResponse.addProperty("message", message); 
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(jsonResponse.toString()); 
	} 
	private void logError(SlingHttpServletResponse response, Exception e) throws IOException 
	{ 
	 
	e.printStackTrace(); 
	JsonObject jsonResponse = new JsonObject(); 
	jsonResponse.addProperty("status", "error"); 
	jsonResponse.addProperty("message", e.getMessage()); 
	response.setContentType("application/json"); 
	response.setCharacterEncoding("UTF-8"); 
	response.getWriter().write(jsonResponse.toString()); 
}
}