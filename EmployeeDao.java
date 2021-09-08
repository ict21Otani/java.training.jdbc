/**
 *
 */
package enshu6_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NiB ict21otani
 * EmplyeeテーブルのDAOクラス
 * データベースアクセス
 *
 */
public class EmployeeDao  extends DaoBase{

	/**
	 * @return 従業員データ全部 List<Employee> 形式
	 * Employeeの情報を全取得
	 * 部署名もDepartmentからとってくる
	 * */
	public List<Employee> findAll(){

		//接続情報
		/*String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";
		 */
		String sql ="select emp_id,Emp_name,e.dept_id,registered_date,dept_name " +
				"from Employee e inner join department d on e.dept_id=d.dept_id order by emp_id;";

		//戻り値用リスト
		List<Employee> list=new ArrayList<>();

		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//コネクション接続
		try(Connection con = DriverManager.getConnection(url,user,password)){

			//オートコミットモードOFF
			con.setAutoCommit(false);

			//ステートメント定義
			try(PreparedStatement ps=con.prepareStatement(sql)){
			    //SQL実行部分---


				//SQL実行　結果取得
				ResultSet rs=ps.executeQuery();

				/*戻り値　詰め込むようの変数*/
				Employee emp;
				Department dep;

				//取得結果→戻り値リスト
				while(rs.next()) {
					/*社員(Employee)データセット*/
					emp=new Employee();
					emp.setEmp_id(rs.getInt("emp_id"));
					emp.setEmp_name(rs.getString("emp_name"));
					emp.setDept_id(rs.getInt("dept_id"));
					emp.setRegistered_date(rs.getTimestamp("registered_date"));

					/*部署データのセット*/
					dep=new  Department();//値セット用のDepartmentクラスインスタンス
					dep.setDept_id(emp.getDept_id());//テーブル結合のキーなので同じ値
					dep.setDept_name(rs.getString("dept_name"));

					/*部署データのセット---*/

					//Departmentクラスのインスタンスをセット
					emp.setDeprtment(dep);
					/*社員(Employee)データセット--*/

					list.add(emp);
				}


				//SQL実行部分----

				//コミット処理
				con.commit();

			}catch(SQLException e) {
				con.rollback();
				System.out.println("rollbackしました");
				e.printStackTrace();
				System.out.println("更新失敗");

			}//ここでpsをクローズ(自動)

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失敗");

		}//ここでclose（自動）

		System.out.println("レコード実行成功:");

		return list;

	}


	/**
	 * @return IDに紐づいた従業員情報
	 * @param id 検索するid
	 * Employeeの情報を全取得
	 * 部署名もDepartmentからとってくる
	 * */
	public Employee findById(int id){

		//接続情報
		String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";

		String sql ="select emp_id,Emp_name,e.dept_id,registered_date,dept_name\r\n" +
				"from Employee e inner join department d on  e.dept_id=d.dept_id  where e.emp_id=? order by emp_id;";

		//戻り値用
		Employee emp =new Employee();

		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//コネクション接続
		try(Connection con = DriverManager.getConnection(url,user,password)){

			//オートコミットモードOFF
			con.setAutoCommit(false);

			//ステートメント定義
			try(PreparedStatement ps=con.prepareStatement(sql)){
			    //SQL実行部分---

				//プレースホルダーに値を挿入
				ps.setInt(1, id);
				//SQL実行　結果取得
				ResultSet rs=ps.executeQuery();


				//取得結果
				while(rs.next()) {
					/*社員(Employee)データセット*/
					emp.setEmp_id(rs.getInt("emp_id"));
					emp.setEmp_name(rs.getString("emp_name"));
					emp.setDept_id(rs.getInt("dept_id"));
					emp.setRegistered_date(rs.getTimestamp("registered_date"));

					/*部署データのセット*/
					Department dep=new  Department();//値セット用のDepartmentクラスインスタンス
					dep.setDept_id(emp.getDept_id());//テーブル結合のキーなので同じ値
					dep.setDept_name(rs.getString("dept_name"));

					/*部署データのセット---*/

					//Departmentクラスのインスタンスをセット
					emp.setDeprtment(dep);
					/*社員(Employee)データセット--*/

				}


				//SQL実行部分----

				//コミット処理
				con.commit();

			}catch(SQLException e) {
				con.rollback();
				System.out.println("rollbackしました");
				e.printStackTrace();
				System.out.println("更新失敗");

			}//ここでpsをクローズ(自動)

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失敗");

		}//ここでclose（自動）

		System.out.println("レコード実行成功:");

		return emp;



	}


