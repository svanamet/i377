package test;

import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import test.Unit;
import ex3.AbstractDao;

public class Dao extends AbstractDao {

	public List<Unit> AddAllUnits() throws SQLException {
		List<Unit> units = new ArrayList<Unit>();
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM unit");
			while (rs.next()) {
				Unit unit = new Unit();
				unit.setId(rs.getInt(1));
				unit.setNimi(rs.getString(2));
				unit.setKood(rs.getString(3));
				units.add(unit);
			}

		} finally {
			closeResources();
		}

		return units;
	}

	public void DeleteUnit(int id) throws SQLException {
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery("DELETE FROM unit WHERE id=" + id);

		} finally {
			closeResources();
		}

	}

	public void DeleteAllUnits() {
		try {
			st = getConnection().createStatement();
			st.executeUpdate("TRUNCATE TABLE unit");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}

	}

	public void AddUnit(Unit unit) throws SQLException {
		try {

			PreparedStatement ps = null;

			String nimi = unit.nimi;
			String kood = unit.kood;

			st = getConnection().createStatement();
			st.executeQuery("INSERT INTO unit VALUES (NEXT VALUE FOR seq1, '"
					+ nimi + "', '" + kood + "')");

			// ps =
			// getConnection().prepareStatement("INSERT INTO unit VALUES (NEXT VALUE FOR seq1, ?, ?)");
			// ps.setString(1, nimi);
			// ps.setString(2, kood);
			//
			// rs = ps.executeQuery();

		} finally {
			closeResources();
		}

	}

	public List<Unit> Filter(String filter) {
		try {
			PreparedStatement ps = null;
			List<Unit> units = new ArrayList<Unit>();

			ps = getConnection().prepareStatement(
					"select * FROM unit WHERE UPPER(name) like ?");
			ps.setString(1, "%" + filter.toUpperCase() + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				Unit unit = new Unit();
				unit.setId(rs.getInt(1));
				unit.setNimi(rs.getString(2));
				unit.setKood(rs.getString(3));
				units.add(unit);
			}
			return units;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}

	}

}