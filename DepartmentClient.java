package enshu6_2;

import java.util.List;

public class DepartmentClient {

	/**
	 * デパートメントクラスを呼び出す
	 *
	 * **/
	public static void main(String[] args) {
		DepartmentDao dao=new DepartmentDao();
		List<Department> list =dao.findAll();

		for(Department dep:list) {
			System.out.println(dep.getDept_id());
			System.out.println(dep.getDept_name());
		}

	}

}
