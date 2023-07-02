package sprot.radar.model;

import lombok.Data;
import sprot.radar.util.AppUtil;
import java.time.LocalDateTime;


@Data
public class Match {

    private String id;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDateTime dateStart;

    private AppUtil appUtil = new AppUtil();

    public Match(String homeTeam, String awayTeam) {
        this.id = appUtil.generateId();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.dateStart = LocalDateTime.now();
    }

}
