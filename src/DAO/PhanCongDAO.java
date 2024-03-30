package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.PersonDTO;
import DTO.PhanCongDTO;
import DTO.courseDTO;
import DAO.PhanCongIDAO;

//SELECT c.*, p.*
//FROM courseinstructor ci
//JOIN course c ON ci.courseID = c.courseID
//JOIN person p ON ci.personID = p.personID;

//SELECT * FROM course;
//
//tăng dần theo id person
//SELECT c.*, p.*
//FROM courseinstructor ci
//JOIN course c ON ci.courseID = c.courseID
//JOIN person p ON ci.personID = p.personID
//ORDER BY p.PersonID ASC;
//
//tăng dần theo course Title
//SELECT c.*, p.*
//FROM courseinstructor ci
//JOIN course c ON ci.courseID = c.courseID
//JOIN person p ON ci.personID = p.personID
//ORDER BY c.Title ASC;

public class PhanCongDAO implements PhanCongIDAO {

	@Override
	public List<PhanCongDTO> getAllPhanCong() {
		List<PhanCongDTO> listPhanCongDTOs = new ArrayList<PhanCongDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM courseinstructor";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				PhanCongDTO pDto = new PhanCongDTO(courseID, personID);
				listPhanCongDTOs.add(pDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return listPhanCongDTOs;
	}
	@Override
	public List<PhanCongDTO> getAllPhanCongAfterSortingByPersonName() {
		List<PhanCongDTO> listPhanCongDTOs = new ArrayList<PhanCongDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT c.*, p.* FROM courseinstructor ci JOIN course c ON ci.courseID = c.courseID JOIN person p ON ci.personID = p.personID WHERE p.HireDate IS NOT NULL ORDER BY p.LastName ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				PhanCongDTO pDto = new PhanCongDTO(courseID, personID);
				listPhanCongDTOs.add(pDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return listPhanCongDTOs;
	}

	@Override
	public List<PhanCongDTO> getAllPhanCongAfterSortingByPersonID() {
		List<PhanCongDTO> listPhanCongDTOs = new ArrayList<PhanCongDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT c.*, p.* FROM courseinstructor ci JOIN course c ON ci.courseID = c.courseID JOIN person p ON ci.personID = p.personID WHERE p.HireDate IS NOT NULL ORDER BY p.PersonID ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				PhanCongDTO pDto = new PhanCongDTO(courseID, personID);
				listPhanCongDTOs.add(pDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return listPhanCongDTOs;
	}

	@Override
	public List<PhanCongDTO> getAllPhanCongAfterSortingByCourseTitle() {
		List<PhanCongDTO> listPhanCongDTOs = new ArrayList<PhanCongDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT c.*, p.* FROM courseinstructor ci JOIN course c ON ci.courseID = c.courseID JOIN person p ON ci.personID = p.personID WHERE p.HireDate IS NOT NULL ORDER BY c.Title ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				PhanCongDTO pDto = new PhanCongDTO(courseID, personID);
				listPhanCongDTOs.add(pDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return listPhanCongDTOs;
	}

	@Override
	public List<PhanCongDTO> getAllPhanCongAfterSortingByCourseID() {
		List<PhanCongDTO> listPhanCongDTOs = new ArrayList<PhanCongDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT c.*, p.* FROM courseinstructor ci JOIN course c ON ci.courseID = c.courseID JOIN person p ON ci.personID = p.personID WHERE p.HireDate IS NOT NULL ORDER BY c.CourseID ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				PhanCongDTO pDto = new PhanCongDTO(courseID, personID);
				listPhanCongDTOs.add(pDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return listPhanCongDTOs;
	}

	@Override
	public boolean addPhanCong(PhanCongDTO pDto) {
		Connection connection = null;

		try {
			connection = ConnectDB.getConnection();
			String sql = "INSERT INTO courseinstructor(CourseID, PersonID) VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, pDto.getCourseId());
			preparedStatement.setInt(2, pDto.getPersonId());

			int rowsAffected = preparedStatement.executeUpdate();

			if(rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return false;
	}

	@Override
	public boolean editPhanCong(PhanCongDTO newPDto, PhanCongDTO oldPDto) {
		Connection connection = null;

		try {
			connection = ConnectDB.getConnection();
			String sql = "UPDATE courseinstructor SET CourseID = ?, PersonID = ? WHERE CourseID = ? AND PersonID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, newPDto.getCourseId());
			preparedStatement.setInt(2, newPDto.getPersonId());

			preparedStatement.setInt(3, oldPDto.getCourseId());
			preparedStatement.setInt(4, oldPDto.getPersonId());

			int rowsAffected = preparedStatement.executeUpdate();

			if(rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectDB.closeConnection(connection);
		}
		return false;
	}

	@Override
	public boolean deletePhanCong(int pID, int cID) {
		Connection connection = null;

		try {
			connection = ConnectDB.getConnection();
			String sql = "DELETE FROM courseinstructor WHERE PersonID = ? and CourseID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, pID);
			preparedStatement.setInt(2, cID);

			int rowsAffected = preparedStatement.executeUpdate();

			if(rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return false;
	}

	@Override
	public boolean hasPhanCong(int pID, int cID) {
		PhanCongDTO pDto = null;

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM courseinstructor WHERE PersonID = ? and CourseID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setInt(1, pID);
			preparedStatement.setInt(2, cID);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int personID = resultSet.getInt("PersonID");
				System.out.println("CourseID: " + courseID + ", PersonID: " + personID);

				pDto = new PhanCongDTO(courseID, personID);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return pDto != null;
	}

	//---------------------------------------------------------
	//	Bổ sung thêm để lấy tên couse với tên giảng viên
	public static List<PersonDTO> getAllPerson() {
		List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM person WHERE HireDate IS NOT NULL";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int personID = resultSet.getInt("PersonID");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				Date hireDate = resultSet.getDate("HireDate");
				Date enrollmentDate = resultSet.getDate("EnrollmentDate");
				System.out.println("personID: " + personID + ", lastName: " + lastName + ", firstName: " + firstName + ", hireDate: " + hireDate + ", enrollmentDate: " + enrollmentDate);

				PersonDTO newPs = new PersonDTO(personID, lastName, firstName, hireDate, enrollmentDate);
				personDTOList.add(newPs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return personDTOList;
	}
	public static PersonDTO getPersonByID(int id) {
		PersonDTO pDto = null;

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM person WHERE PersonID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int personID = resultSet.getInt("PersonID");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				Date hireDate = resultSet.getDate("HireDate");
				Date enrollmentDate = resultSet.getDate("EnrollmentDate");
				System.out.println("personID: " + personID + ", lastName: " + lastName + ", firstName: " + firstName + ", hireDate: " + hireDate + ", enrollmentDate: " + enrollmentDate);

				pDto = new PersonDTO(personID, lastName, firstName, hireDate, enrollmentDate);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return pDto;
	}
	public static List<courseDTO> getAllCourse() {
		List<courseDTO> courseDTOLst = new ArrayList<courseDTO>();

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM course";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				String titleString = resultSet.getString("Title");
				int credits = resultSet.getInt("Credits");
				int departmentID = resultSet.getInt("DepartmentID");
				System.out.println("Id: " + courseID + ", titleString: " + titleString + ", credits: " + credits + ", departmentID: " + departmentID);

				courseDTO cDto = new courseDTO(courseID, titleString, credits, departmentID);
				courseDTOLst.add(cDto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return courseDTOLst;
	}
	public static courseDTO getCourseByID(int id) {
		courseDTO cDto = null;

		Connection connection = ConnectDB.getConnection();
		String sqlQueryString = "SELECT * FROM course WHERE CourseID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				String titleString = resultSet.getString("Title");
				int credits = resultSet.getInt("Credits");
				int departmentID = resultSet.getInt("DepartmentID");
				System.out.println("Id: " + courseID + ", titleString: " + titleString + ", credits: " + credits + ", departmentID: " + departmentID);

				cDto = new courseDTO(courseID, titleString, credits, departmentID);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return cDto;
	}
}
