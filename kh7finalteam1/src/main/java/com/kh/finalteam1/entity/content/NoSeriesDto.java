package com.kh.finalteam1.entity.content;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NoSeriesDto {
	private int contentNo;
	private int contentPlaytime;
	private String seriesPath;

}
