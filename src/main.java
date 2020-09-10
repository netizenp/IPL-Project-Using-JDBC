import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) {
		matchesPlayedPerYear();
        matchesWonByAllTeamAllSeason();
        extraRunConcededPerTeam2016();
        economicalBowler2015();
	}
	private static void matchesPlayedPerYear(){
        System.out.println("Number of matches played per year of all the years in IPL: ");
        DBConn connection = new DBConn();
        ResultSet rs = connection.getExecuteQuery("select season, count(id) from matches group by season order by season;");
        try {
            while(rs.next()){
                System.out.println(rs.getString("season") + ": " + rs.getString("count(id)"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
    }
    private static void matchesWonByAllTeamAllSeason(){
        System.out.println("Number of matches won of all teams over all the years of IPL: ");
        DBConn connection = new DBConn();
        ResultSet rs = connection.getExecuteQuery("select distinct winner, count(winner) from matches group by winner;");
        try {
            while(rs.next()){
                System.out.println(rs.getString("winner") + ": " + rs.getString("count(winner)"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
    }
    private static void extraRunConcededPerTeam2016(){
        System.out.println("For the year 2016 get the extra runs conceded per team: ");
        DBConn connection = new DBConn();
        ResultSet rs = connection.getExecuteQuery("select distinct bowling_team, sum(extra_runs) from deliveries where match_id IN (select id from matches where season = 2016) group by bowling_team;");
        try {
            while(rs.next()){
                System.out.println(rs.getString(1) + ": " + rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
    }
    private static void economicalBowler2015(){
        System.out.println("For the year 2015 get the top economical bowlers: ");
        DBConn connection = new DBConn();
        ResultSet rs = connection.getExecuteQuery("select distinct bowler,((sum(total_runs) - (sum(bye_runs) + sum(legbye_runs)))/((sum(case when wide_runs = '0' and noball_runs = '0' then 1 else 0 END))/6)) as eco from deliveries where match_id IN (select id from matches where season = 2015) group by bowler order by eco limit 1;");
        try {
            while(rs.next()){
                System.out.println(rs.getString(1) + ": " + rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
    }
}