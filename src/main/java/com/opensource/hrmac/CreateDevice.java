package com.opensource.hrmac;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.RegistryManager;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

/**
 * 
 * @author hramc
 * 
 * Code to generate IOT devices in IOT Hub
 * Update connection string and deviceId
 *
 */
public class CreateDevice {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws NoSuchAlgorithmException
	 * 
	 * Code the create the unique IOT devices in the IOT Hub
	 */
	public static void main(String[] args) throws IOException, IllegalArgumentException, NoSuchAlgorithmException {
		
		// Registry Manager to manage the devices
		RegistryManager registryManager = RegistryManager.createFromConnectionString(Constants.connectionString);

		// Create a device that's enabled by default, 
		// with an autogenerated key.
		for(int i=1;i<=Constants.noOfDevice;i++) {
			Device device = Device.createFromId(Constants.deviceIdPrefix+i, null, null);
			try {
			  device = registryManager.addDevice(device);
			} catch (IotHubException iote) {
			  // If the device already exists.
			  try {
			    device = registryManager.getDevice(device.getDeviceId());
			  } catch (IotHubException iotf) {
			    iotf.printStackTrace();
			  }
			}
	
			// Display information about the
			// device you created.
			System.out.println("Device "+device.getDeviceId()+" is created successfully and key is "+device.getPrimaryKey());
			
		}
	}

}
