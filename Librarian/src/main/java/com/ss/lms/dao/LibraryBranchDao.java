package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ss.lms.entity.LibraryBranch;

@Component
public class LibraryBranchDao extends DBConnection{
	private static LibraryBranchDao instance = null;

	private LibraryBranchDao() {
		// Exists only to defeat instantiation.
	}

	public static LibraryBranchDao getInstance() {
		if (instance == null) {
			instance = new LibraryBranchDao();
		}
		return instance;
	}
	
	public List<LibraryBranch> getAllLibraryBranch(){
		String query = "select * from tbl_library_branch";
		List<LibraryBranch> libraryBranchList = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
		
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()) {
				LibraryBranch libraryBranch = new LibraryBranch();
				libraryBranch.setBranchId(resultSet.getInt("branchId"));
				libraryBranch.setBranchName(resultSet.getString("branchName"));
				libraryBranch.setBranchAddress(resultSet.getString("branchAddress"));
				libraryBranchList.add(libraryBranch);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return libraryBranchList;
	}
	
	public boolean doesLibraryBranchExist(LibraryBranch libraryBranch) {
		String query = "select * from tbl_library_branch where branchId=?";
		boolean retBool=false;
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, libraryBranch.getBranchId());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getInt("branchId")!=0) {
					retBool=true;
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return retBool;
	}
	
	public void updateLibraryBranch(LibraryBranch libraryBranch) {
		String query = "update tbl_library_branch set branchName=?,branchAddress=?\n" + 
				" where branchId=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, libraryBranch.getBranchName());
			ps.setString(2, libraryBranch.getBranchAddress());
			ps.setInt(3, libraryBranch.getBranchId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LibraryBranch getLibraryBranchById(Integer id) {
		String query = "select * from tbl_library_branch where branchId=?";
		LibraryBranch libraryBranch = new LibraryBranch();
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				libraryBranch.setBranchId(resultSet.getInt("branchId"));
				libraryBranch.setBranchName(resultSet.getString("branchName"));
				libraryBranch.setBranchAddress(resultSet.getString("branchAddress"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return libraryBranch;
	}
}
