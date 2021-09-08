/**
 *
 */
package enshu6_2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NiB ict21otani
 *
 * EmployeeDao呼び出しクラス
 *
 */
public class EmployeeClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeDao dao = new EmployeeDao();

		/*全検索*/
		System.out.println("全検索");
		List<Employee> list = dao.findAll();

		for (Employee emp : list) {
			System.out.print(":" + emp.getEmp_id());
			System.out.print(":" + emp.getEmp_name());
			System.out.print(":" + emp.getDept_id());
			System.out.println(":" + emp.getRegistered_date());

			//Employeeの中のDepartmentの中の部署名取得
			System.out.println(":" + emp.getDeprtment().getDept_name());

		}

		/*ID検索*/
		System.out.println("\n\n\nID検索");
		//検索メソッド呼び出し
		Employee emp = dao.findById(1001);

		System.out.print(":" + emp.getEmp_id());
		System.out.print(":" + emp.getEmp_name());
		System.out.print(":" + emp.getDept_id());
		System.out.println(":" + emp.getRegistered_date());

		//Employeeの中のDepartmentの中の部署名取得
		System.out.println(":" + emp.getDeprtment().getDept_name());

		/*削除*/
		System.out.println("\n\n\nデリートします。");

		/*削除データ用リストの生成---s*/
		//引数用リスト
		List<Employee> deleteList = new ArrayList<>();
		//一人目
		Employee tmp = new Employee();
		tmp.setEmp_id(1020);
		deleteList.add(tmp);

		//二人目
		tmp = new Employee();
		tmp.setEmp_id(1021);
		deleteList.add(tmp);
		/*削除データ用リストの生成---e*/


		System.out.println("削除するデータを表示します.");
		//削除前確認検索検索byID呼び出し
		for (Employee empl :deleteList) {
			//削除対象表示処理
			Employee employee = dao.findById(empl.getEmp_id());
			System.out.print(":" + employee.getEmp_id());
			System.out.print(":" + employee.getEmp_name());
			System.out.print(":" + employee.getDept_id());
			System.out.println(":" + employee.getRegistered_date());

			//Employeeの中のDepartmentの中の部署名取得
			System.out.println(":" + employee.getDeprtment().getDept_name());
		}

		//削除処理呼び出し
		int cnt = dao.delete(deleteList);

		System.out.println("削除件数:" + cnt);



		/*インサート*/
		System.out.println("\n\n\nインサートします。");

		/**入力データ用リストの生成*/

		//引数用リスト
		List<Employee> insertList = new ArrayList<>();
		//一人目
		tmp = new Employee();
		tmp.setEmp_id(1020);
		tmp.setEmp_name("日本");
		tmp.setDept_id(3);
		insertList.add(tmp);

		//二人目
		tmp = new Employee();
		tmp.setEmp_id(1021);
		tmp.setEmp_name("中国");
		tmp.setDept_id(2);
		insertList.add(tmp);
		 cnt = dao.insert(insertList);
		System.out.println("更新件数:" + cnt);

		System.out.println("確認用表示");
		//確認用検索byID呼び出し　更新に使ったリスト使用
		for (Employee empl : insertList) {
			Employee employee = dao.findById(empl.getEmp_id());
			System.out.print(":" + employee.getEmp_id());
			System.out.print(":" + employee.getEmp_name());
			System.out.print(":" + employee.getDept_id());
			System.out.println(":" + employee.getRegistered_date());

			//Employeeの中のDepartmentの中の部署名取得
			System.out.println(":" + employee.getDeprtment().getDept_name());
		}

		/*アップデート*/
		System.out.println("\n\n\nアップデートします。");

		/**入力データ用リストの生成*/

		//引数用リスト
		List<Employee> updateList = new ArrayList<>();
		//一人目
		tmp = new Employee();
		tmp.setEmp_id(1020);
		tmp.setEmp_name("新宿");
		tmp.setDept_id(1);
		updateList.add(tmp);

		//二人目
		tmp = new Employee();
		tmp.setEmp_id(1021);
		tmp.setEmp_name("渋谷");
		tmp.setDept_id(4);
		updateList.add(tmp);
		cnt = dao.update(updateList);
		System.out.println("更新件数:" + cnt);

		System.out.println("確認用表示");
		//確認用検索byID呼び出し　更新に使ったリスト使用
		for (Employee empl : updateList) {
			Employee employee = dao.findById(empl.getEmp_id());
			System.out.print(":" + employee.getEmp_id());
			System.out.print(":" + employee.getEmp_name());
			System.out.print(":" + employee.getDept_id());
			System.out.println(":" + employee.getRegistered_date());

			//Employeeの中のDepartmentの中の部署名取得
			System.out.println(":" + employee.getDeprtment().getDept_name());
		}

	}

}
