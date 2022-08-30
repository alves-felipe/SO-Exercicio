package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	private String os() {
		return System.getProperty("os.name").split(" ")[0];
	}
	
	public void ip() {
		if(os().equals("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ifconfig");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("flags")) {
						System.out.println(line);
					}
					if(line.contains("inet") && !line.contains("inet6")) {
						System.out.println(line.split(" netmask")[0]);
					}
					line = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(os().equals("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ipconfig");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("Adaptador")) {
						System.out.println(line);
					}
					if(line.contains("IPv4")) {
						System.out.println(line);
					}
					line = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ping() {
		String command = os().equals("Linux") ? "ping -4 -c 10 www.google.com.br" : "PING -4 -n 10 www.google.com.br";
		
		try {
			Process p = Runtime.getRuntime().exec(command);
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			
			while(line != null) {
				if(line.contains("time ")) {
					System.out.println(line.split("time ")[1]);
				}
				
				if(line.contains("Média ")) {
					System.out.println(line.split("Média = ")[1]);
				}
				line = buffer.readLine();
			}
			
			buffer.close();
			reader.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
