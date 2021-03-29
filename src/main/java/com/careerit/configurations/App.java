package com.careerit.configurations;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.careerit.configurations.dto.PlayerDTO;
import com.careerit.configurations.dto.RoleCountDTO;
import com.careerit.configurations.model.Player;
import com.careerit.configurations.model.Team;
import com.careerit.configurations.service.TeamService;
import com.careerit.configurations.service.TeamServiceImpl;
import com.careerit.configurations.util.JsonReaderUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// writeToJsonFile();
		// readFromJsonFile();
		
		// 1. Get labels 
		TeamService teamService = new TeamServiceImpl();
		System.out.println(teamService.getTeamLabels());
		
		//2. Get player of the given team
		String label = "CSK";
		List<PlayerDTO> players = teamService.getPlayerByTeam(label);
		System.out.println(players);
		
		//3. Get player count by role
		List<RoleCountDTO> roleCount = teamService.getCountByRole(label);
		System.out.println(roleCount);
		
	}

	private static void readFromJsonFile() {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Player> players = objectMapper.readValue(new File("players.json"), new TypeReference<List<Player>>() {
			});
			System.out.println(players);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void writeToJsonFile() {
		Player player1 = new Player("MS Dhoni", "Wicket Keeper", 18_00_00_000);
		Player player2 = new Player("KL Rahul", "Wicket Keeper", 15_00_00_000);

		List<Player> list = Stream.of(player1, player2).collect(Collectors.toList());

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("players.json"), list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
