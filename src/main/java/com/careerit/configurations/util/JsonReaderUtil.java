package com.careerit.configurations.util;

import java.io.IOException;
import java.util.List;

import com.careerit.configurations.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonReaderUtil {

	private JsonReaderUtil() {

	}

	public static List<Team> getTeamsFromJSONFile() {
		String fileName = "/teams.json";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Team> teamList = null;
		try {
			teamList = objectMapper.readValue(JsonReaderUtil.class.getResourceAsStream(fileName),
					new TypeReference<List<Team>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teamList;
	}
}
