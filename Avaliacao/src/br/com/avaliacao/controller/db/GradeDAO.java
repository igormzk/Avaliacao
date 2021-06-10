package br.com.avaliacao.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Course;
import br.com.avaliacao.model.Grade;
import br.com.avaliacao.model.Student;

/**
 * Classe de Data Access Object (objeto de acesso a dados).
 * Utilizar para realizar as operações no banco de dados.
 * @author Igor Muzeka
 * @version 1.0
 */
public class GradeDAO implements DAO<Grade> {

	private Connection con;
	
	public GradeDAO() {
		con = ConnectionFactory.open();
	}

	@Override
	public int save(Grade obj) {
		String sql = obj.getId() == null ? "INSERT INTO grade (id_student, grade_1, grade_2, grade_3, grade_4, id_course) VALUES (?, ?, ?, ?, ?, ?)" : "UPDATE grade SET id_student = ?, grade_1 = ?, grade_2 = ?, grade_3 = ?, grade_4 = ?, id_course = ? WHERE id = ?";
		int result = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getStudent().getId());
			ps.setBigDecimal(2, obj.getGrade1());
			ps.setBigDecimal(3, obj.getGrade2());
			ps.setBigDecimal(4, obj.getGrade3());
			ps.setBigDecimal(5, obj.getGrade4());
			ps.setInt(6, obj.getCourse().getId());
			if (obj.getId() != null) {
				ps.setInt(7, obj.getId());
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
		String sql ="DELETE FROM grade WHERE id = ?";
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
	public Grade load(int id) {
		Grade g = null;
		String sql = "SELECT g.*, s.name name_student, c.name name_course, c.credit FROM student s INNER JOIN grade g ON s.id = g.id_student LEFT JOIN course c ON c.id = g.id_course WHERE g.id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				g = new Grade();
				g.setId(rs.getInt("id"));
				g.setGrade1(rs.getBigDecimal("grade_1"));
				g.setGrade2(rs.getBigDecimal("grade_2"));
				g.setGrade3(rs.getBigDecimal("grade_3"));
				g.setGrade4(rs.getBigDecimal("grade_4"));
				Student s = new Student();
				s.setId(rs.getInt("s.id_student"));
				s.setName(rs.getString("name_student"));
				g.setStudent(s);
				Course c = new Course();
				c.setId(rs.getInt("id_course"));
				c.setName(rs.getString("name_course"));
				c.setCredit(rs.getInt("credit"));
				g.setCourse(c);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return g;
	}

	@Override
	public List<Grade> load() {
		List<Grade> list = new ArrayList<Grade>();
		String sql = "SELECT g.*, s.name name_student, c.name name_course, c.credit FROM student s INNER JOIN grade g ON s.id = g.id_student LEFT JOIN course c ON c.id = g.id_course";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Grade g = new Grade();
				g.setId(rs.getInt("id"));
				g.setGrade1(rs.getBigDecimal("grade_1"));
				g.setGrade2(rs.getBigDecimal("grade_2"));
				g.setGrade3(rs.getBigDecimal("grade_3"));
				g.setGrade4(rs.getBigDecimal("grade_4"));
				Student s = new Student();
				s.setId(rs.getInt("id_student"));
				s.setName(rs.getString("name_student"));
				g.setStudent(s);
				Course c = new Course();
				c.setId(rs.getInt("id_course"));
				c.setName(rs.getString("name_course"));
				c.setCredit(rs.getInt("credit"));
				g.setCourse(c);
				list.add(g);
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
