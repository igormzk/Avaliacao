package br.com.avaliacao.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Student;

/**
 * Classe de Data Access Object (objeto de acesso a dados).
 * Utilizar para realizar as operações no banco de dados.
 * @author Igor Muzeka
 * @version 1.0
 */
public class StudentDAO implements DAO<Student> {
	
	private Connection con;
	
	public StudentDAO() {
		con = ConnectionFactory.open();
	}

	@Override
	public int save(Student obj) {
		String sql = obj.getId() == null ? "INSERT INTO student (name) VALUES (?)" : "UPDATE student SET name = ? WHERE id = ?";
		int result = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getName());
			if (obj.getId() != null) {
				ps.setInt(2, obj.getId());
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
		String sql ="DELETE FROM student WHERE id = ?";
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
	public Student load(int id) {
		Student s = null;
		String sql = "SELECT * FROM student WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return s;
	}

	@Override
	public List<Student> load() {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM student ORDER BY name";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				list.add(s);
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
