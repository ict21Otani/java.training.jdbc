/**
 *
 */
package enshu6_2;

import java.sql.Timestamp;

/**
 * @author NiB ict21 大谷
 *Employeeテーブル対応のクラス
 */
public class Employee {

		/**
		 * 社員ID
		 **/
		private int  emp_id;
		/**
		 * 社員名
		 **/
		private String emp_name;
		/**
		 * 部署ID
		 **/

		private int dept_id;
		/**
		 * 登録日
		 **/
		private Timestamp registered_date;
		/**
		 * Departmentテーブルクラス
		 **/
		private Department deprtment;

		public int getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(int emp_id) {
			this.emp_id = emp_id;
		}
		public String getEmp_name() {
			return emp_name;
		}
		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}
		public int getDept_id() {
			return dept_id;
		}
		public void setDept_id(int dept_id) {
			this.dept_id = dept_id;
		}
		public Timestamp getRegistered_date() {
			return registered_date;
		}
		public void setRegistered_date(Timestamp registered_date) {
			this.registered_date = registered_date;
		}
		public Department getDeprtment() {
			return deprtment;
		}
		public void setDeprtment(Department deprtment) {
			this.deprtment = deprtment;
		}
}
