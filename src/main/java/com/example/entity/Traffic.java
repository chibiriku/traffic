package com.example.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Data;

@Data
public class Traffic {
	private Long id;
	private Long userid;
	private Date useday;
	private String means;
	private String secter;
	private String road;
	private Integer cost;
	
	public void setUseday(Date useday) {
        this.useday = useday;
    }

    // usedayの変換メソッド
    public LocalDate getUsedayAsLocalDate() {
        return this.useday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setUsedayAsLocalDate(LocalDate localDate) {
        this.useday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}