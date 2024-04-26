package Interface;

import java.util.Map;

public class ReactorType {

	Map<String, Reactor> applications;

	public Map<String, Reactor> getApplications() {
		return applications;
	}

	public void setApplications(Map<String, Reactor> applications) {
		this.applications = applications;
	}

	@Override public String toString() {
		return "YamlConfig{" +
				"applications=" + applications +
				'}';
	}
}