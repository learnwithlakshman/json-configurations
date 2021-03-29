package com.careerit.configurations.service;

import java.util.List;
import java.util.Map;

import com.careerit.configurations.dto.PlayerDTO;
import com.careerit.configurations.dto.RoleCountDTO;
import com.careerit.configurations.dto.TeamAmountByRoleDTO;
import com.careerit.configurations.dto.TeamAmountDTO;
import com.careerit.configurations.dto.TeamDTO;

public interface TeamService {

	public List<String> getTeamLabels();
	public List<PlayerDTO> getPlayerByTeam(String label);	
	public List<RoleCountDTO> getCountByRole(String label);
	public List<PlayerDTO> getPlayerByTeamAndRole(String label, String role);
	public List<TeamDTO> getAllTeamDetails();
	public List<TeamAmountDTO> getAmountSpentByTeams();
	public List<TeamAmountByRoleDTO> getAmountSpentByTeamAndRole(String label,String role);
	public List<PlayerDTO> getPlayersBySort(String fieldName);
	public Map<String,List<PlayerDTO>> getMaxPaidPlayersByRole();
}
