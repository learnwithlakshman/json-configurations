package com.careerit.configurations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.careerit.configurations.dto.PlayerDTO;
import com.careerit.configurations.dto.RoleCountDTO;
import com.careerit.configurations.dto.TeamAmountByRoleDTO;
import com.careerit.configurations.dto.TeamAmountDTO;
import com.careerit.configurations.dto.TeamDTO;
import com.careerit.configurations.model.Player;
import com.careerit.configurations.model.Team;
import com.careerit.configurations.util.JsonReaderUtil;

public class TeamServiceImpl implements TeamService {

	private static final List<Team> teams;

	static {
		teams = JsonReaderUtil.getTeamsFromJSONFile();
	}

	@Override
	public List<String> getTeamLabels() {
		return teams.stream().map(t -> t.getLabel()).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDTO> getPlayerByTeam(String label) {
		Optional<Team> optTeam = teams.stream().filter(t -> t.getLabel().equals(label)).findFirst();
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		if (optTeam.isPresent()) {
			Team team = optTeam.get();
			list = team.getPlayers().stream().map(p -> new PlayerDTO(p.getName(), p.getRole(), p.getPrice()))
					.collect(Collectors.toList());
		}
		return list;
	}

	@Override
	public List<RoleCountDTO> getCountByRole(String label) {
		
		List<Player> players = teams.stream().filter(t->t.getLabel().equals(label)).map(t->t.getPlayers()).findFirst().get();
		Map<String, Long> roleCount = players.stream().collect(Collectors.groupingBy(Player::getRole,Collectors.counting()));
		return roleCount.entrySet().stream().map(e->new RoleCountDTO(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDTO> getPlayerByTeamAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamDTO> getAllTeamDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmountDTO> getAmountSpentByTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmountByRoleDTO> getAmountSpentByTeamAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> getPlayersBySort(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
