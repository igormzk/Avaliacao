package br.com.avaliacao.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Course;

/**
 * Classe de Data Access Object (objeto de acesso a dados).
 * Utilizar para realizar as operações no banco de dados.
 * @author Igor Muzeka
 * @version 1.0
 */
public class CourseDAO implements DAO<Course> {
	
	private Connection con;
	
	public CourseDAO() {
		con = ConnectionFactory.open();
	}

	@Override
	public int save(Course obj) {
		String sql = obj.getId() == null ? "INSERT INTO course (name, credit) VALUES (?, ?)" : "UPDATE course SET name = ?, credit = ? WHERE id = ?";
		int result = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getCredit());
			if (obj.getId() != null) {
				ps.setInt(3, obj.getId());
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return result;
	}

	@Override
	public int delete(int id) {
		String sql ="DELETE FROM course WHERE id = ?";
		int result = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return result;
		
	}

	@Override
	public Course load(int id) {
		Course c = null;
		String sql = "SELECT * FROM course WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Course();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setCredit(rs.getInt("credit"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return c;
	}

	@Override
	public List<Course> load() {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT * FROM course ORDER BY id";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course c = new Course();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setCredit(rs.getInt("credit"));
				list.add(c);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return list;
	}
}
