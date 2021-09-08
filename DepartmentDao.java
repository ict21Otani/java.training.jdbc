package enshu6_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author NiB ict21otani
 * departmentテーブルにアクセスするDaoクラス
 */
public class DepartmentDao  extends DaoBase{

	/***
	 * departmentテーブル全取得メソッド
	 * @author NiB ict21otani
	 **/
	public List<Department> findAll(){

		/**戻り値用の変数定義　検索結果を突っ込んで返す*/
		List<Department> list =new ArrayList<>();
/*
		String url = "jdbc:postgresql://localhost/jdbc";//DBごとに違う　ベンダーのマニュアルを確認
		String user = "jdbc";
		String password = "jdbc";*/
		String sql ="select * from department;";
		try {
			//最近のJVMはビルドパスがとっていれば勝手に補完してくる
			//ただしjdbc読み込みエラーを感知するために記述する。
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//try-with-resouce

				try(Connection con = DriverManager.getConnection(url,user,password)){

					//コミットモードOFF
					con.setAutoCommit(false);

					//try-with-resouce
					try(PreparedStatement ps=con.prepareStatement(sql)){
					    //SQL実行部分---

						ResultSet rs=ps.executeQuery();


						Department dep;

						while(rs.next()) {
							dep=new Department();
							dep.setDept_id(rs.getInt("dept_id"));
							dep.setDept_name(rs.getString("dept_name"));
							list.add(dep);
						}


						//SQL実行部分----
						con.commit();

					}catch(SQLException e) {
						con.rollback();
						System.out.println("rollbackしました");
						e.printStackTrace();
						System.out.println("更新失敗");

					}//ここでpsをクローズ(自動)

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//throwで呼び出し元に投げてもいい。
					System.out.println("更新失敗");

				}//ここでclose（自動）

				System.out.println("レコード実行成功:");

				return list;
	}




}
