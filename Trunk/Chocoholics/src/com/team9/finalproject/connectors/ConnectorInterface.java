package com.team9.finalproject.connectors;

import com.team9.finalproject.DataManager;

/**
 * The interface used for connecting to the data manager.
 * @author William Hampton
 */
public interface ConnectorInterface {
	public DataManager loadDataManager();
	public String commandLoop();
	public String quit();
	public String display(String output);
}
