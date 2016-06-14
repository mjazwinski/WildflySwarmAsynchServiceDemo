package pl.spiralarchitect.swarm;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/task-service")
@ApplicationScoped
public class SwarmService {
	
	@Inject
	private EisBusinessDelegate bDelegate;
	
	private ExecutorService mtf = null;
	
	@PostConstruct
	private void init() {
		 mtf = Executors.newFixedThreadPool(1);
	}
	
	@PreDestroy
	private void destroy() {
		mtf.shutdownNow();
	}
	
	@Path("/process-task")
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public void helloSwarm(@QueryParam("task-name") String taskName, @QueryParam("task-param") List<String> taskParams, @Suspended final AsyncResponse response) {
		response.setTimeout(15, TimeUnit.SECONDS);
		mtf.execute(new Runnable() {
			
			@Override
			public void run() {
				TaskRequest request = new TaskRequest();
				request.setTaskName(taskName);
				request.setTaskParameters(taskParams.toArray(new String[taskParams.size()]));
				response.resume(bDelegate.process(request));
			}
		});
	}
	
	@Path("/process-sync-task")
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String helloSwarm(@QueryParam("task-name") String taskName, @QueryParam("task-param") List<String> taskParams) {

		TaskRequest request = new TaskRequest();
		request.setTaskName(taskName);
		request.setTaskParameters(taskParams.toArray(new String[taskParams.size()]));
		return bDelegate.process(request);
	}
	
}
