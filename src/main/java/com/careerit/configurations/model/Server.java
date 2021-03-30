package com.careerit.configurations.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Server {
	private String ip;
	private String username;
	private String password;
	private List<String> clients;
	public Server(String ip, String username, String password) {
		super();
		this.ip = ip;
		this.username = username;
		this.password = password;
	}
	
}