	/**
	 * @return 更新件数
	 * @param  List<Employee> インサートする従業員の情報
	 * Employeeの情報を登録
	 * */
	public int insert( List<Employee> list){

		//接続情報
		String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";
		//insert into EMPLOYEE values(1009,'福島',2,now()); SQLサンプル
		String sql ="insert into EMPLOYEE values(?,?,?,now());";

		int cnt =0;//件数取得用

		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//コネクション接続
		try(Connection con = DriverManager.getConnection(url,user,password)){

			//オートコミットモードOFF
			con.setAutoCommit(false);

			//ステートメント定義
			try(PreparedStatement ps=con.prepareStatement(sql)){
			    //SQL実行部分---

				for(Employee empl:list) {
				//プレースホルダーに値を挿入(from引数のリスト)
					ps.setInt(1, empl.getEmp_id());
					ps.setString(2, empl.getEmp_name());
					ps.setInt(3, empl.getDept_id());
					//SQL実行　結果取得
					cnt+=ps.executeUpdate();
				}

				//SQL実行部分----

				//コミット処理
				con.commit();

			}catch(SQLException e) {
				con.rollback();
				System.out.println("rollbackしました");
				e.printStackTrace();
				System.out.println("更新失敗");

			}//ここでpsをクローズ(自動)

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失敗");

		}//ここでclose（自動）

		System.out.println("レコード実行成功:"+cnt);

		return cnt;



	}

	/**
	 * @return 更新件数
	 * @param  List<Employee> アップデートする従業員の情報
	 * Employeeの情報を更新　キー emp_id 更新対象　名前,部署
	 * */
	public int update( List<Employee> list){

		//接続情報
		String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";
		String sql ="update Employee set emp_name=?,dept_id=? where emp_id=?;";

		int cnt =0;//件数取得用

		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//コネクション接続
		try(Connection con = DriverManager.getConnection(url,user,password)){

			//オートコミットモードOFF
			con.setAutoCommit(false);

			//ステートメント定義
			try(PreparedStatement ps=con.prepareStatement(sql)){
			    //SQL実行部分---

				for(Employee empl:list) {
				//プレースホルダーに値を挿入(from引数のリスト)
					ps.setString(1, empl.getEmp_name());
					ps.setInt(2, empl.getDept_id());
					ps.setInt(3, empl.getEmp_id());

					//SQL実行　結果取得
					cnt+=ps.executeUpdate();
				}

				//SQL実行部分----

				//コミット処理
				con.commit();

			}catch(SQLException e) {
				con.rollback();
				System.out.println("更新失敗");
				System.out.println("rollbackしました");
				e.printStackTrace();


			}//ここでpsをクローズ(自動)

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失敗");

		}//ここでclose（自動）

		System.out.println("レコード実行成功:"+cnt);

		return cnt;



	}


	/**
	 * @return 削除件数
	 * @param  List<Employee> 削除する従業員の情報
	 * Employeeの情報を削除　キー emp_id
	 * */
	public int delete( List<Employee> list){

		//接続情報
		String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";
		String sql ="\r\n" +
				"delete from EMPLOYEE where emp_id=?;";

		int cnt =0;//件数取得用

		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//コネクション接続
		try(Connection con = DriverManager.getConnection(url,user,password)){

			//オートコミットモードOFF
			con.setAutoCommit(false);

			//ステートメント定義
			try(PreparedStatement ps=con.prepareStatement(sql)){
			    //SQL実行部分---

				for(Employee empl:list) {
				//プレースホルダーに値を挿入(from引数のリスト)

					ps.setInt(1, empl.getEmp_id());

					//SQL実行　結果取得
					cnt+=ps.executeUpdate();
				}

				//SQL実行部分----

				//コミット処理
				con.commit();

			}catch(SQLException e) {
				con.rollback();
				System.out.println("更新失敗");
				System.out.println("rollbackしました");
				e.printStackTrace();


			}//ここでpsをクローズ(自動)

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失敗");

		}//ここでclose（自動）

		System.out.println("レコード実行成功:"+cnt);

		return cnt;

	}



}
