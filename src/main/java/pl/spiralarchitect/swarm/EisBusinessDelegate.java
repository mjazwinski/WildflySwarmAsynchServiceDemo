package pl.spiralarchitect.swarm;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EisBusinessDelegate {

	public String process(TaskRequest request) {
		try {
			TimeUnit.SECONDS.sleep(5);
			return "Done processing task " + request.getTaskName();
		} catch (InterruptedException e) {
			return "Failed to process " + request.getTaskName();
		}
	}
	
}
