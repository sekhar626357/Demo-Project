package com.adobe.aem.guides.demo.core.servlets;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

//@Component(service = Servlet.class,immediate=true)
//@SlingServletPaths(value= {"/bin/ram/laksman","/bin/ram/laksman/kumar"})
//public class RecentArticleServlet extends SlingSafeMethodsServlet{
//	
//	@Override
//	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().write("Response From PathBased-Servlet-GET");
//		
//	}
//
//}


//@Component(service = Servlet.class,immediate=true)
//@SlingServletPaths(value= {"/Dinesh/ram/laksman","/bin/ram/laksman/kumar"})
//public class RecentArticleServlet extends SlingAllMethodsServlet{
//	
//	@Override
//	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().write("Responce From PathBased-Servlet-GET");
//		
//	}
//	
//	@Override
//	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().write("Responce From PathBased-Servlet-POST");
//	}
//	
//	@Override
//	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().write("Responce From PathBased-Servlet-PUT");
//	}
//	
//	@Override
//	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().write("Responce From PathBased-Servlet-DELETE");
//	}
//}
@Component(service = Servlet.class,immediate=true)
@SlingServletResourceTypes(resourceTypes= {"Demo/resource/recent-articles"},
							extensions= {"json","txt","html"},
							methods= {"GET","PUT","POST","DELETE"},
							selectors= {"s4","Sports"})
public class RecentArticleServlet extends SlingAllMethodsServlet{
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Responce From ResourceBased-Servlet-GET");
		
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Responce From ResourceBased-Servlet-POST");
	}
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Responce From ResourceBased-Servlet-PUT");
	}
	
	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Responce From ResourceBased-Servlet-DELETE");
	}

}
