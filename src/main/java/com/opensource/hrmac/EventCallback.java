package com.opensource.hrmac;

import com.microsoft.azure.iothub.IotHubStatusCode;
import com.microsoft.azure.iothub.IotHubEventCallback;

/**
 * Event Callback
 * @author hramc
 *
 */
public class EventCallback implements IotHubEventCallback
	 {
	   public void execute(IotHubStatusCode status, Object context) {
	     System.out.println("IoT Hub responded to message with status: " + status.name());

	     if (context != null) {
	       synchronized (context) {
	         context.notify();
	       }
	     }
	   }
}
