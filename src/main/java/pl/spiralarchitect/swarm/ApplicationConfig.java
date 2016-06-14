package pl.spiralarchitect.swarm;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/swarm")
public class ApplicationConfig extends Application {
	
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(SwarmService.class);
        return resources;
    }
}
